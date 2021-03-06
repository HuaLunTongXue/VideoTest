package kevin.android.om.videotest.ads;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * <b>Create Date:</b> 2016/12/7<br>
 * <b>Author:</b> Stone <br>
 * <b>Description:</b>
 * 广告自动更新服务
 * <br>
 */
public class AdsUpdaterWorker extends Worker{
    public static final String ACTION_ADS_UPDATE = "vmc.ACTION_ADS_UPDATE";
    private static final String TAG = "AdsUpdater";
    private static AdsUpdaterWorker INSTANCE;
    private WeakReference<Context> mContext;
    private static final String TEST_URL = "https://svmdemo02.hollywant.com/app_api/vmc";
    private static final String json = "{\"jsonrpc\":\"2.0\",\"method\":\"call\",\"params\":{\"method\":\"vmc_ad_list\",\"machine_id\":\"6\",\"app_version\":\"0.5.0\"},\"id\":3}";
    private static final int UPDATE_TIME = 60*1000;

    //构造方法
    private AdsUpdaterWorker(Context context) {
        this.mContext = new WeakReference<Context>(context);
    }

    //单例
    public static AdsUpdaterWorker getInstance(Context context) {
        if (null == INSTANCE) {
            synchronized (AdsUpdaterWorker.class) {
                if (null == INSTANCE) {
                    INSTANCE = new AdsUpdaterWorker(context.getApplicationContext());
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 通知广告已更新
     *
     * @param list
     */
    private void notifyAdsUpdate(List<Adv.Ads> list) {
        Intent intent = new Intent();
        intent.setAction(ACTION_ADS_UPDATE);
        String[] urls = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            urls[i] = list.get(i).ad_url;
        }
        intent.putExtra("adsList", urls);
        final Context context = mContext.get();
        if (context != null) {
            context.sendBroadcast(intent);
        }
    }

    private void updateAdsList(final Context context) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    post(TEST_URL,json,context);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        ).start();
    }





    @Override
    protected void onWorking() {
        final Context context = mContext.get();
        if (null == context) {
            Log.w(TAG, "context is null, so stop self.");
            stopWork();
            return;
        }

        Log.v(TAG, "开始更新广告");
        updateAdsList(context);
        // 等待当次请求结束
        // 3分钟更新一次
        safeWait(UPDATE_TIME);
        Log.d(TAG, "结束广告更新");
    }



    private void httpGet(String response,Context context){
        try {
            Gson gson = new Gson();
            Log.e(TAG,"response="+response);
            JSONObject jsonObject = new JSONObject(response);
            Object json;
            String result;
            json = jsonObject.opt("result");
            Log.e(TAG,"jsonObject.jsonrpc="+jsonObject.opt("jsonrpc"));
            Log.e(TAG,"jsonObject.id="+jsonObject.opt("id"));
            Log.e(TAG,"jsonObject.result="+jsonObject.opt("result"));
            result = json.toString();
            Log.e(TAG,"result="+result);
            Adv aModel = gson.fromJson(json.toString(),Adv.class);
            Log.e(TAG,"aModel.result="+aModel.total);
            List<Adv.Ads> list = aModel.records;
            List<Adv.Ads> videoList = new ArrayList<>();
            int length = aModel.records.size();
            for(int i = 0;i<length;i++){
                if(aModel.records.get(i).ad_type.equals("VIDEO")){
                    Log.e(TAG,"ad_url="+aModel.records.get(i).ad_url);
                    videoList.add(aModel.records.get(i));
                }
            }
            AdsUtils.saveAdList(context,videoList);
            notifyAdsUpdate(videoList);


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void post(String url, String json,Context context) throws IOException {
        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String data = response.body().string();
            httpGet(data,context);
            Log.i(TAG,"response.body()="+data);

        } else {
            Log.i(TAG,response.toString());

            throw new IOException("Unexpected code " + response);
        }
    }


    public class Adv{
        public int total;
        public ArrayList<Ads> records;

        public class Ads {
            public int ad_order;
            public String ad_url;
            public String ad_detail;
            public String ad_type;

        }

    }

}
