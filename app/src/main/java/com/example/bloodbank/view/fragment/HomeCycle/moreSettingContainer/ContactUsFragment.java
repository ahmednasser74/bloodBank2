package com.example.bloodbank.view.fragment.HomeCycle.moreSettingContainer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.contactUs.ContactUs;
import com.example.bloodbank.data.model.contactUs.ContactUsData;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;

import butterknife.ButterKnife;


public class ContactUsFragment extends BaseFragment {

    private ContactUsData contactUsData;

    public ContactUsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_contact_us, container, false);
        ButterKnife.bind(this, view);

        return view;
    }


    @Override
    public void onBack() {
        super.onBack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
