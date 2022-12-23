package com.example.Retrofit.ViewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Retrofit.ApiSitting.ApiClient;
import com.example.Retrofit.model.BaseResponse;
import com.example.Retrofit.model.authenticationResponse;
import com.example.Retrofit.services.TokenSaver;

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
                    Log.d("statee",response.isSuccessful()+"");
                    if (response.body() != null) {
                        TokenSaver.setToken(context,response.body().getObject().getToken());
                        MutableLiveData.setValue(response.body());
                        Log.d("statee",response.body().getObject().getToken());

                    }
                    Log.d("statee","response.body().getObject() is null");

                }else {
                     Log.d("statee","response.body() is null");

                }

            }

            @Override
            public void onFailure(Call<BaseResponse<authenticationResponse>> call, Throwable t) {

            }
        });
    }//end of loginAsUser
    public void loginAsDelivery(Context context , String email ,String password) {
        ApiClient.getINSTANCE(context).loginDelivery(email, password).enqueue(new Callback<BaseResponse<authenticationResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<authenticationResponse>> call, Response<BaseResponse<authenticationResponse>> response) {


            }

            @Override
            public void onFailure(Call<BaseResponse<authenticationResponse>> call, Throwable t) {

            }
        });
    }//end of loginAsDelivery

    public void registerAsDelivery(Context context ,  String name,  String email,  String password,int phone,  int work_id){
        ApiClient.getINSTANCE(context).registerDelivery(name, email, password, phone, work_id).enqueue(new Callback<BaseResponse<authenticationResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<authenticationResponse>> call, Response<BaseResponse<authenticationResponse>> response) {

            }

            @Override
            public void onFailure(Call<BaseResponse<authenticationResponse>> call, Throwable t) {

            }
        });
    }//end of registerAsDelivery
    public void registerAsUser(Context context ,  String name,  String email,  String password,int phone) {
    ApiClient.getINSTANCE(context).registerUser(name, email, password, phone).enqueue(new Callback<BaseResponse<authenticationResponse>>() {
        @Override
        public void onResponse(Call<BaseResponse<authenticationResponse>> call, Response<BaseResponse<authenticationResponse>> response) {

        }

        @Override
        public void onFailure(Call<BaseResponse<authenticationResponse>> call, Throwable t) {

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
    }//end of logout
}

