package com.example.bloodbank.view.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.bloodbank.R;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.view.fragment.HomeCycle.EditProfileFragment;
import com.example.bloodbank.view.fragment.HomeCycle.HomeContainer.HomeContainerFragment;
import com.example.bloodbank.view.fragment.HomeCycle.moreSettingContainer.MoreFragment;
import com.example.bloodbank.view.fragment.HomeCycle.notification.NotificationListFragment;
//import com.google.android.gms.common.ConnectionResult;
//import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeCycleActivity extends BaseActivity {


    @BindView(R.id.home_cycle_fl_fragment_container)
    FrameLayout homeCycleFlFragmentContainer;
    @BindView(R.id.activity_home_bottom_nav_bar)
    BottomNavigationView activityHomeBottomNavBar;

    private static final int EROR_DIALOG_REQUEST = 9001;
    private TextView textViewHospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_cycle);
        ButterKnife.bind(this);

        HelperMethod.replace(new HomeContainerFragment(), getSupportFragmentManager(),
                R.id.home_cycle_fl_fragment_container, null, null);

        initBottonNavigation();
    }

    private void initBottonNavigation() {

        activityHomeBottomNavBar = findViewById(R.id.activity_home_bottom_nav_bar);
        activityHomeBottomNavBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        HelperMethod.replace(new HomeContainerFragment(), getSupportFragmentManager(),
                                R.id.home_cycle_fl_fragment_container, null, null);
                        break;
                    case R.id.nav_profile:
                        HelperMethod.replace(new EditProfileFragment(), getSupportFragmentManager(),
                                R.id.home_cycle_fl_fragment_container, null, null);
                        break;
                    case R.id.nav_notification:
                        HelperMethod.replace(new NotificationListFragment(), getSupportFragmentManager(),
                                R.id.home_cycle_fl_fragment_container, null, null);
                        break;
                    case R.id.nav_more:
                        HelperMethod.replace(new MoreFragment(), getSupportFragmentManager(),
                                R.id.home_cycle_fl_fragment_container, null, null);
                        break;
                }
                return true;
            }
        });
    }

    @OnClick(R.id.home_cycle_fl_fragment_container)
    public void onViewClicked(View view) {
        /**this to dismiss keyboard when touch background*/
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void setSelection(int id) {
        activityHomeBottomNavBar.setSelectedItemId(id);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}