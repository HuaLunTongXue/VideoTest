package kevin.android.om.videotest.activities;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoView;

import kevin.android.om.videotest.R;
import kevin.android.om.videotest.VActivity;
import kevin.android.om.videotest.fragments.AdvertFragment;

public class ProductListActivity extends VActivity {

    private static final String TAG = ProductListActivity.class.getSimpleName();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        (findViewById(R.id.mainBgId)).setBackgroundResource(R.color.mainBg);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
