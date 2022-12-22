package com.example.Retrofit.Data;


import com.example.Retrofit.model.BaseResponse;
import com.example.Retrofit.model.HomeDeliverReq;
import com.example.Retrofit.model.Work;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DataInterface {
    @GET("all/works")
    Call<BaseResponse<Work>> getAllWork();
    @GET("home/deliver")
    Call<BaseResponse<HomeDeliverReq>> homeDeliverReq();

    @GET("auth/logout")
    Call<Void> logOut();

}
