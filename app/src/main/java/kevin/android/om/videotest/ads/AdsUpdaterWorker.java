package kevin.android.om.videotest.ads;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

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

//    /**
//     * 通知广告已更新
//     *
//     * @param list
//     */
//    private void notifyAdsUpdate(AdList list) {
//        Intent intent = new Intent();
//        intent.setAction(ACTION_ADS_UPDATE);
//        intent.putExtra(Extras.DATA, (Parcelable) list);
//        final Context context = mContext.get();
//        if (context != null) {
//            context.sendBroadcast(intent);
//        }
//    }

//    private void updateAdsList(final Context context) {
//
//        final Odoo odoo = Odoo.getInstance(context);
//        odoo.adList(new AdsListRequest(), new OdooHttpCallback<AdList>(context) {
//            @Override
//            public void onSuccess(AdList result) {
//                if (null != result) {
//                    ArrayList<Ads> ads = new ArrayList<>();
//                    for (Ads ad : result.records) {
//                        if (
//                            null != ad.ad_url &&
//                            null != ad.ad_type && ad.ad_url.length() > 7) {
//                            ads.add(ad);
//                        }
//                    }
//                    result.records = ads;
//                    AdsUtils.saveAdList(context, result);
//                    notifyAdsUpdate(result);
//                    log.v(TAG, "updateAdsList, onSuccess: 广告更新成功");
//                }
//            }
//
//            @Override
//            public void onError(HttpError error) {
//                super.onError(error);
//                log.w(TAG, "updateAdsList, onError: 广告更新失败");
//            }
//
//            @Override
//            public void onFinish() {
//                super.onFinish();
//                log.v(TAG, "updateAdsList, onFinish: 当次广告更新结束");
//            }
//        });
//    }

    @Override
    protected void onWorking() {
        final Context context = mContext.get();
        if (null == context) {
            Log.w(TAG, "context is null, so stop self.");
            stopWork();
            return;
        }

        Log.v(TAG, "开始更新广告");
//        updateAdsList(context);
        // 等待当次请求结束
        // 3分钟更新一次
        safeWait(60*1000*3);
        Log.d(TAG, "结束广告更新");
    }
}
