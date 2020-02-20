package com.example.bloodbank.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;

import com.example.bloodbank.R;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.view.fragment.UserCycle.LoginFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserCycleActivity extends BaseActivity {

    @BindView(R.id.user_cycle_fl_fragment_container)
    FrameLayout userCycleFlFragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_cycle);
        ButterKnife.bind(this);

        LoginFragment loginFragment = new LoginFragment();
        HelperMethod.replace(loginFragment, getSupportFragmentManager(),
                R.id.user_cycle_fl_fragment_container, null, null);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @OnClick(R.id.user_cycle_fl_fragment_container)
    public void onViewClicked(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        /**this to dismiss keyboard when touch background*/
    }
}
