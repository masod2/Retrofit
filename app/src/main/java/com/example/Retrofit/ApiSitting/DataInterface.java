package com.example.Retrofit.ApiSitting;


import com.example.Retrofit.model.BaseResponse;
import com.example.Retrofit.model.HomeDeliverReq;
import com.example.Retrofit.model.authenticationResponse;
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

    @FormUrlEncoded
    @POST("auth/login/user")
    Call<BaseResponse<authenticationResponse>> loginUser(@Field("email") String email, @Field("password") String password);
    @FormUrlEncoded
    @POST("auth/login/delivery")
    Call<BaseResponse<authenticationResponse>> loginDelivery(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/register/user")
    Call<BaseResponse<authenticationResponse>> registerUser(@Field("name") String name,@Field("email") String email, @Field("password") String password,@Field("phone") int phone);
    @FormUrlEncoded
    @POST("auth/register/delivery")
    Call<BaseResponse<authenticationResponse>> registerDelivery(@Field("name") String name,@Field("email") String email, @Field("password") String password,@Field("phone") int phone,@Field("work_id") int work_id);

    @GET("auth/logout")
    Call<BaseResponse> logout();
}
