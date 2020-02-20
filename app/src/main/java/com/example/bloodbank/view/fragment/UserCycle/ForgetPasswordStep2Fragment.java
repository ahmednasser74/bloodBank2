package com.example.bloodbank.view.fragment.UserCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodbank.R;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ForgetPasswordStep2Fragment extends BaseFragment {

    @BindView(R.id.forget_fragment_step1_logo)
    ImageView forgetFragmentStep1Logo;
    @BindView(R.id.forget_fragment_step1_et_verification)
    TextInputLayout forgetFragmentStep1EtVerification;
    @BindView(R.id.forget_fragment_step1_et_new_password)
    TextInputLayout forgetFragmentStep1EtNewPassword;
    @BindView(R.id.forget_fragment_step1_et_renew_password)
    TextInputLayout forgetFragmentStep1EtRenewPassword;
    @BindView(R.id.forget_fragment_step2_btn_change)
    Button forgetFragmentStep2BtnSend;

    public ForgetPasswordStep2Fragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

//        initFragment();
        View view = inflater.inflate(R.layout.fragment_forget_password_step2, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.forget_fragment_step2_btn_change)
    public void onViewClicked() {

        LoginFragment loginFragment = new LoginFragment();
        HelperMethod.replace(loginFragment, getActivity().getSupportFragmentManager(),
                R.id.user_cycle_fl_fragment_container, null, null);
    }
}
