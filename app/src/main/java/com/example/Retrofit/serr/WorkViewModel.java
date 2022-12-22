package com.example.Retrofit.serr;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Retrofit.Data.ApiClient;
import com.example.Retrofit.model.BaseResponse;
import com.example.Retrofit.model.Work;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class WorkViewModel extends ViewModel {
    public MutableLiveData<List<Work>> listMutableLiveData = new MutableLiveData<>();
    MutableLiveData<String> posts = new MutableLiveData<>();

    public void getPosts() {
        ApiClient.getINSTANCE().getAllWork().enqueue(new Callback<BaseResponse<Work>>() {
            @Override
            public void onResponse(Call<BaseResponse<Work>> call, Response<BaseResponse<Work>> response) {
                List<Work> dataWorkList = new ArrayList<>(response.body().data);
                listMutableLiveData.setValue(dataWorkList);
                Log.e("Statee", "listMutableLiveData");
            }

            @Override
            public void onFailure(Call<BaseResponse<Work>> call, Throwable t) {
                posts.setValue("error");
                Log.e("Statee", "onFailure");
            }
        });
    }
}
