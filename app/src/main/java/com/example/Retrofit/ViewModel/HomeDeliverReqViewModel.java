package com.example.Retrofit.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Retrofit.ApiSitting.ApiClient;
import com.example.Retrofit.model.BaseResponse;
import com.example.Retrofit.model.HomeDeliverReq;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeDeliverReqViewModel extends ViewModel {
    public  MutableLiveData<List<HomeDeliverReq>> MutableLiveData = new MutableLiveData<>();

    public void homeDeliverReq (Context context){
        ApiClient.getINSTANCE(context).homeDeliverReq().enqueue(new Callback<BaseResponse<HomeDeliverReq>>() {
            @Override
            public void onResponse(Call<BaseResponse<HomeDeliverReq>> call, Response<BaseResponse<HomeDeliverReq>> response) {
                if (response.isSuccessful()  ) {
                    List<HomeDeliverReq> homeDeliverReqList = new ArrayList<>(response.body().getData());
                    MutableLiveData.setValue( homeDeliverReqList);

                }else {
                    MutableLiveData.setValue(null );

                }
            }

            @Override
            public void onFailure(Call<BaseResponse<HomeDeliverReq>> call, Throwable t) {
                MutableLiveData.setValue(null );

            }
        });
    }
    }
