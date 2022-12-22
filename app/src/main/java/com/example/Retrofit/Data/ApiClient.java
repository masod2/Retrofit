package com.example.Retrofit.Data;

import androidx.annotation.NonNull;

import com.example.Retrofit.BuildConfig;
import com.example.Retrofit.model.BaseResponse;
import com.example.Retrofit.model.Work;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ApiClient {

    private static String TAG = "ApiClient";
    private static final String BASE_URL = "https://studentucas.awamr.com/api/";
    private static ApiClient INSTANCE;
    private     DataInterface dataInterface;

    private static Retrofit getClient(boolean authorization, int v) {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);

        httpClient.addInterceptor(logging);

        httpClient.addInterceptor(new Interceptor() {
                                      @Override
                                      public Response intercept(@NonNull Chain chain) throws IOException {

                                          Request original = chain.request();
                                          Request request = original.newBuilder()
                                                  .header("Content-Type", "application/x-www-form-urlencoded")
                                                  .header("Accept", "application/json")
                                                  // .header("Accept-Language", "ar")
                                                  .header("Authorization", "")
                                                  .method(original.method(), original.body())
                                                  .build();

                                          return chain.proceed(request);
                                      }
                                  }


        );


        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();


    }

    public static ApiClient getINSTANCE() {
        if (null == INSTANCE) {
            INSTANCE = new ApiClient();
        }
        return INSTANCE;
    }

    public Call<BaseResponse<Work>> getAllWork() {
        return dataInterface.getAllWork();
    }

}
