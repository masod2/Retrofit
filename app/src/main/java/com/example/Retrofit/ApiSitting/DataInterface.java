package com.example.Retrofit.ApiSitting;


import com.example.Retrofit.model.BaseResponse;
import com.example.Retrofit.model.BaseResponseString;
import com.example.Retrofit.model.BaseResponseobj;
import com.example.Retrofit.model.HomeDeliverReq;
import com.example.Retrofit.model.Work;
import com.example.Retrofit.model.authenticationResponse;

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
    Call<BaseResponseobj<authenticationResponse>> loginUser(@Field("email") String email, @Field("password") String password);
    @FormUrlEncoded
    @POST("auth/login/delivery")
    Call<BaseResponseobj<authenticationResponse>> loginDelivery(@Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("auth/register/user")
    Call<BaseResponseobj<authenticationResponse>> registerUser(@Field("name") String name,@Field("email") String email, @Field("password") String password,@Field("phone") String phone);
    @FormUrlEncoded
    @POST("auth/register/delivery")
    Call<BaseResponseobj<authenticationResponse>> registerDelivery(@Field("name") String name,@Field("email") String email, @Field("password") String password,@Field("phone") String phone,@Field("work_id") int work_id);

    @GET("auth/logout")
    Call<BaseResponseString> logout();
}
