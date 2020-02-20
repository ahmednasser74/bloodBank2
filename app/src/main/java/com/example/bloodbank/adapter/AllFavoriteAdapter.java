package com.example.bloodbank.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bloodbank.R;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.model.getAllFavorite.GetAllFavoriteData;
import com.example.bloodbank.data.model.post.PostData;
import com.example.bloodbank.data.model.postToggleFavorite.PostToggleFavorite;
import com.example.bloodbank.helper.HelperMethod;
import com.example.bloodbank.view.activity.BaseActivity;
import com.example.bloodbank.view.fragment.HomeCycle.HomeContainer.PostDetailsFragment;
import com.example.bloodbank.view.fragment.UserCycle.LoginFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.bloodbank.data.api.ApiClient.getClient;

public class AllFavoriteAdapter extends RecyclerView.Adapter<AllFavoriteAdapter.ViewHolder> {

    private BaseActivity activity;
    private List<GetAllFavoriteData> ListAllFavoriteData = new ArrayList<>();

    public AllFavoriteAdapter(BaseActivity activity, List<GetAllFavoriteData> listAllFavoriteData) {
        this.activity = activity;
        ListAllFavoriteData = listAllFavoriteData;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.item_articles_post_blood,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {

        holder.position = position;
        GetAllFavoriteData getAllFavoriteData = ListAllFavoriteData.get(position);
        holder.title.setText(getAllFavoriteData.getTitle());
        Glide.with(activity).load(getAllFavoriteData.getThumbnailFullPath()).into(holder.fragmentItemPostsImage);

        if (ListAllFavoriteData.get(position).getIsFavourite()) {
            holder.fragmentItemPostsImgFavorite.setImageResource(R.drawable.shape_favorite_icon);
        }
    }

    private void setAction(ViewHolder holder, int position) {

        holder.position = position;

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(activity, "Opened", Toast.LENGTH_SHORT).show();
                PostDetailsFragment postDetailsFragment = new PostDetailsFragment();
                postDetailsFragment.getAllFavoriteData = ListAllFavoriteData.get(position);
                HelperMethod.replace(postDetailsFragment, activity.getSupportFragmentManager(),
                        R.id.home_cycle_fl_fragment_container, null, null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return ListAllFavoriteData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.fragment_item_posts_image)
        ImageView fragmentItemPostsImage;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.fragment_item_posts_img_favorite)
        ImageView fragmentItemPostsImgFavorite;

        private View view;
        private ApiService apiService;
        public int position;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.fragment_item_posts_img_favorite)
        public void onViewClicked() {
            apiService = getClient();

            ListAllFavoriteData.get(position).setIsFavourite(!ListAllFavoriteData.get(position).getIsFavourite());

            if (ListAllFavoriteData.get(position).getIsFavourite()) {
                fragmentItemPostsImgFavorite.setImageResource(R.drawable.shape_favorite_icon);
            } else {
                fragmentItemPostsImgFavorite.setImageResource(R.drawable.shape_favorite_icon_none);
            }

            getClient().getPostToggleFav(ListAllFavoriteData.get(position).getId(), LoginFragment.getApiToken()).enqueue(new Callback<PostToggleFavorite>() {
                @Override
                public void onResponse(Call<PostToggleFavorite> call, Response<PostToggleFavorite> response) {
                    try {

                        if (response.body().getStatus() != 1) {

                            ListAllFavoriteData.get(position).setIsFavourite(!ListAllFavoriteData.get(position).getIsFavourite());

                            if (ListAllFavoriteData.get(position).getIsFavourite()) {
                                fragmentItemPostsImgFavorite.setImageResource(R.drawable.shape_favorite_icon);
                            } else {
                                fragmentItemPostsImgFavorite.setImageResource(R.drawable.shape_favorite_icon_none);
                            }
                        }
                    } catch (Exception e) {
                    }
                }

                @Override
                public void onFailure(Call<PostToggleFavorite> call, Throwable t) {
                    ListAllFavoriteData.get(position).setIsFavourite(!ListAllFavoriteData.get(position).getIsFavourite());

                    if (ListAllFavoriteData.get(position).getIsFavourite()) {
                        fragmentItemPostsImgFavorite.setImageResource(R.drawable.shape_favorite_icon);
                    } else {
                        fragmentItemPostsImgFavorite.setImageResource(R.drawable.shape_favorite_icon_none);
                    }
                }
            });
        }
    }
}
