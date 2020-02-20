package com.example.bloodbank.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;

public class BaseActivity extends AppCompatActivity {
    //ba3ml el base de 3shan adef 3aleha ay functions ana 3ayzha w a3ml ay activity extend meno
    public BaseFragment baseFragment;


    public void superBackPressed() {

       super.onBackPressed();
//de sabta ben el base fragment w el base activity 3shan lama a3ml back fe el fragment yrag3ny w howa byt3aml m3 el activity
    }

    @Override
    public void onBackPressed() {
        baseFragment.onBack();//el on back de 3shan ab2a 3mlt on back fe fragment mesh fe activity
    }
}
