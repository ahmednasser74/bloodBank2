package com.example.bloodbank.view.fragment.UserCycle;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.view.activity.HomeCycleActivity;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;
import static com.example.bloodbank.data.local.sharedPreference.SaveData;


public class LoginFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.login_fragment_logo)
    ImageView loginFragmentLogo;
    @BindView(R.id.login_fragment_et_password)
    TextInputLayout loginFragmentEtPassword;
    @BindView(R.id.login_fragment_check_box)
    CheckBox loginFragmentCheckBox;
    @BindView(R.id.login_fragment_tv_forget_password)
    TextView loginFragmentTvForgetPassword;
    @BindView(R.id.login_fragment_btn_login)
    Button loginFragmentBtnLogin;
    @BindView(R.id.login_fragment_btn_create)
    Button loginFragmentBtnCreate;
    @BindView(R.id.login_fragment_et_phone_number)
    TextInputLayout loginFragmentEtPhoneNumber;
    private ApiService apiService = getClient();

    public static String apiToken;

    public static String getApiToken() {
        return apiToken;
    }

    public LoginFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this, view);

        sharedorefrance();
        return view;
    }


    private void sharedorefrance() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", 0);
        if (sharedPreferences.getBoolean("x", false)) {

            Objects.requireNonNull(loginFragmentEtPhoneNumber.getEditText()).setText(sharedPreferences.getString("num", ""));
            loginFragmentEtPassword.getEditText().setText(sharedPreferences.getString("pass", ""));
            loginFragmentCheckBox.setChecked(true);
        }
    }

    @OnClick({R.id.login_fragment_tv_forget_password, R.id.login_fragment_btn_create, R.id.login_fragment_btn_login, R.id.login_fragment_check_box})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.login_fragment_check_box:
                if (loginFragmentCheckBox.isChecked()) {
                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("login", 0);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("num", loginFragmentEtPhoneNumber.getEditText().getText().toString());
                    editor.putString("pass", loginFragmentEtPassword.getEditText().getText().toString());
                    editor.putBoolean("x", true);
//                    editor.putString(API_TOKEN);
                    editor.commit();

                }
                break;
            case R.id.login_fragment_tv_forget_password:
                ForgetPasswordStep1Fragment forgetPasswordStep1Fragment = new ForgetPasswordStep1Fragment();
                HelperMethod.replace(forgetPasswordStep1Fragment, getActivity().getSupportFragmentManager(),
                        R.id.user_cycle_fl_fragment_container, null, null);
                break;

            case R.id.login_fragment_btn_create:
                RegisterFragment loginFragment = new RegisterFragment();
                HelperMethod.replace(loginFragment, getActivity().getSupportFragmentManager(),
                        R.id.user_cycle_fl_fragment_container, null, null);
                break;
            case R.id.login_fragment_btn_login:
                String phone = loginFragmentEtPhoneNumber.getEditText().getText().toString();
                String password = loginFragmentEtPassword.getEditText().getText().toString();
                Login(phone, password);
                break;
        }
    }

    private void Login(String phone, String password) {

        apiService.getLogin(phone, password).enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                try {

                    if (response.body().getStatus() == 1) {

                        SaveData(getActivity(), apiToken, response.body().getData().getApiToken());
                        apiToken = response.body().getData().getApiToken();
                        Intent intent = new Intent(getActivity(), HomeCycleActivity.class);
                        getActivity().startActivity(intent);

                    }

                    Toast.makeText(baseActivity, response.body().getMsg(), Toast.LENGTH_SHORT).show();

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBack() {
        baseActivity.finish();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}