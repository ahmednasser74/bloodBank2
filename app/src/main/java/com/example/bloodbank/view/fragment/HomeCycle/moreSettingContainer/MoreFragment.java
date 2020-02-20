package com.example.bloodbank.view.fragment.HomeCycle.moreSettingContainer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.bloodbank.R;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.view.activity.UserCycleActivity;
import com.example.bloodbank.view.fragment.HomeCycle.notification.NotificationSettingFragment;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static android.content.ContentValues.TAG;


public class MoreFragment extends BaseFragment {

    public MoreFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        ButterKnife.bind(this, view);


        return view;
    }

    @OnClick({R.id.fragment_more_fav, R.id.fragment_more_contact, R.id.fragment_more_about, R.id.fragment_more_rate, R.id.fragment_more_notification_setting, R.id.fragment_more_log_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_more_fav:
                HelperMethod.replace(new FavoriteFragment(), getActivity().getSupportFragmentManager(),
                        R.id.home_cycle_fl_fragment_container, null, null);
                break;
            case R.id.fragment_more_contact:
                HelperMethod.replace(new ContactUsFragment(), getActivity().getSupportFragmentManager(),
                        R.id.home_cycle_fl_fragment_container, null, null);
                break;
            case R.id.fragment_more_about:
                HelperMethod.replace(new AboutAppFragment(), getActivity().getSupportFragmentManager(),
                        R.id.home_cycle_fl_fragment_container, null, null);
                break;
            case R.id.fragment_more_rate:
                Intent i = new Intent(android.content.Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://play.google.com/store/apps/details?id=my packagename "));
                startActivity(i);
                break;
            case R.id.fragment_more_notification_setting:
                HelperMethod.replace(new NotificationSettingFragment(), getActivity().getSupportFragmentManager(),
                        R.id.home_cycle_fl_fragment_container, null, null);
                break;
            case R.id.fragment_more_log_out:
                dialogLogOut();
                break;
        }
    }

    private void dialogLogOut() {
        new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_warning_logo)
                .setTitle("LogOut")
                .setMessage("Are you sure to LogOut ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getActivity(), UserCycleActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().finish();
                        Toast.makeText(baseActivity, "You Have LogOut", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No", null)
                .show();

        /**this with designed dialog*/
//                Dialog dialog = new Dialog(getActivity());
//        LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        View v = inflater.inflate(R.layout.item_sign_out_dialog, null);
//
//        dialog.setContentView(v);
//
//        Button button = (Button) dialog.findViewById(R.id.item_sign_out_btn_yes);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), UserCycleActivity.class);
//                getActivity().startActivity(intent);
//                getActivity().finish();
//                Toast.makeText(baseActivity, "You Have LogOut", Toast.LENGTH_SHORT).show();
//            }
//        });
//        Button btn_done = (Button) dialog.findViewById(R.id.item_sign_out_btn_no);
//        btn_done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.show();
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
