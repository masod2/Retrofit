package com.example.Retrofit.ViewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Retrofit.ApiSitting.ApiClient;
import com.example.Retrofit.model.BaseResponse;
import com.example.Retrofit.model.authenticationResponse;
import com.example.Retrofit.services.TokenSaver;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationViewModel extends ViewModel {
    public MutableLiveData<BaseResponse<authenticationResponse>> MutableLiveData = new MutableLiveData<>();

    public void loginAsUser(Context context, String email, String password) {
        ApiClient.getINSTANCE(context).loginUser(email, password).enqueue(new Callback<BaseResponse<authenticationResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<authenticationResponse>> call, Response<BaseResponse<authenticationResponse>> response) {
                Log.d("statee", response.isSuccessful() + "");
                if (response.isSuccessful()) {
                    Log.d("statee", "response.isSuccessful 1");
                    if (response.body().success) {
                        Log.d("statee", "response.body().success 2 ");

                        if (response.body().getObject().getToken() == null) {
                            Log.d("statee", "response.body().getObject().getToken()!=null 3");

                            String token = response.body().getObject().getToken();
                            Log.d("statee", token);
                            TokenSaver.setToken(context, token);

                        } else {
                            Log.e("statee", "response.body().getObject().getToken()==null ");
                        }
                        Log.d("statee", response.body().message);

                    } else {
                        Log.e("statee", "response.body(). NOT  success 2 ");
                        Log.e("statee", response.body().message);

                    }
                    Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("statee", "response. NOT  Successful 1");

                }
            }

            @Override
            public void onFailure(Call<BaseResponse<authenticationResponse>> call, Throwable t) {

            }
        });
    }//end of loginAsUser

    public void loginAsDelivery(Context context, String email, String password) {
        Log.d("statee", "loginAsDelivery in viwe model 1 ");
        ApiClient.getINSTANCE(context).loginDelivery(email, password).enqueue(new Callback<BaseResponse<authenticationResponse>>() {
             @Override
            public void onResponse(Call<BaseResponse<authenticationResponse>> call, Response<BaseResponse<authenticationResponse>> response) {
                 Log.d("statee", "loginAsDelivery onResponse viwe model 2");
                 Log.d("statee", response.isSuccessful() + "");
                if (response.isSuccessful()) {
                    Log.d("statee", "response.isSuccessful 1");
                    if (response.body().success) {
                        Log.d("statee", "response.body().success 2 ");

                        if (response.body().getObject().getToken() == null) {
                            Log.d("statee", "response.body().getObject().getToken()!=null 3");

                            String token = response.body().getObject().getToken();
                            Log.d("statee", token);
                            TokenSaver.setToken(context, token);

                        } else {
                            Log.e("statee", "response.body().getObject().getToken()==null ");
                        }
                        Log.d("statee", response.body().message);

                    } else {
                        Log.e("statee", "response.body(). NOT  success 2 ");
                        Log.e("statee", response.body().message);

                    }
                    Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("statee", "response. NOT  Successful 1");

                }

            }

            @Override
            public void onFailure(Call<BaseResponse<authenticationResponse>> call, Throwable t) {
                Log.e("statee", "loginAsDelivery onFailure viwe model 2");

            }
        });

    }//end of loginAsDelivery

    public void registerAsDelivery(Context context, String name, String email, String password, int phone, int work_id) {
        Log.d("statee", "registerAsDelivery in viwe model 1 ");

        ApiClient.getINSTANCE(context).registerDelivery(name, email, password, phone, work_id).enqueue(new Callback<BaseResponse<authenticationResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<authenticationResponse>> call, Response<BaseResponse<authenticationResponse>> response) {
                Log.d("statee", "registerAsDelivery onResponse viwe model 2");
                Log.d("statee", response.isSuccessful() + "");
                if (response.isSuccessful()) {
                    Log.d("statee", "response.isSuccessful 1");
                    if (response.body().success) {
                        Log.d("statee", "response.body().success 2 ");

                        if (response.body().getObject().getToken() == null) {
                            Log.d("statee", "response.body().getObject().getToken()!=null 3");

                            String token = response.body().getObject().getToken();
                            Log.d("statee", token);
                            TokenSaver.setToken(context, token);

                        } else {
                            Log.e("statee", "response.body().getObject().getToken()==null ");
                        }
                        Log.d("statee", response.body().message);

                    } else {
                        Log.e("statee", "response.body(). NOT  success 2 ");
                        Log.e("statee", response.body().message);

                    }
                    Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("statee", "response. NOT  Successful 1");

                }

            }

            @Override
            public void onFailure(Call<BaseResponse<authenticationResponse>> call, Throwable t) {
                Log.e("statee", "registerAsDelivery onFailure viwe model 2");

            }
        });
    }//end of registerAsDelivery

    public void registerAsUser(Context context, String name, String email, String password, int phone) {
        Log.d("statee", "registerAsUser in viwe model 1 ");

        ApiClient.getINSTANCE(context).registerUser(name, email, password, phone).enqueue(new Callback<BaseResponse<authenticationResponse>>() {
            @Override
            public void onResponse(Call<BaseResponse<authenticationResponse>> call, Response<BaseResponse<authenticationResponse>> response) {

                Log.d("statee", "registerAsUser onResponse viwe model 2");
                Log.d("statee", response.isSuccessful() + "");
                if (response.isSuccessful()) {
                    Log.d("statee", "response.isSuccessful 1");
                    if (response.body().success) {
                        Log.d("statee", "response.body().success 2 ");

                        if (response.body().getObject().getToken() == null) {
                            Log.d("statee", "response.body().getObject().getToken()!=null 3");

                            String token = response.body().getObject().getToken();
                            Log.d("statee", token);
                            TokenSaver.setToken(context, token);

                        } else {
                            Log.e("statee", "response.body().getObject().getToken()==null ");
                        }
                        Log.d("statee", response.body().message);

                    } else {
                        Log.e("statee", "response.body(). NOT  success 2 ");
                        Log.e("statee", response.body().message);

                    }
                    Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("statee", "response. NOT  Successful 1");

                }

            }

            @Override
            public void onFailure(Call<BaseResponse<authenticationResponse>> call, Throwable t) {
                Log.e("statee", "registerAsUser onFailure viwe model 2");

            }
        });
    }

    public void logout(Context context) {
        ApiClient.getINSTANCE(context).logout().enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    MutableLiveData.setValue(response.body());
                    TokenSaver.logout(context);
                } else {
                    MutableLiveData.setValue(null);

                }

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });
    }//end of logout
}

