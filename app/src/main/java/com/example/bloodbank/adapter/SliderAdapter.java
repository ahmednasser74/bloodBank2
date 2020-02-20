package com.example.bloodbank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.example.bloodbank.R;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends PagerAdapter {

    private LayoutInflater mLayoutInflater;
    private Context context;
    private List<Integer> sliderImage = new ArrayList<>();
    private List<Integer> sliderText = new ArrayList<>();


    public SliderAdapter(Context context) {
        this.context = context;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        sliderImage.add(R.drawable.ic_slide1);
        sliderImage.add(R.drawable.ic_slide2);
        sliderImage.add(R.drawable.ic_slide3);
        sliderText.add(R.string.slider_desc);
        sliderText.add(R.string.slider_desc);
        sliderText.add(R.string.slider_desc);
    }

    @Override
    public int getCount() {
        return sliderImage.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        View itemView = mLayoutInflater.inflate(R.layout.item_slider, container, false);

        ImageView slideImageView = itemView.findViewById(R.id.item_slider_img);
        TextView textView = itemView.findViewById(R.id.item_slider_txt);


        slideImageView.setImageResource(sliderImage.get(position));
        textView.setText(context.getString(sliderText.get(position)));

        container.addView(itemView);

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }

}