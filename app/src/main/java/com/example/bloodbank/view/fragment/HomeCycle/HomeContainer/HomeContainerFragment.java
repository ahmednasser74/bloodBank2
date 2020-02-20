package com.example.bloodbank.view.fragment.HomeCycle.HomeContainer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.ViewPagerFragmentAdapter;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.view.fragment.HomeCycle.donation.CreateDonationFragment;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.view.View.GONE;


public class HomeContainerFragment extends BaseFragment {

    @BindView(R.id.fragment_home_container_Vp)
    ViewPager fragmentHomeContainerVp;
    @BindView(R.id.fragment_home_container_Tl)
    TabLayout fragmentHomeContainerTl;
    @BindView(R.id.fragment_home_container_btn_floating)
    FloatingActionButton fragmentHomeContainerBtnFloating;

    private ViewPagerFragmentAdapter viewPagerFragmentAdapter;

    public HomeContainerFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_home_container, container, false);
        ButterKnife.bind(this, view);

        fragmentHomeContainerVp = view.findViewById(R.id.fragment_home_container_Vp);
        fragmentHomeContainerTl = view.findViewById(R.id.fragment_home_container_Tl);

        viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager());
        viewPagerFragmentAdapter.addPager(new ArticlesFragment(), "Articles");
        viewPagerFragmentAdapter.addPager(new DonationFragment(), "Donation");
        fragmentHomeContainerVp.setAdapter(viewPagerFragmentAdapter);
        fragmentHomeContainerTl.setupWithViewPager(fragmentHomeContainerVp);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBack() {
        super.onBack();
    }

    @OnClick(R.id.fragment_home_container_btn_floating)
    public void onViewClicked() {
        CreateDonationFragment createDonationFragment = new CreateDonationFragment();
        HelperMethod.replace(createDonationFragment, getActivity().getSupportFragmentManager(),
                R.id.home_cycle_fl_fragment_container, null, null);
    }
}
