package com.example.bloodbank.view.fragment.HomeCycle.HomeContainer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.DonationRequestAdapter;
import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.data.GeneralRequest;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.model.getAllDonationRequest.DonationData;
import com.example.bloodbank.data.model.getAllDonationRequest.GetAllDonationRequest;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.helper.OnEndLess;
import com.example.bloodbank.view.activity.BaseActivity;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;


public class DonationFragment extends BaseFragment {


    @BindView(R.id.fragment_donation_Rv_donation)
    RecyclerView fragmentDonationRvDonation;
    @BindView(R.id.donation_fragment_sp_blood_type)
    Spinner donationFragmentSpBloodType;
    @BindView(R.id.donation_fragment_sp_city)
    Spinner donationFragmentSpCity;
    @BindView(R.id.donation_fragment_btn_filter)
    Button donationFragmentBtnFilter;

    private ApiService apiService;
    private DonationRequestAdapter donationRequestAdapter;
    private List<DonationData> ListDonationRequest = new ArrayList<>();
    private SpinnerAdapter bloodTypespinnerAdapter, governoratesSpinnerAdapter;
    private OnEndLess onEndLess;
    private boolean FILTER = false;
    private int maxPage = 0;

    public DonationFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();

        View view = inflater.inflate(R.layout.fragment_donation, container, false);
        ButterKnife.bind(this, view);
        apiService = getClient();


        inti();
        return view;
    }

//    @OnClick({R.id.donation_tv_information, R.id.donation_tv_phone})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.donation_tv_information:
//                HelperMethod.replace(new DonationDetailsFragment(), getActivity().getSupportFragmentManager(),
//                        R.id.home_cycle_fl_fragment_container, null, null);
//                break;
//        }
//    }

    private void inti() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentDonationRvDonation.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getDonation(current_page);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                }
            }
        };
        fragmentDonationRvDonation.addOnScrollListener(onEndLess);

        donationRequestAdapter = new DonationRequestAdapter((BaseActivity) getActivity(), ListDonationRequest);
        fragmentDonationRvDonation.setAdapter(donationRequestAdapter);

        bloodTypespinnerAdapter = new SpinnerAdapter(getActivity());
        GeneralRequest.getSpinnerData(getClient().getBloodType(), bloodTypespinnerAdapter,
                donationFragmentSpBloodType, "Blood Type");

        governoratesSpinnerAdapter = new SpinnerAdapter(getActivity());
        GeneralRequest.getSpinnerData(getClient().getGovernorates(), governoratesSpinnerAdapter,
                donationFragmentSpCity, "Governorates");

        getDonation(1);
    }


    private void getDonation(int page) {
        Call<GetAllDonationRequest> call;

        if (FILTER) {
            call = getClient()
                    .getAllDonationRequest("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", page
                            , bloodTypespinnerAdapter.selectedId, governoratesSpinnerAdapter.selectedId);
        } else {
            call = apiService.getAllDonationRequest("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", 1);
        }

        startCall(call, page);
    }

    private void startCall(Call<GetAllDonationRequest> call, int page) {

        call.enqueue(new Callback<GetAllDonationRequest>() {
            @Override
            public void onResponse(Call<GetAllDonationRequest> call, Response<GetAllDonationRequest> response) {
                try {
                    if (response.body().getStatus() == 1) {

                        if (page == 1) {
                            onEndLess.previousTotal = 0; // The total number of items in the dataset after the last load
                            onEndLess.current_page = 1;
                            onEndLess.previous_page = 1;
                            ListDonationRequest = new ArrayList<>();

                            maxPage=response.body().getData().getLastPage();
                            DonationRequestAdapter donationRequestAdapter = new DonationRequestAdapter((BaseActivity) getActivity(), ListDonationRequest);
                            fragmentDonationRvDonation.setAdapter(donationRequestAdapter);
                        }

                        maxPage = response.body().getData().getLastPage();
                        ListDonationRequest.addAll(response.body().getData().getData());
                        donationRequestAdapter.notifyDataSetChanged();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GetAllDonationRequest> call, Throwable t) {

            }
        });
    }


    @Override
    public void onBack() {
        super.onBack();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @OnClick(R.id.donation_fragment_btn_filter)
    public void onViewClicked() {
        donationFragmentBtnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int bloodType = bloodTypespinnerAdapter.selectedId;
                int city = governoratesSpinnerAdapter.selectedId;
                getDonation(1);
            }
        });
    }
}
