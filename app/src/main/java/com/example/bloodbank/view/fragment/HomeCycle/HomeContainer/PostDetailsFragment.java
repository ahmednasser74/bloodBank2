package com.example.bloodbank.view.fragment.HomeCycle.HomeContainer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.model.getAllFavorite.GetAllFavorite;
import com.example.bloodbank.data.model.getAllFavorite.GetAllFavoriteData;
import com.example.bloodbank.data.model.post.PostData;
import com.example.bloodbank.data.model.postDetails.PostDetails;
import com.example.bloodbank.data.model.postDetails.PostDetailsData;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;

public class PostDetailsFragment extends BaseFragment {

    @BindView(R.id.post_fragment_details_img)
    ImageView postFragmentDetailsImg;
    @BindView(R.id.post_fragment_details_tv_title)
    TextView postFragmentDetailsTvTitle;
    @BindView(R.id.post_fragment_details_tv_details)
    TextView postFragmentDetailsTvDetails;

    public PostData post;
    public GetAllFavoriteData getAllFavoriteData;
    public int position;
    private ApiService apiService = getClient();

    public PostDetailsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_post_details, container, false);
        ButterKnife.bind(this, view);
        apiService = getClient();

        getItemDetails();

        return view;
    }

    private void getItemDetails() {

        postFragmentDetailsTvTitle.setText(post.getTitle());
        postFragmentDetailsTvDetails.setText(post.getContent());

        Glide.with(getActivity()).load(post.getThumbnailFullPath()).into(postFragmentDetailsImg);


        //        if (post.getIsFavourite()) {
//            postFragmentDetailsImg.setImageResource(R.drawable.shape_favorite_icon);
//        } else {
//            postFragmentDetailsImg.setImageResource(R.drawable.shape_favorite_icon_none);
//        }

    }

}
