package com.example.Retrofit.Data;


import com.example.Retrofit.model.BaseResponse;
import com.example.Retrofit.model.HomeDeliverReq;
import com.example.Retrofit.model.LoginResponse;
import com.example.Retrofit.model.Work;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface DataInterface {
    @GET("all/works")
    Call<BaseResponse<Work>> getWorks();
    @GET("home/deliver")
    Call<BaseResponse<HomeDeliverReq>> homeDeliverReq();
    @GET("auth/logout")
    Call<Void> logOut();
    @FormUrlEncoded
    @POST("auth/login/user")
    Call<BaseResponse<LoginResponse>> loginUser(@Field("email") String email, @Field("password") String password);
    @FormUrlEncoded
    @POST("auth/login/delivery")
    Call<BaseResponse<LoginResponse>> loginDelivery(@Field("email") String email, @Field("password") String password);
}
