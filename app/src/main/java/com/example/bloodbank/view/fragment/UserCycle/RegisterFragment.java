package com.example.bloodbank.view.fragment.UserCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.data.GeneralRequest;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.model.register.Register;
import com.example.bloodbank.helper.DateTxt;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;
import com.google.android.material.textfield.TextInputLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;
import static com.example.bloodbank.helper.HelperMethod.showCalender;


public class RegisterFragment extends BaseFragment {


    @BindView(R.id.register_fragment_btn_register)
    Button registerFragmentBtnLogin;
    @BindView(R.id.register_fragment_Sp_blood_type)
    Spinner registerFragmentSpBloodType;
    @BindView(R.id.register_fragment_sp_governorate)
    Spinner registerFragmentSpGovernorate;
    @BindView(R.id.register_fragment_sp_city)
    Spinner registerFragmentSpCity;
    @BindView(R.id.register_fragment_et_date)
    TextView registerFragmentEtDate;
    @BindView(R.id.fragment_slider_tv_create_account)
    TextView fragmentSliderTvCreateAccount;
    @BindView(R.id.register_fragment_et_name)
    TextInputLayout registerFragmentEtName;
    @BindView(R.id.register_fragment_et_email)
    TextInputLayout registerFragmentEtEmail;
    @BindView(R.id.register_fragment_et_last_donate)
    TextView registerFragmentEtLastDonate;
    @BindView(R.id.register_fragment_et_phone_number)
    TextInputLayout registerFragmentEtPhoneNumber;
    @BindView(R.id.register_fragment_et_password)
    TextInputLayout registerFragmentEtPassword;
    @BindView(R.id.register_fragment_et_re_password)
    TextInputLayout registerFragmentEtRePassword;

    SpinnerAdapter governorateAdapter, cityAdapter, bloodTypeAdapter;
    private ApiService apiService = getClient();


    public RegisterFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();

        View view = inflater.inflate(R.layout.fragment_register, container, false);
        ButterKnife.bind(this, view);

        bloodTypeAdapter = new SpinnerAdapter(getActivity());
        GeneralRequest.getSpinnerData(getClient().getBloodType(), bloodTypeAdapter,
                registerFragmentSpBloodType, "Blood Type");

        governorateAdapter = new SpinnerAdapter(getActivity());
        cityAdapter = new SpinnerAdapter(getActivity());

        GeneralRequest.getSpinnerData(getClient().getGovernorates(), governorateAdapter, registerFragmentSpGovernorate,
                "Governorates", new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        if (i != 0) {
                            GeneralRequest.getSpinnerData(getClient().getCities(cityAdapter.selectedId),
                                    cityAdapter, registerFragmentSpCity, "City");
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
        return view;
    }

    public void getRegisterData() {

        String name = registerFragmentEtName.getEditText().getText().toString();
        if (name.isEmpty()) {
            Toast.makeText(getActivity(), "Please Enter Name", Toast.LENGTH_SHORT).show();
        }
        String email = registerFragmentEtEmail.getEditText().getText().toString();
        if (email.isEmpty()) {
            Toast.makeText(getActivity(), "Please Enter E-mail", Toast.LENGTH_SHORT).show();
        }
        String date = registerFragmentEtDate.toString().trim();
        if (date.isEmpty()) {
            Toast.makeText(getActivity(), "Please Enter BirthDate", Toast.LENGTH_SHORT).show();
        }
        String lastDonate = registerFragmentEtLastDonate.toString().trim();
        if (lastDonate.isEmpty()) {
            Toast.makeText(getActivity(), "Please Enter lastDonate", Toast.LENGTH_SHORT).show();
        }
        String phone = registerFragmentEtPhoneNumber.getEditText().getText().toString();
        if (phone.isEmpty()) {
            Toast.makeText(getActivity(), "Please Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
        String password = registerFragmentEtPassword.getEditText().getText().toString();
        if (password.isEmpty()) {
            Toast.makeText(getActivity(), "Please Enter Password", Toast.LENGTH_SHORT).show();
        }
        String passwordConfirmation = registerFragmentEtRePassword.getEditText().getText().toString();
        if (passwordConfirmation.isEmpty()) {
            Toast.makeText(getActivity(), "Enter Password Confirmation", Toast.LENGTH_SHORT).show();
        }
        int bloodType = bloodTypeAdapter.selectedId;
        if (bloodType == 0) {
            Toast.makeText(getActivity(), "Please Select Blood Type", Toast.LENGTH_SHORT).show();
        }
        int city = cityAdapter.selectedId;
        if (city == 0) {
            Toast.makeText(getActivity(), "Please Select City", Toast.LENGTH_SHORT).show();
        }

        apiService.getRegister(name, email, date, city, phone, lastDonate, password, passwordConfirmation, bloodType).enqueue(new Callback<Register>() {
            @Override
            public void onResponse(Call<Register> call, Response<Register> response) {

                try {
                    if (response.body().getStatus() == 1) {
                        Toast.makeText(getActivity(), response.body().getMsg(), Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Register> call, Throwable t) {

            }
        });
    }

    @OnClick({R.id.register_fragment_et_date, R.id.register_fragment_btn_register, R.id.register_fragment_et_last_donate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_fragment_et_date:
                DateTxt dateTxt = new DateTxt("01", "01", "2019", "01-01-2019");
                showCalender(getActivity(), "Select Date", registerFragmentEtDate, dateTxt);
                break;
            case R.id.register_fragment_btn_register:
                getRegisterData();
                break;
            case R.id.register_fragment_et_last_donate:
                DateTxt dateTxt1 = new DateTxt("01", "01", "2019", "01-01-2019");
                showCalender(getActivity(), "Select Last Donation", registerFragmentEtLastDonate, dateTxt1);
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