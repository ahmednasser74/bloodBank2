package com.example.bloodbank.view.fragment.HomeCycle.moreSettingContainer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.AllFavoriteAdapter;
import com.example.bloodbank.adapter.ArticlesAdapter;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.model.getAllFavorite.GetAllFavoriteData;
import com.example.bloodbank.data.model.post.PostData;
import com.example.bloodbank.view.activity.BaseActivity;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.bloodbank.data.api.ApiClient.getClient;


public class FavoriteFragment extends BaseFragment {

    @BindView(R.id.fragment_favorite_Rv_posts)
    RecyclerView fragmentFavoriteRvPosts;
    private List<GetAllFavoriteData> ListAllFavoriteData = new ArrayList<>();
    private ApiService apiService = getClient();
    private ArticlesAdapter postAdapter;
    private AllFavoriteAdapter allFavoriteAdapter;


    public FavoriteFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_favorite_list, container, false);
        ButterKnife.bind(this, view);
        init();

        return view;
    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentFavoriteRvPosts.setLayoutManager(linearLayoutManager);

        allFavoriteAdapter = new AllFavoriteAdapter((BaseActivity) getActivity(), ListAllFavoriteData);
        fragmentFavoriteRvPosts.setAdapter(postAdapter);
    }

    private void getFavoritePosts() {
        getClient().getAllFavorite("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27");
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
