package com.example.bloodbank.view.fragment.HomeCycle.donation;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.data.GeneralRequest;
import com.example.bloodbank.view.activity.HomeCycleActivity;
import com.example.bloodbank.view.activity.MapsActivity;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.bloodbank.data.api.ApiClient.getClient;


public class CreateDonationFragment extends BaseFragment {

    SpinnerAdapter governorateAdapter, cityAdapter, bloodTypeAdapter;
    @BindView(R.id.create_donation_fragment_sp_blood_type)
    Spinner createDonationFragmentSpBloodType;
    @BindView(R.id.create_donation_fragment_sp_governorate)
    Spinner createDonationFragmentSpGovernorate;
    @BindView(R.id.create_donation_fragment_sp_city)
    Spinner createDonationFragmentSpCity;


    private static final int EROR_DIALOG_REQUEST = 9001;
    private TextView textViewHospital;
    private View view;


    public CreateDonationFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        view = inflater.inflate(R.layout.fragment_create_donation, container, false);
        ButterKnife.bind(this, view);

        intiSpinner();

        if (isServiceOK()) {
            intiMap();
        }

        return view;
    }

    private void intiMap() {
        TextView textViewHospital = view.findViewById(R.id.create_donation_fragment_tv_hospital_map);
        textViewHospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

    }

    private void intiSpinner() {

        bloodTypeAdapter = new SpinnerAdapter(getActivity());
        GeneralRequest.getSpinnerData(getClient().getBloodType(), bloodTypeAdapter,
                createDonationFragmentSpBloodType, "Blood Type");

        governorateAdapter = new SpinnerAdapter(getActivity());
        GeneralRequest.getSpinnerData(getClient().getGovernorates(), governorateAdapter, createDonationFragmentSpGovernorate,
                "Governorates", new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        cityAdapter = new SpinnerAdapter(getActivity());
                        GeneralRequest.getSpinnerData(getClient().getCities(cityAdapter.selectedId),
                                cityAdapter, createDonationFragmentSpCity, "GeneralResponse");
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });

    }

    public boolean isServiceOK() {
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(getActivity());
        if (available == ConnectionResult.SUCCESS) {
            return true;
        } else if (GoogleApiAvailability.getInstance().isUserResolvableError(available)) {
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(getActivity(), available, EROR_DIALOG_REQUEST);
            dialog.show();
        } else {
            Toast.makeText(baseActivity, "You can't make map request", Toast.LENGTH_SHORT).show();
        }
        return false;
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

