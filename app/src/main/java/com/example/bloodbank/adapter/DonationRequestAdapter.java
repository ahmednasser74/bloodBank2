package com.example.bloodbank.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.bloodbank.R;
import com.example.bloodbank.data.model.getAllDonationRequest.DonationData;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.view.activity.BaseActivity;
import com.example.bloodbank.view.fragment.HomeCycle.donation.DonationDetailsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DonationRequestAdapter extends RecyclerView.Adapter<DonationRequestAdapter.ViewHolder> {


    private BaseActivity activity;
    private List<DonationData> ListDonationRequest = new ArrayList<>();

    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    private DonationData getAllDonationRequestData;

    public DonationRequestAdapter(BaseActivity activity, List<DonationData> listDonationRequest) {
        this.activity = activity;
        ListDonationRequest = listDonationRequest;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_donation,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        getAllDonationRequestData = ListDonationRequest.get(position);

        holder.donationTvCityName.setText(getAllDonationRequestData.getCity().getName());
        holder.donationTvName.setText(getAllDonationRequestData.getPatientName());
        holder.donationTvHospitalName.setText(getAllDonationRequestData.getHospitalName());
        holder.donationTvBloodType.setText(getAllDonationRequestData.getBloodType().getName());

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ListDonationRequest.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.donation_tv_information)
        TextView donationTvInformation;
        @BindView(R.id.donation_tv_phone)
        TextView donationTvPhone;
        @BindView(R.id.donation_tv_name)
        TextView donationTvName;
        @BindView(R.id.donation_tv_hospital_name)
        TextView donationTvHospitalName;
        @BindView(R.id.donation_tv_city_name)
        TextView donationTvCityName;
        @BindView(R.id.donation_tv_donation_circle)
        TextView donationTvDonationCircle;
        @BindView(R.id.donation_tv_blood_type)
        TextView donationTvBloodType;
        private View view;

        @OnClick({R.id.donation_tv_information, R.id.donation_tv_phone})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.donation_tv_information:
                    HelperMethod.replace(new DonationDetailsFragment(), activity.getSupportFragmentManager(),
                            R.id.home_cycle_fl_fragment_container, null, null);
                    break;

                case R.id.donation_tv_phone:
                    String phone = getAllDonationRequestData.getPhone();
                    Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
                    activity.startActivity(intent);
                    break;
            }
        }

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(), "open item", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
