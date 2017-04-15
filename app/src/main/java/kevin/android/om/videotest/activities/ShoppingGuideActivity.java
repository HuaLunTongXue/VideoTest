package kevin.android.om.videotest.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kevin.android.om.videotest.R;
import kevin.android.om.videotest.VActivity;

public class ShoppingGuideActivity extends VActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_guide);
        (findViewById(R.id.mainBgId)).setBackgroundResource(R.color.mainBg);

    }
}
