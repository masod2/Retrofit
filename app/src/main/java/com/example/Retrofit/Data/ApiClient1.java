//package com.example.Retrofit.Data;
//
//
//
// import com.example.Retrofit.pojo.Work;
//
// import retrofit2.Call;
// import retrofit2.Retrofit;
//import retrofit2.converter.gson.GsonConverterFactory;
//
//
//
//
//
//
//
//public class ApiClient1 {
//    private static final String BASE_URL = "https://studentucas.awamr.com/api/";
//    private final DataInterface dataInterface;
//    private static ApiClient1 INSTANCE;
//
//    private ApiClient1() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(BASE_URL)
//                .build();
//        dataInterface = retrofit.create(DataInterface.class);
//    }
//
//    public static ApiClient1 getINSTANCE() {
//        if (null == INSTANCE){
//            INSTANCE = new ApiClient1();
//        }
//        return INSTANCE;
//    }
//
//    public Call<Work> getAllWork(){
//        return dataInterface.getAllWork();
//    }
//}
