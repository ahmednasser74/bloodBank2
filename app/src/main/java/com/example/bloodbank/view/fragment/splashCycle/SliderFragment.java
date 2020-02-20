package com.example.bloodbank.view.fragment.splashCycle;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.SliderAdapter;
import com.example.bloodbank.view.activity.UserCycleActivity;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SliderFragment extends BaseFragment {

    ViewPager SliderViewPager;

    LinearLayout mDotsLayout;
    @BindView(R.id.fragment_slider_img_skip)
    ImageView fragmentSliderImgSkip;
    @BindView(R.id.slider_fragment_view_pager)
    ViewPager sliderFragmentVpViewPager;
    private ImageView[] mDots;
    Unbinder unbinder;
    private int mCurrentPage;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_slider, container, false);
        ButterKnife.bind(this, view);

        SliderViewPager = view.findViewById(R.id.slider_fragment_view_pager);
        SliderAdapter sliderAdapter = new SliderAdapter(getActivity());
        SliderViewPager.setAdapter(sliderAdapter);
        SliderViewPager.addOnPageChangeListener(viewListener);
        mDotsLayout = view.findViewById(R.id.slider_dots_Layout);
        addDotsIndicator(0);

        return view;
    }

    private void addDotsIndicator(int current_position) {
        mDots = new ImageView[3];
        if (current_position >= 0) {
            mDotsLayout.removeAllViews();
        }

        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new ImageView(getActivity());

            if (i == current_position) {
                mDots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_red_dot));
            } else {
                mDots[i].setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.shape_gray_dot));
            }
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            params.setMargins(4, 0, 4, 0);
            mDotsLayout.addView(mDots[i], params);
        }
    }

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;
            if (position == 0 || position == 1) {

                fragmentSliderImgSkip.setEnabled(true);
                fragmentSliderImgSkip.setImageResource(R.drawable.shape_white_arrow_icon);

            } else {

                fragmentSliderImgSkip.setEnabled(true);
                fragmentSliderImgSkip.setImageResource(R.drawable.shape_finish_icon);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };


    @OnClick(R.id.fragment_slider_img_skip)
    public void onViewClicked() {
        if (sliderFragmentVpViewPager.getCurrentItem() == 0) {
            sliderFragmentVpViewPager.setCurrentItem(1);
        } else if (sliderFragmentVpViewPager.getCurrentItem() == 1) {
            sliderFragmentVpViewPager.setCurrentItem(2);
            fragmentSliderImgSkip.setImageResource(R.drawable.shape_finish_icon);
        } else {
            Intent intent = new Intent(getActivity(), UserCycleActivity.class);
            getActivity().startActivity(intent);
            getActivity().finish();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBack() {
        baseActivity.finish();
    }

}