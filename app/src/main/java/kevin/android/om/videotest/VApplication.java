package kevin.android.om.videotest;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.net.URL;
import java.util.List;

import kevin.android.om.videotest.ads.AdsDownloadWorker;
import kevin.android.om.videotest.ads.AdsUpdaterWorker;

/**
 * <b>Project:</b> VideoTest<br>
 * <b>Create Date:</b> 2017/4/15<br>
 * <b>Author:</b> kevin_zhuang<br>
 * <b>Description:</b> <br>
 */
public class VApplication extends Application {

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String[] downUrl = intent.getStringArrayExtra("adsList");
            AdsDownloadWorker.getInstance(context).download(downUrl);
        }
    };

    @Override
    public void onCreate() {
        super.onCreate();

        registerReceiver(mBroadcastReceiver,new IntentFilter("vmc.ACTION_ADS_UPDATE"));

        AdsUpdaterWorker.getInstance(this).startWork();
        AdsDownloadWorker.getInstance(this).startWork();
    }





}
