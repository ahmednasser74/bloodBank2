package com.example.bloodbank.view.fragment.HomeCycle.HomeContainer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bloodbank.R;
import com.example.bloodbank.adapter.ArticlesAdapter;
import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.data.GeneralRequest;
import com.example.bloodbank.data.api.ApiService;
import com.example.bloodbank.data.model.post.Post;
import com.example.bloodbank.data.model.post.PostData;
import com.example.bloodbank.helper.OnEndLess;
import com.example.bloodbank.view.activity.BaseActivity;
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


public class ArticlesFragment extends BaseFragment {


    @BindView(R.id.fragment_home_img_search)
    ImageView fragmentHomeImgSearch;
    @BindView(R.id.fragment_home_ac_search)
    EditText fragmentHomeAcSearch;
    //    @BindView(R.id.articles_fragment_progress_bar)
//    ProgressBar articlesFragmentProgressBar;
    private boolean FILTER = false;
    @BindView(R.id.fragment_articles_RV_posts)
    RecyclerView fragmentArticlesRVPosts;
    @BindView(R.id.fragment_articles_sp_filter)
    Spinner fragmentArticlesSpFilter;

    private SpinnerAdapter filterSpinnerAdapter;
    private List<PostData> ListPostData = new ArrayList<>();
    private ApiService apiService = getClient();
    private ArticlesAdapter postAdapter;
    private OnEndLess onEndLess;
    private int maxPage = 0;

    public ArticlesFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        setUpActivity();
        View view = inflater.inflate(R.layout.fragment_articles, container, false);
        ButterKnife.bind(this, view);

        inti();
        return view;
    }

    private void inti() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentArticlesRVPosts.setLayoutManager(linearLayoutManager);

        onEndLess = new OnEndLess(linearLayoutManager, 1) {
            @Override
            public void onLoadMore(int current_page) {
                if (current_page <= maxPage) {
                    if (maxPage != 0 && current_page != 1) {
                        onEndLess.previous_page = current_page;
                        getArticles(current_page, "", filterSpinnerAdapter.selectedId);
                    } else {
                        onEndLess.current_page = onEndLess.previous_page;
                    }
                } else {
                    onEndLess.current_page = onEndLess.previous_page;
                }
            }
        };
        fragmentArticlesRVPosts.addOnScrollListener(onEndLess);

        postAdapter = new ArticlesAdapter((BaseActivity) getActivity(), ListPostData);
        fragmentArticlesRVPosts.setAdapter(postAdapter);

        filterSpinnerAdapter = new SpinnerAdapter(getActivity());
        GeneralRequest.getSpinnerData(getClient().getCatrgory(), filterSpinnerAdapter,
                fragmentArticlesSpFilter, "Filter");

        getArticles(1, "", 0);
    }

    private void getArticles(int page, String search, int categoryId) {
        Call<Post> call;
        if (FILTER) {
            call = apiService.getPosts("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27", page);
        } else {
            call = apiService.getPostsFilter("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27", page
                    , search, categoryId);
        }

        onCall(call, page);
    }

    private void onCall(Call<Post> call, int page) {
        call.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.body().getStatus() == 1) {
                    if (page == 1) {

                        ListPostData = new ArrayList<>();
                        postAdapter = new ArticlesAdapter((BaseActivity) getActivity(), ListPostData);
                        fragmentArticlesRVPosts.setAdapter(postAdapter);

                        onEndLess.current_page = 1;
                        onEndLess.previous_page = 1;
                        onEndLess.totalItemCount = 0;
                        onEndLess.visibleItemCount = 0;
                    }
                    ListPostData.addAll(response.body().getData().getData());//keda ana set all data
                    postAdapter.notifyDataSetChanged();//de 3shan a2ol ll adapter y8yer el data mn hena y3mlha update 3ala tol
                }

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                try {

                } catch (Exception e) {
                }
            }
        });
    }

    @OnClick(R.id.fragment_home_img_search)
    public void onViewClicked() {
        fragmentHomeImgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = fragmentHomeAcSearch.getText().toString().trim();
                int spinner = filterSpinnerAdapter.selectedId;
                getArticles(1, search, spinner);

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
