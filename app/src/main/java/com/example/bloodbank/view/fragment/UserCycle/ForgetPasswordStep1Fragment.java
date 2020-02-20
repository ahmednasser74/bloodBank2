package com.example.bloodbank.view.fragment.UserCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodbank.R;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ForgetPasswordStep1Fragment extends BaseFragment {

    @BindView(R.id.forget_fragment_step1_btn_send)
    Button forgetFragmentStep1BtnSend;

    public ForgetPasswordStep1Fragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

//        initFragment();
        View view = inflater.inflate(R.layout.fragment_forget_password_step1, container, false);
        ButterKnife.bind(this, view);
        return view;
    }


    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.forget_fragment_step1_btn_send)
    public void onViewClicked() {

        ForgetPasswordStep2Fragment forgetPasswordStep2Fragment = new ForgetPasswordStep2Fragment();
        HelperMethod.replace(forgetPasswordStep2Fragment, getActivity().getSupportFragmentManager(),
                R.id.user_cycle_fl_fragment_container, null, null);
    }
}
