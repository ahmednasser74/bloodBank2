package com.example.bloodbank.view.activity;

import android.os.Bundle;
import com.example.bloodbank.R;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.view.fragment.splashCycle.SplashFragment;

public class SplashCycleActivity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_cycle);

        SplashFragment splashFragment = new SplashFragment();
        HelperMethod.replace(splashFragment, getSupportFragmentManager(),
                R.id.splash_cycle_fl_fragment_container, null, null);

    }

    @Override
    public void onBackPressed() {
        super.finish();
    }

}
