package com.example.bloodbank.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.generalResponse.GeneralResponseData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NotificationSettingAdapter extends RecyclerView.Adapter<NotificationSettingAdapter.ViewHolder> {


    private Activity activity;
    private List<GeneralResponseData> notificationSettingData = new ArrayList<>();
    private List<String> oldIds = new ArrayList<>();
    public List<Integer> newIds = new ArrayList<>();

    public NotificationSettingAdapter(Activity activity, List<GeneralResponseData> notificationSettingDataList, List<String> oldIds) {
        this.activity = activity;
        this.notificationSettingData = notificationSettingDataList;
        this.oldIds = oldIds;
    }

    /**
     * de 3shan el id ely gaya mn database el adema el mtsgla
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_notification_setting,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        holder.fragmentItemBloodCb.setText(notificationSettingData.get(position).getName());

        if (oldIds.contains(String.valueOf(notificationSettingData.get(position).getId()))) {
            holder.fragmentItemBloodCb.setChecked(true);
        } else {
            holder.fragmentItemBloodCb.setChecked(false);
        }
    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return notificationSettingData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.fragment_item_blood_cb)
        CheckBox fragmentItemBloodCb;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.fragment_item_blood_cb)
        public void onViewClicked() {

            if (!fragmentItemBloodCb.isChecked()) {
                for (int i = 0; i < newIds.size(); i++) {
                    if (newIds.get(i).equals(notificationSettingData.get(getAdapterPosition()).getId())) {
                        newIds.remove(i);   /**hena lw mwgod w ana 3ayz ams7o mn el newIds */
                        break;
                    }
                }
            } else {
                newIds.add(notificationSettingData.get(getAdapterPosition()).getId());
                /** hena lw 3ady et3mlo check fa keda ha3mlo add */
            }
        }
    }
}
