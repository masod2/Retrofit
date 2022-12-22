
package com.example.Retrofit.model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Retrofit.Data.ApiClient;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Work {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Integer id;

     public Work(int workid, String workName) {
         this.id=workid;
         this.name=workName;
     }

     public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public class WorkViewModel extends ViewModel {
        public MutableLiveData<List< Work>> listMutableLiveData = new MutableLiveData<>();
        MutableLiveData<String> posts = new MutableLiveData<>();

        public void getWorks() {
            ApiClient.getINSTANCE().getAllWork().enqueue(new Callback<BaseResponse<Work>>() {
                @Override
                public void onResponse(Call<BaseResponse<Work>> call, Response<BaseResponse<Work>> response) {
                    List<Work> dataWorkList = new ArrayList<>(response.body().getData());
                    listMutableLiveData.setValue(dataWorkList);
                    Log.e("Statee", dataWorkList.size()+"");

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

}
