package com.example.bloodbank.view.fragment.HomeCycle.donation;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.model.donationDetails.DonationDetails;
import com.example.bloodbank.data.model.donationDetails.DonationDetailsData;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;
import com.google.android.gms.maps.MapView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;


public class DonationDetailsFragment extends BaseFragment {

    @BindView(R.id.donation_details_fragment_tv_name)
    TextView donationDetailsFragmentTvName;
    @BindView(R.id.donation_details_fragment_tv_age)
    TextView donationDetailsFragmentTvAge;
    @BindView(R.id.donation_details_fragment_tv_blood_type)
    TextView donationDetailsFragmentTvBloodType;
    @BindView(R.id.donation_details_fragment_tv_num_bags)
    TextView donationDetailsFragmentTvNumBags;
    @BindView(R.id.donation_details_fragment_tv_hospital)
    TextView donationDetailsFragmentTvHospital;
    @BindView(R.id.donation_details_fragment_tv_address)
    TextView donationDetailsFragmentTvAddress;
    @BindView(R.id.donation_details_fragment_tv_phone)
    TextView donationDetailsFragmentTvPhone;
    @BindView(R.id.donation_details_fragment_map)
    MapView donationDetailsFragmentMap;
    @BindView(R.id.donation_details_fragment_btn_call)
    Button donationDetailsFragmentBtnCall;
    @BindView(R.id.donation_details_fragment_tv_title_name)
    TextView donationDetailsFragmentTvTitleName;

    private DonationDetailsData donationDetailsData;

    public DonationDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        getClient();
        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_donation_details, container, false);
        ButterKnife.bind(this, view);


        intiDetailsData(1);

        return view;
    }

    private void intiDetailsData(int page) {

        getClient().getDonationDetails("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",
                page).enqueue(new Callback<DonationDetails>() {
            @Override
            public void onResponse(Call<DonationDetails> call, Response<DonationDetails> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        donationDetailsFragmentTvTitleName.setText(donationDetailsData.getPatientName());
                        donationDetailsFragmentTvName.setText(donationDetailsData.getPatientName());
                        donationDetailsFragmentTvAge.setText(donationDetailsData.getPatientAge());
                        donationDetailsFragmentTvBloodType.setText(donationDetailsData.getBloodType().getName());
                        donationDetailsFragmentTvNumBags.setText(donationDetailsData.getBagsNum());
                        donationDetailsFragmentTvHospital.setText(donationDetailsData.getHospitalName());
                        donationDetailsFragmentTvAddress.setText(donationDetailsData.getHospitalAddress());
                        donationDetailsFragmentTvPhone.setText(donationDetailsData.getPhone());
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<DonationDetails> call, Throwable t) {
                try {

                } catch (Exception e) {

                }
            }
        });
    }


    @OnClick(R.id.donation_details_fragment_btn_call)
    public void onViewClicked() {
//        donationTvPhone.setText(getAllDonationRequestData.getPhone());
        String phone = "01119193535";
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
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
