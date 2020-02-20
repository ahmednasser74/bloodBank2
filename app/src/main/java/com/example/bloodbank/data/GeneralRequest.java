package com.example.bloodbank.data;

import android.widget.AdapterView;
import android.widget.Spinner;

import com.example.bloodbank.adapter.SpinnerAdapter;
import com.example.bloodbank.data.model.category.Category;
import com.example.bloodbank.data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.view.fragment.untitled_folder.BaseFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralRequest extends BaseFragment {

    public static void getSpinnerData(Call<GeneralResponse> call, SpinnerAdapter spinnerAdapter, Spinner spinner, String hint,
                                      AdapterView.OnItemSelectedListener listener) {

        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {

                try {
                    if (response.body().getStatus() == 1) {
                        spinnerAdapter.setData(response.body().getData(), hint);
                        spinner.setAdapter(spinnerAdapter);
                        spinner.setOnItemSelectedListener(listener);
                    }

                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });

    }

    public static void getSpinnerData(Call<GeneralResponse> call, SpinnerAdapter spinnerAdapter, Spinner spinner, String hint) {

        call.enqueue(new Callback<GeneralResponse>() {
            @Override
            public void onResponse(Call<GeneralResponse> call, Response<GeneralResponse> response) {

                try {
                    if (response.body().getStatus() == 1) {
                        spinnerAdapter.setData(response.body().getData(), hint);
                        spinner.setAdapter(spinnerAdapter);
                    }
                } catch (Exception e) {

                }
            }

            @Override
            public void onFailure(Call<GeneralResponse> call, Throwable t) {

            }
        });

    }

}
//    public static void getArticlesData() {
//        List<PostData> ListPostData = new ArrayList<>();
//        ApiService apiService = getClient();
//        ArticlesAdapter postAdapter = new ArticlesAdapter((BaseActivity) getActivity, ListPostData);
//
//        apiService.getPosts("Zz9HuAjCY4kw2Ma2XaA6x7T5O3UODws1UakXI9vgFVSoY3xUXYOarHX2VH27", 1).enqueue(new Callback<Post>() {
//            @Override
//            public void onResponse(Call<Post> call, Response<Post> response) {
//                if (response.body().getStatus() == 1) {
//                    ListPostData.addAll(response.body().getData().getData());//keda ana set all data
//                    postAdapter.notifyDataSetChanged();//de 3shan a2ol ll adapter y8yer el data mn hena y3mlha update 3ala tol
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Post> call, Throwable t) {
//
//            }
//        });
//    }
//}
