package com.example.Retrofit.ApiSitting;

import android.content.Context;

import com.example.Retrofit.model.BaseResponse;
import com.example.Retrofit.model.BaseResponseobj;
import com.example.Retrofit.model.HomeDeliverReq;
import com.example.Retrofit.model.Work;
import com.example.Retrofit.model.authenticationResponse;
import com.example.Retrofit.services.TokenSaver;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;


public class ApiClient {
    private static final String BASE_URL = "https://studentucas.awamr.com/api/";
    private DataInterface dataInterface;
    private static ApiClient INSTANCE;

    private ApiClient(Context context) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(getClient(context))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        dataInterface = retrofit.create(DataInterface.class);
    }

    public static ApiClient getINSTANCE(Context context) {
        if (null == INSTANCE) {
            INSTANCE = new ApiClient(context);
        }
        return INSTANCE;
    }

    private static OkHttpClient getClient(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60, TimeUnit.SECONDS);
        builder.writeTimeout(60, TimeUnit.SECONDS);
        builder.readTimeout(60, TimeUnit.SECONDS);
        builder.addInterceptor(chain -> {
            Request original = chain.request();
            Request request = original.newBuilder()
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .header("Accept", "application/json")
                    .header("Authorization", TokenSaver.getToken(context))
                    .method(original.method(), original.body())
                    .build();
            return chain.proceed(request);
        });
        OkHttpClient client = builder.build();

        return client;
    }

    public Call<BaseResponse<Work>> getWorks() {
        return dataInterface.getWorks();
    }

    public Call<BaseResponse<HomeDeliverReq>> homeDeliverReq() {
        return dataInterface.homeDeliverReq();
    }

    public Call<BaseResponseobj<authenticationResponse>> loginUser(@Field("email") String email, @Field("password") String password) {
        return dataInterface.loginUser(email, password);
    }

    public Call<BaseResponseobj<authenticationResponse>> loginDelivery(@Field("email") String email, @Field("password") String password) {
        return dataInterface.loginDelivery(email, password);
    }


    public Call<BaseResponseobj<authenticationResponse>> registerDelivery(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("phone") String phone, @Field("work_id") int work_id) {
        return dataInterface.registerDelivery(name, email, password, phone, work_id);
    }

    public Call<BaseResponseobj<authenticationResponse>> registerUser(@Field("name") String name, @Field("email") String email, @Field("password") String password, @Field("phone") String phone) {
        return dataInterface.registerUser(name, email, password, phone);
    }


    public Call<BaseResponse> logout() {
        return dataInterface.logout();
    }
}//end of ApiClient




