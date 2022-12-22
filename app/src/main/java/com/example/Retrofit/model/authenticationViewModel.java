package com.example.Retrofit.model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Retrofit.Data.ApiClient;
import com.example.Retrofit.serr.TokenSaver;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class authenticationViewModel extends ViewModel {
    public MutableLiveData<BaseResponse<authenticationResponse>>  MutableLiveData = new MutableLiveData<>();

    public void loginAsUser(Context context , String email ,String password) {
        ApiClient.getINSTANCE(context).loginUser(email,password).enqueue(new Callback<BaseResponse<authenticationResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<authenticationResponse>> call, Response<BaseResponse<authenticationResponse>> response) {
                if (response.isSuccessful()  ) {

                    if (response.body() != null) {
                        TokenSaver.setToken(context,response.body().getObject().getToken());
                      }

                    MutableLiveData.setValue(response.body());

                }else {
                    MutableLiveData.setValue(null );

                }

            }

            @Override
            public void onFailure(Call<BaseResponse<authenticationResponse>> call, Throwable t) {
                MutableLiveData.setValue(null);

            }
        });
    }
    public void loginAsDelivery(Context context , String email ,String password) {
        ApiClient.getINSTANCE(context).loginDelivery(email,password).enqueue(new Callback<BaseResponse<authenticationResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<authenticationResponse>> call, Response<BaseResponse<authenticationResponse>> response) {
                if (response.isSuccessful()  ) {
                    if (response.body() != null) {
                        String token = response.body().getObject().getToken();
                        Log.d("statee",token);
                        TokenSaver.setToken(context,token);

                    }
                    MutableLiveData.setValue(response.body());
                }else {
                    MutableLiveData.setValue(null );

                }
            }

            @Override
            public void onFailure(Call<BaseResponse<authenticationResponse>> call, Throwable t) {
                MutableLiveData.setValue(null);

            }
        });
    }
    public void logout (Context context){
        ApiClient.getINSTANCE(context).logout().enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()  ) {
                    MutableLiveData.setValue(response.body());
                    TokenSaver.logout(context);
                }else {
                    MutableLiveData.setValue(null );

                }

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }
}

