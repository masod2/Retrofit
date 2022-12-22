package com.example.Retrofit.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Retrofit.Data.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WorkViewModel extends ViewModel {
    public MutableLiveData<List<Work>> listMutableLiveData = new MutableLiveData<>();

    public void getWorks(Context context) {
        ApiClient.getINSTANCE(context).getWorks().enqueue(new Callback<BaseResponse<Work>>() {
            @Override
            public void onResponse(Call<BaseResponse<Work>> call, Response<BaseResponse<Work>> response) {
                List<Work> dataWorkList = new ArrayList<>(response.body().getData());
                listMutableLiveData.setValue(dataWorkList);
                Log.e("Statee", dataWorkList.size() + "");
                Log.e("Statee", "listMutableLiveData"+dataWorkList.get(0).getName());

            }

            @Override
            public void onFailure(Call<BaseResponse<Work>> call, Throwable t) {

            }
        });
    }
}

