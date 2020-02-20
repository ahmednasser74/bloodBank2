package com.example.bloodbank.view.fragment.HomeCycle.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.NotificationSettingAdapter;
import com.example.bloodbank.data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.data.model.notificationSetting.NotificationSetting;
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


public class NotificationSettingFragment extends BaseFragment {

    @BindView(R.id.notification_fragment_setting_rv_blood)
    RecyclerView NotificationSettingFragmentRvBlood;
    @BindView(R.id.notification_fragment_setting_rv_city)
    RecyclerView NotificationSettingFragmentRvCity;
    @BindView(R.id.notification_fragment_setting_img_blood_type)
    ImageView notificationFragmentSettingImgBloodType;
    @BindView(R.id.notification_fragment_setting_img_city)
    ImageView notificationFragmentSettingImgCity;
    @BindView(R.id.notification_fragment_setting_btn_save)
    Button notificationFragmentSettingBtnSave;
    @BindView(R.id.notification_fragment_setting_rel_blood_recycler)
    RelativeLayout notificationFragmentSettingRelBloodRecycler;

    private List<String> bloodTypes = new ArrayList<>(), governorate = new ArrayList<>();
    private NotificationSettingAdapter bloodAdapter, governorateAdapter;

    public NotificationSettingFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_notification_setting, container, false);
        ButterKnife.bind(this, view);

        inti();
        getNotificationSetting();
        return view;
    }

    private void inti() {
        NotificationSettingFragmentRvBlood.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        NotificationSettingFragmentRvCity.setLayoutManager(new GridLayoutManager(getActivity(), 3));

    }

    private void getNotificationSetting() {
        getClient().getNotificationSetting("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl").enqueue(new Callback<NotificationSetting>() {
            @Override
            public void onResponse(Call<NotificationSetting> call, Response<NotificationSetting> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        bloodTypes = response.body().getData().getBloodTypes();
                        governorate = response.body().getData().getGovernorates();
                        getBloodType();
                        getGovernorate();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<NotificationSetting> call, Throwable t) {
                try {

                } catch (Exception e) {

                }
            }
        });
    }

    private void getBloodType() {

        getClient().getBloodType().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    bloodAdapter = new NotificationSettingAdapter(getActivity(),
                            response.body().getData(), bloodTypes);
                    NotificationSettingFragmentRvBlood.setAdapter(bloodAdapter);

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
            }
        });
    }

    private void getGovernorate() {
        getClient().getGovernorates().enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {
                try {
                    governorateAdapter = new NotificationSettingAdapter(getActivity(),
                            response.body().getData(), bloodTypes);
                    NotificationSettingFragmentRvCity.setAdapter(governorateAdapter);

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {
            }
        });
    }

    public void visible(View view, ImageView imageView) {
        if (view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
            imageView.setImageResource(R.drawable.ic_minus);
        } else {
            imageView.setImageResource(R.drawable.ic_plus);
            view.setVisibility(View.GONE);
        }
    }

    public void onCall(final boolean state) {
        getClient().setNotificationSetting("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl",
                bloodAdapter.newIds, governorateAdapter.newIds).enqueue(new Callback<NotificationSetting>() {
            @Override
            public void onResponse(Call<NotificationSetting> call, Response<NotificationSetting> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<NotificationSetting> call, Throwable t) {
                try {

                } catch (Exception e) {
                }
            }
        });
    }

    @OnClick({R.id.notification_fragment_setting_rel_btn_blood_type, R.id.notification_fragment_setting_rel_btn_city, R.id.notification_fragment_setting_btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.notification_fragment_setting_rel_btn_blood_type:
                visible(notificationFragmentSettingRelBloodRecycler, notificationFragmentSettingImgBloodType);
                break;
            case R.id.notification_fragment_setting_rel_btn_city:
                visible(NotificationSettingFragmentRvCity, notificationFragmentSettingImgCity);
                break;
            case R.id.notification_fragment_setting_btn_save:
                onCall(false);
                break;
        }
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
