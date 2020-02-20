package com.example.bloodbank.data.api;


import com.example.bloodbank.data.model.donationDetails.DonationDetails;
import com.example.bloodbank.data.model.getAllFavorite.GetAllFavorite;
import com.example.bloodbank.data.model.postToggleFavorite.PostToggleFavorite;
import com.example.bloodbank.data.model.contactUs.ContactUs;
import com.example.bloodbank.data.model.generalResponse.GeneralResponse;
import com.example.bloodbank.data.model.getAllDonationRequest.GetAllDonationRequest;
import com.example.bloodbank.data.model.login.Login;
import com.example.bloodbank.data.model.notificationList.NotificationList;
import com.example.bloodbank.data.model.notificationSetting.NotificationSetting;
import com.example.bloodbank.data.model.post.Post;
import com.example.bloodbank.data.model.postDetails.PostDetails;
import com.example.bloodbank.data.model.register.Register;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;


public interface ApiService {

    @GET("cities")
    Call<GeneralResponse> getCities(@Query("governoratedId") int governoratedId);// da lw method GET w b3d @Query da ely ana bb3to m3 request

    @GET("donation-request")
    Call<DonationDetails> getDonationDetails(@Query("api_token") String apiToken,
                                             @Query("donation_id") int donationId);

    @GET("blood-types")
    Call<GeneralResponse> getBloodType();

    @GET("governorates")
    Call<GeneralResponse> getGovernorates();

    @POST("categories")
    Call<GeneralResponse> getCatrgory();

    @GET("posts")
    Call<Post> getPosts(@Query("api_token") String apiToken,
                        @Query("page") int page);

    @GET("posts")
    Call<Post> getPostsFilter(@Query("api_token") String apiToken,
                              @Query("page") int page,
                              @Query("keyword") String Keyword,
                              @Query("category_id") int categoryId);

    @GET("post")
    Call<PostDetails> getPostDetails(@Query("api_token") String apiToken,
                                     @Query("post_id") int postId,
                                     @Query("page") int page);

    @GET("notifications")
    Call<NotificationList> getNotificationList(@Query("api_token") String apiToken,
                                               @Query("page") int page);

    @POST("notifications-settings")
    @FormUrlEncoded
    Call<NotificationSetting> getNotificationSetting(@Field("api_token") String apiToken);

    @POST("notifications-settings")
    @FormUrlEncoded
    Call<NotificationSetting> setNotificationSetting(@Field("api_token") String apiToken,
                                                     @Field("governorates[]") List<Integer> governorates,
                                                     @Field("blood_types[]") List<Integer> bloodType);

    @GET("donation-requests")
    Call<GetAllDonationRequest> getAllDonationRequest(@Query("api_token") String apiToken,
                                                      @Query("page") int page);


    @GET("donation-requests")
    Call<GetAllDonationRequest> getAllDonationRequest(@Query("api_token") String apiToken,
                                                      @Query("page") int page,
                                                      @Query("blood_type_id") int bloodType,
                                                      @Query("city_id") int city);

    @POST("login")
    @FormUrlEncoded
    Call<Login> getLogin(@Field("phone") String phone,
                         @Field("password") String password);

    @POST("signup")
    @FormUrlEncoded
    Call<Register> getRegister(@Field("name") String name,
                               @Field("email") String email,
                               @Field("birth_date") String birthDate,
                               @Field("city_id") int cityId,
                               @Field("phone") String phone,
                               @Field("donation_last_date") String donationLastDate,
                               @Field("password") String password,
                               @Field("password_confirmation") String passwordConfirmation,
                               @Field("blood_type_id") int bloodTypeID);

    @POST("contact")
    @FormUrlEncoded
    Call<ContactUs> getContactUs(@Field("api_token") String apiToken,
                                 @Field("title") String page,
                                 @Field("message") String message);


    @POST("post-toggle-favourite")
    @FormUrlEncoded
    Call<PostToggleFavorite> getPostToggleFav(@Field("post_id") int postId,
                                              @Field("api_token") String apiToken);


    @GET("my-favourites")
    Call<GetAllFavorite> getAllFavorite(@Query("api_token") String apiToken);


}
