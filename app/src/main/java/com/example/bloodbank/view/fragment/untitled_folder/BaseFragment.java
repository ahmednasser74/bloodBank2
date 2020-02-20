package com.example.bloodbank.view.fragment.untitled_folder;

import androidx.fragment.app.Fragment;

import com.example.bloodbank.view.activity.BaseActivity;


public class BaseFragment extends Fragment {

    public BaseActivity baseActivity;
    public BaseFragment baseFragment;

    public void setUpActivity() {//method de han3ml feha en el fragment ely sh8alen 3aleha now heya el obkject ely sh8a fe base activity

        baseActivity = (BaseActivity) getActivity(); //kol da 3shan azbt on back pressed 3shan lazm tkon
        baseActivity.baseFragment = this;

        // b3ml initialize ll obj da b el obj ely fe base activity 3shan yakhod el onBackPressed mn el fragment el mfto7a now
    }

    public void onBack() {//el on back de 3shan ab2a 3mlt on back fe fragment mesh fe activity
        baseActivity.superBackPressed();
    }

    @Override
    public void onStart() {
        super.onStart();
        setUpActivity();
    }

}
