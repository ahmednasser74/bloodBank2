package com.example.bloodbank.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.data.model.notificationList.NotificationList;
import com.example.bloodbank.data.model.notificationList.NotificationListData;
import com.example.bloodbank.view.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.ViewHolder> {

    private BaseActivity activity;

    public NotificationListAdapter( BaseActivity activity, List<NotificationListData> notificationListDataList) {
        this.activity = (BaseActivity) activity;
        this.notificationListDataList = notificationListDataList;
    }

    private List<NotificationListData> notificationListDataList = new ArrayList<>();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_notification_list,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        if (notificationListDataList.get(position).getPivot().getIsRead().equals("0")) {

            holder.itemNotificationImgRing.setImageResource(R.drawable.ic_red_ring_black_24dp);
        } else{
            holder.itemNotificationImgRing.setImageResource(R.drawable.ic_red_ring_none_black_24dp);
        }
        holder.itemNotificationTvTitle.setText(notificationListDataList.get(position).getTitle());

    }

    private void setAction(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return notificationListDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;

        @BindView(R.id.item_notification_img_ring)
        ImageView itemNotificationImgRing;
        @BindView(R.id.item_notification_tv_title)
        TextView itemNotificationTvTitle;
        @BindView(R.id.item_notification_tv_time)
        TextView itemNotificationTvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
