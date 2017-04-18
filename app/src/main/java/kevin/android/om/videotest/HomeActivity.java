package kevin.android.om.videotest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import kevin.android.om.videotest.fragments.AdvertFragment;
import kevin.android.om.videotest.fragments.GuideFragment;
import kevin.android.om.videotest.fragments.ImageAdsFragment;
import kevin.android.om.videotest.fragments.InfoFragment;
import kevin.android.om.videotest.fragments.ShoppingFragment;
import kevin.android.om.videotest.fragments.SurpriseFragment;

public class HomeActivity extends AppCompatActivity {
    private static final String FRAGMENT_TAG_INFO = "info";
    private static final String FRAGMENT_TAG_ADVERT = "advert";
    private static final String FRAGMENT_TAG_IMAGE_ADVERT = "image_advert";
    private static final String FRAGMENT_TAG_GUIDE = "guide";
    private static final String FRAGMENT_TAG_SHOPPING = "shopping";
    private static final String FRAGMENT_TAG_SURPRISE = "surprise";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        (findViewById(R.id.mainBgId)).setBackgroundResource(R.color.mainBg);



        final FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = fm.findFragmentByTag(FRAGMENT_TAG_INFO);
        if (null == fragment && null != findViewById(R.id.home_info)) {
            fragment = InfoFragment.newInstance("","");
            ft.add(R.id.home_info, fragment, FRAGMENT_TAG_INFO);
        }


        // 视频广告
        fragment = fm.findFragmentByTag(FRAGMENT_TAG_ADVERT);

        if (null == fragment && null != findViewById(R.id.home_advert_video)) {
            fragment = AdvertFragment.newInstance("","");
            ft.add(R.id.home_advert_video, fragment, FRAGMENT_TAG_ADVERT);
        }
        // 图片广告
        fragment = fm.findFragmentByTag(FRAGMENT_TAG_IMAGE_ADVERT);
        if (null == fragment && null != findViewById(R.id.home_advert_image)) {
            fragment = ImageAdsFragment.newInstance("","");
            ft.add(R.id.home_advert_image, fragment, FRAGMENT_TAG_IMAGE_ADVERT);
        }

        // 点我有惊喜
        fragment = fm.findFragmentByTag(FRAGMENT_TAG_SURPRISE);
        if (null == fragment && null != findViewById(R.id.home_surprise)) {
            if (null == ft) {
                ft = fm.beginTransaction();
            }

            fragment = SurpriseFragment.newInstance("","");
            ft.add(R.id.home_surprise, fragment, FRAGMENT_TAG_SURPRISE);
        }


        // 购物引导
        fragment = fm.findFragmentByTag(FRAGMENT_TAG_GUIDE);
        if (null == fragment && null != findViewById(R.id.home_guide)) {
            if (null == ft) {
                ft = fm.beginTransaction();
            }

            fragment = GuideFragment.newInstance("","");
            ft.add(R.id.home_guide, fragment, FRAGMENT_TAG_GUIDE);
        }


        // 快乐购
        fragment = fm.findFragmentByTag(FRAGMENT_TAG_SHOPPING);
        if (null == fragment && null != findViewById(R.id.home_shopping)) {
            if (null == ft) {
                ft = fm.beginTransaction();
            }

            fragment = ShoppingFragment.newInstance("","");
            ft.add(R.id.home_shopping, fragment, FRAGMENT_TAG_SHOPPING);
        }


        if (null != ft) {
            ft.commit();
        }


    }
}
