package kevin.android.om.videotest.ads;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;


import com.google.gson.Gson;

import java.io.File;
import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <b>Create Date:</b> 8/30/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * <p>
 * <br>
 */
public class AdsUtils {

    private static final String AD_SP_NAME = "ads";
    private static final String AD_KEY_NAME = "ads";
    private static final String SP_ADS_NAME = "ads_download_file_attr";


    private AdsUtils() {
        //no instance
    }

    public static void saveAdList(Context context, List<AdsUpdaterWorker.Adv.Ads> ads) {
        final SharedPreferences sp = context.getSharedPreferences(AD_SP_NAME, Context.MODE_PRIVATE);
        Set<String> strings = new HashSet<>();
        for (AdsUpdaterWorker.Adv.Ads ad : ads) {
            Gson gson = new Gson();
            strings.add(gson.toJson(ad));
        }

        final SharedPreferences.Editor editor = sp.edit();
        editor.putStringSet(AD_KEY_NAME, strings);
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    /**
     * 根据广告类型，返回广告
     *
     * @param context
     *
     * @return
     */
    public static List<AdsUpdaterWorker.Adv.Ads> getAdList(Context context) {
        final SharedPreferences sp = context.getSharedPreferences(AD_SP_NAME, Context.MODE_PRIVATE);
        Set<String> strings = sp.getStringSet(AD_KEY_NAME, new HashSet<String>());
        if (0 == strings.size()) {
            return null;
        }

        List<AdsUpdaterWorker.Adv.Ads> list = new ArrayList<>();

        AdsUpdaterWorker.Adv.Ads ads;
        Gson gson =  new Gson();
        for (String s : strings) {

            ads = gson.fromJson(s, AdsUpdaterWorker.Adv.Ads.class);
            list.add(ads);
        }

        return list;
    }


    /**
     * 根据URL获取对应文件的缓存名称
     *
     * @param url
     *
     * @return
     */
    public static String getCacheFileName(String url) {
        return md5(url);
    }

    /**
     * 获取缓存目录
     *
     * @param context
     *
     * @return
     */
    public static File getCacheDir(Context context) {
        return new File(context.getCacheDir(), "ads");
    }

    /**
     * 根据URL获取缓存文件
     *
     * @param context
     * @param url
     *
     * @return
     */
    public static File getCacheFile(Context context, String url) {
        return new File(getCacheDir(context), getCacheFileName(url));
    }

    private static String md5(String str) {
        StringBuffer buffer = new StringBuffer();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] byts = md.digest(str.getBytes(Charset.forName("utf-8")));
            for (byte byt : byts) {
                int d = byt & 0xFF;
                if (d < 16) {
                    buffer.append(0);
                }
                buffer.append(Integer.toHexString(d));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }


    /**
     * 保存下载的文件的属性
     *
     * @param context 上下文
     */
    public static void setDownloadFileAttr(Context context, String fileName, int value) {
        getSp(context)
                .edit()
                .putInt(fileName, value)
                .apply();
    }


    /**
     * 获取下载文件的属性
     *
     * @param context 上下文
     *
     * @return 下载文件的大小
     */
    public static int getDownloadFileAttr(Context context, String fileName) {
        return getSp(context).getInt(fileName, -1);
    }


    private static SharedPreferences getSp(Context context) {
        return context.getSharedPreferences(SP_ADS_NAME, Context.MODE_PRIVATE);
    }

}
