package com.example.bloodbank.view.fragment.HomeCycle.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.NotificationListAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.model.notificationList.NotificationList;
import com.example.bloodbank.data.model.notificationList.NotificationListData;
import com.example.bloodbank.helper.OnEndLess;
import com.example.bloodbank.view.activity.BaseActivity;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;


public class NotificationListFragment extends BaseFragment {

    @BindView(R.id.notification_list_fragment_Rv_notification_list)
    RecyclerView notificationListFragmentRvNotificationList;
    private List<NotificationListData> listNotificationDataList = new ArrayList<>();

    private LinearLayoutManager linearLayoutManager;
    private NotificationListAdapter notificationListAdapter;
    private OnEndLess onEndLess;
    private int maxPage = 0;

    public NotificationListFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_notification_list, container, false);
        ButterKnife.bind(this, view);

        init();
        return view;
    }

    private void init() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        notificationListFragmentRvNotificationList.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {

                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getNotification(current_page);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                }
            }
        };
        notificationListFragmentRvNotificationList.addOnScrollListener(onEndLess);

        notificationListAdapter = new NotificationListAdapter((BaseActivity) getActivity(), listNotificationDataList);
        notificationListFragmentRvNotificationList.setAdapter(notificationListAdapter);

        getNotification(1);
    }

    /**
     * for request
     */
    private void getNotification(int Page) {
        getClient().getNotificationList("W4mx3VMIWetLcvEcyF554CfxjZHwdtQldbdlCl2XAaBTDIpNjKO1f7CHuwKl", 1).enqueue(new Callback<NotificationList>() {
            @Override
            public void onResponse(Call<NotificationList> call, Response<NotificationList> response) {
                try {
                    if (response.body().getStatus() == 1) {
                        maxPage = response.body().getData().getLastPage();
                        listNotificationDataList.addAll(response.body().getData().getData());
                        notificationListAdapter.notifyDataSetChanged();

                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void onFailure(Call<NotificationList> call, Throwable t) {

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
}
