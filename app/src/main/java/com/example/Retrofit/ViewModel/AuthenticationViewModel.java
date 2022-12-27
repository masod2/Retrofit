package com.example.Retrofit.ViewModel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.Retrofit.ApiSitting.ApiClient;
import com.example.Retrofit.model.BaseResponseString;
import com.example.Retrofit.model.BaseResponseobj;
import com.example.Retrofit.model.authenticationResponse;
import com.example.Retrofit.services.TokenSaver;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthenticationViewModel extends ViewModel {
    public MutableLiveData<authenticationResponse> MutableLiveData = new MutableLiveData<>();
    public MutableLiveData<String> StringMutableLiveData = new MutableLiveData<String>();

    public void loginAsUser(Context context, String email, String password) {
        Log.d("statee", "loginAsUser in viwe model 1");

        ApiClient.getINSTANCE(context).loginUser(email, password).enqueue(new Callback<BaseResponseobj<authenticationResponse>>() {
            @Override
            public void onResponse(Call<BaseResponseobj<authenticationResponse>> call, Response<BaseResponseobj<authenticationResponse>> response) {
                Log.d("statee", "loginAsUser onResponse viwe model 2");
                Log.d("statee", response.isSuccessful() + "");
                if (response.isSuccessful()) {
                    Log.d("statee", "response.isSuccessful 1");
                    if (response.body().success) {
                        Log.d("statee", "response.body().success 2 ");

                        if (response.body().getObject() != null) {
                            Log.d("statee", "response.body().getObject() != null 3");

                            String token = response.body().getObject().getToken();
                            Log.d("statee", token);
                            TokenSaver.setToken(context, token);
                            StringMutableLiveData.setValue(response.body().getMessage());

                        } else {
                            Log.e("statee", "response.body().getObject()==null ");
                        }
                        Log.d("statee", response.body().message);
                        Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("statee", "response.body(). NOT  success 2 ");
                        Log.e("statee", response.body().message);

                    }
                    Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("statee", "response. NOT  Successful 1\n" + response.errorBody().toString());

                }

            }

            @Override
            public void onFailure(Call<BaseResponseobj<authenticationResponse>> call, Throwable t) {
                Log.e("statee", "loginAsUser onFailure viwe model 2\n" + t.getMessage());

            }
        });
    }//end of loginAsUser

    public void loginAsDelivery(Context context, String email, String password) {
        Log.d("statee", "loginAsDelivery in viwe model 1 ");
        ApiClient.getINSTANCE(context).loginDelivery(email, password).enqueue(new Callback<BaseResponseobj<authenticationResponse>>() {
            @Override
            public void onResponse(Call<BaseResponseobj<authenticationResponse>> call, Response<BaseResponseobj<authenticationResponse>> response) {
                Log.d("statee", "loginAsUser onResponse viwe model 2");
                Log.d("statee", response.isSuccessful() + "");
                if (response.isSuccessful()) {
                    Log.d("statee", "response.isSuccessful 1");
                    if (response.body().success) {
                        Log.d("statee", "response.body().success 2 ");

                        if (response.body().getObject() != null) {
                            Log.d("statee", "response.body().getObject() != null 3");

                            String token = response.body().getObject().getToken();
                            Log.d("statee", token);
                            TokenSaver.setToken(context, token);
                            StringMutableLiveData.setValue(response.body().getMessage());

                        } else {
                            Log.e("statee", "response.body().getObject()==null ");
                        }
                        Log.d("statee", response.body().message);
                        Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                    } else {
                        Log.e("statee", "response.body(). NOT  success 2 ");
                        Log.e("statee", response.body().message);

                    }
                    Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("statee", "response. NOT  Successful 1\n" + response.errorBody().toString());

                }

            }

            @Override
            public void onFailure(Call<BaseResponseobj<authenticationResponse>> call, Throwable t) {
                Log.e("statee", "loginAsUser onFailure viwe model 2\n" + t.getMessage());

            }
        });

    }//end of loginAsDelivery

    public void registerAsDelivery(Context context, String name, String email, String password, String phone, int work_id) {
        Log.d("statee", "registerAsDelivery in viwe model 1 ");

        ApiClient.getINSTANCE(context).registerDelivery(name, email, password, phone, work_id).enqueue(new Callback<BaseResponseobj<authenticationResponse>>() {
            @Override
            public void onResponse(Call<BaseResponseobj<authenticationResponse>> call, Response<BaseResponseobj<authenticationResponse>> response) {
                Log.d("statee", "registerAsDelivery onResponse viwe model 2");
                Log.d("statee", response.isSuccessful() + "");
                if (response.isSuccessful()) {
                    Log.d("statee", "response.isSuccessful 1");
                    if (response.body().success) {
                        Log.d("statee", "response.body().success 2 ");

                        if (response.body().getObject() != null) {
                            Log.d("statee", "response.body().getObject() != null 3");

                            String token = response.body().getObject().getToken();
                            Log.d("statee", token);
                            TokenSaver.setToken(context, token);
                            StringMutableLiveData.setValue(response.body().getMessage());

                        } else {
                            Log.e("statee", "response.body().getObject()==null ");
                        }
                        Log.d("statee", response.body().message);
                        Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();

                    } else {
                        Log.e("statee", "response.body(). NOT  success 2 ");
                        Log.e("statee", response.body().message);
                        StringMutableLiveData.setValue(response.body().message);

                    }
                    Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("statee", "response. NOT  Successful 1\n" + response.errorBody().toString());
                    StringMutableLiveData.setValue(" Connect not Successful");

                }

            }

            @Override
            public void onFailure(Call<BaseResponseobj<authenticationResponse>> call, Throwable t) {
                Log.e("statee", "registerAsDelivery onFailure viwe model 2\n" + t.getMessage());
                StringMutableLiveData.setValue(" Connect onFailure ");

            }
        });
    }//end of registerAsDelivery

    public void registerAsUser(Context context, String name, String email, String password, String phone) {
        Log.d("statee", "registerAsUser in viwe model 1 ");

        ApiClient.getINSTANCE(context).registerUser(name, email, password, phone).enqueue(new Callback<BaseResponseobj<authenticationResponse>>() {
            @Override
            public void onResponse(Call<BaseResponseobj<authenticationResponse>> call, Response<BaseResponseobj<authenticationResponse>> response) {
                Log.d("statee", "registerAsUser onResponse viwe model 2");
                Log.d("statee", response.isSuccessful() + "");
                if (response.isSuccessful()) {
                    Log.d("statee", "response.isSuccessful 1");
                    if (response.body().success) {
                        Log.d("statee", "response.body().success 2 ");

                        if (response.body().getObject() != null) {
                            Log.d("statee", "response.body().getObject() != null 3");

                            String token = response.body().getObject().getToken();
                            Log.d("statee", token);
                            TokenSaver.setToken(context, token);
                            StringMutableLiveData.setValue(response.body().getMessage());

                        } else {
                            Log.e("statee", "response.body().getObject()==null ");
                        }
                        Log.d("statee", response.body().message);
                        Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();

                    } else {
                        Log.e("statee", "response.body(). NOT  success 2 ");
                        Log.e("statee", response.body().message);

                    }
                    Toast.makeText(context, response.body().message, Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("statee", "response. NOT  Successful 1\n" + response.errorBody().toString());

                }

            }

            @Override
            public void onFailure(Call<BaseResponseobj<authenticationResponse>> call, Throwable t) {
                Log.e("statee", "registerAsUser onFailure viwe model 2\n" + t.getMessage());

            }
        });
    }

    public void logout(Context context) {
        ApiClient.getINSTANCE(context).logout().enqueue(new Callback<BaseResponseString>() {
            @Override
            public void onResponse(Call<BaseResponseString> call, Response<BaseResponseString> response) {
                if (response.isSuccessful()) {
                    TokenSaver.logout(context);
                    StringMutableLiveData.setValue(response.body().getMessage());

                } else {
                    StringMutableLiveData.setValue("التوكن منتهى الصلاحية ");
                    TokenSaver.logout(context);//سيتم حذف التوكن وان كان منتهى الصلاحية
                }


            }

            @Override
            public void onFailure(Call<BaseResponseString> call, Throwable t) {
                StringMutableLiveData.setValue(t.getMessage());
                Log.e("statee", "Log out onFailure\n " + t.getMessage());
                TokenSaver.logout(context);//سيتم حذف التوكن بكل الحالات

            }
        });
    }//end of logout
}

