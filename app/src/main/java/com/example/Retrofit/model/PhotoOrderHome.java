
package com.example.Retrofit.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Retrofit.ApiSitting.ApiClient;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotoOrderHome {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("order_id")
    @Expose
    private Integer orderId;
    @SerializedName("photo")
    @Expose
    private String photo;

     public PhotoOrderHome(String imgs ,int id , int orderId ) {
         this.id = id;
         this.orderId=orderId;
         this.photo =imgs ;
     }

     public PhotoOrderHome(String imgs) {
         this.photo= imgs;
     }

     public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    public class PhotoOrderHomeModel extends ViewModel {
        public MutableLiveData<List< HomeDeliverReq>> listMutableLiveData = new MutableLiveData<>();
        MutableLiveData<List<PhotoOrderHome>> posts = new MutableLiveData<>();

        public void getHome(Context context) {
            ApiClient.getINSTANCE(context).homeDeliverReq().enqueue(new Callback<BaseResponse<HomeDeliverReq>>() {
                @Override
                public void onResponse(Call<BaseResponse<HomeDeliverReq>> call, Response<BaseResponse<HomeDeliverReq>> response) {
                    List<HomeDeliverReq> homeDeliverReqList = new ArrayList<>(response.body().getData());
                    listMutableLiveData.setValue(homeDeliverReqList);
                    Log.e("Statee", homeDeliverReqList.size()+"");
                    Log.e("Statee", "listMutableLiveData");
                }

                @Override
                public void onFailure(Call<BaseResponse<HomeDeliverReq>> call, Throwable t) {
                    Log.e("Statee", "onFailure");
                }
            });
        }
    }

}
