package com.example.bloodbank.view.fragment.splashCycle;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodbank.R;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;


public class SplashFragment extends BaseFragment {

    private long SPLASH_DISPLAY_LENGTH = 2000;

    public SplashFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_splash, container, false);
        new Handler().postDelayed(new Runnable() { //da byhndl b3d delay mo3yn bynfz el code ely gowah ely fe run
            @Override
            public void run() {
                SliderFragment sliderFragment = new SliderFragment();
                HelperMethod.replace(sliderFragment, getActivity().getSupportFragmentManager(),
                        R.id.splash_cycle_fl_fragment_container, null, null);
            }
        }, SPLASH_DISPLAY_LENGTH);

        return view;
    }

    @Override
    public void onBack() {
        baseActivity.finish();
    }
}
