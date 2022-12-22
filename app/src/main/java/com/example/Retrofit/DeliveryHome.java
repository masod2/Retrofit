package com.example.Retrofit;

import static com.android.volley.Request.Method.GET;
import static com.android.volley.Request.Method.POST;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.Retrofit.databinding.ActivityDeleveryHomeBinding;
import com.example.Retrofit.model.HomeDeliverReq;
import com.example.Retrofit.model.PhotoOrderHome;
import com.example.Retrofit.model.Work;
import com.example.Retrofit.serr.ReAdapter;
import com.example.Retrofit.serr.TokenSaver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DeliveryHome extends AppCompatActivity {
    ActivityDeleveryHomeBinding binding; //عمل بايندينج للعناصر بعد تفعيلها بالجريدل
    String url;
    ArrayList<HomeDeliverReq> datumArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeleveryHomeBinding.inflate(getLayoutInflater());//تعريف الباينديج على الواجهة
        setContentView(binding.getRoot());
        Log.e("Statee", "DeliveryHome");
        url = "https://studentucas.awamr.com/api/home/deliver";
 // التحقق من وجود توكن
        if (!TokenSaver.getToken(this).equals("")) {
            postTokenToHome();

            Toast.makeText(this, "DONE", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "token not exist please log in to load data", Toast.LENGTH_SHORT).show();


        }
        binding.logout.setOnClickListener(v -> {
            url = "https://studentucas.awamr.com/api/auth/logout";
            // التحقق من وجود توكن
            if (!TokenSaver.getToken(this).equals("")) {
                LogOut();

            } else {
                Toast.makeText(this, "token not exist please log in to load data", Toast.LENGTH_SHORT).show();

            }
        });


    }

    private void LogOut() {
        String token = TokenSaver.getToken(this);

        Toast.makeText(this, "Start loging out", Toast.LENGTH_SHORT).show();
        binding.progressBar3.setVisibility(View.VISIBLE);
        Log.e("Stateee", "LogOut 1 ");

//انشاء ريكويست جديد
        JsonObjectRequest objectRequest = new JsonObjectRequest(GET, url, null
                , response -> {
            binding.progressBar3.setVisibility(View.INVISIBLE);
            Log.e("Stateee", "on Request 3 ");

            //فحص حالة استجابة السيرفر
            try {
                // يتم الانتقال لشاشة تسجيل الدخول بكل الحالات
                if (response.getBoolean("success")) {                    //ما يحدث عند نجاح الاستقبال
                    Log.e("Stateee", "on success 4 ");

                    Toast.makeText(this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    TokenSaver.logout(this);

                } else {

                    // (اخطاء مدخلات )ما يحدث عند فشل  الاستقبال
                    Toast.makeText(getApplicationContext(), response.getString("Unauthenticated") + response.getString("error"), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), response.getString("التوكن منتهى الصلاحية أو غير موجود   ") + response.getString("error"), Toast.LENGTH_SHORT).show();
                    Log.e("Stateee", "  input rong  5 ");

                }
                Log.e("Stateee", "  after intent   6 ");

                startActivity(new Intent(getApplicationContext(), LogInActivity.class)); // يتم الانتقال لشاشة تسجيل الدخول بكل الحالات
                finish();


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
            binding.progressBar3.setVisibility(View.INVISIBLE);

            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", token);
                return headers;

            }

        };
        binding.progressBar3.setVisibility(View.INVISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(objectRequest);

    } // end method LogOut

    private void postTokenToHome() {
        String token = TokenSaver.getToken(this);

        binding.progressBar3.setVisibility(View.VISIBLE);
        Log.e("Statee", " on postTokenToHome 1");
        //انشاء ريكويست جديد
        JsonObjectRequest objectRequest = new JsonObjectRequest(POST, url, null
                , response -> {
            Log.e("Statee", " on response 2");

            binding.progressBar3.setVisibility(View.INVISIBLE);
            //فحص حالة استجابة السيرفر
            try {
                if (response.getBoolean("success")) {                    //ما يحدث عند نجاح الاستقبال
                    Log.e("Statee", " on success 3");
                    Toast.makeText(this, response.getString("message"), Toast.LENGTH_SHORT).show();
                    JSONArray jsonArray = response.getJSONArray("data");//قراءة الاري الموجود بالرد الذى تم استلامه

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i); // قراءة العنصر iمن المصفوفة

                        int orderid = jsonObject1.getInt("id");
                        String workName = jsonObject1.getJSONObject("work").getString("name");
                        int workid = jsonObject1.getJSONObject("work").getInt("id");
                        String imgs = jsonObject1.getJSONObject("photo_order_home").getString("photo");
                        String created_at = jsonObject1.getString("created_at");
                        Log.d("Statee", orderid + workName + created_at + workid + imgs);

                        HomeDeliverReq datum = new HomeDeliverReq();
                        datum.setId(orderid);
                        datum.setWork(new Work(workid, workName));
                        datum.setPhotoOrderHome(new PhotoOrderHome(imgs));
                        datum.setCreatedAt(created_at);
                        datum.setLat(jsonObject1.getString("lat"));
                        datum.setLong(jsonObject1.getString("long"));
                        datumArrayList.add(datum);
                    }
                    ReAdapter adabter = new ReAdapter(datumArrayList);
                    binding.resy.setLayoutManager(new LinearLayoutManager(this));
                    binding.resy.setAdapter(adabter);


                    //  }
                    Toast.makeText(getApplicationContext(), " " + response.getString("message"), Toast.LENGTH_SHORT).show();
                    Log.e("Statee", " end of  success 4" + response.getString("message"));

                } else {

                    // (اخطاء مدخلات )ما يحدث عند فشل  الاستقبال
                    Toast.makeText(getApplicationContext(), " " + response.getString("error"), Toast.LENGTH_SHORT).show();
                    Log.e("Statee", " اخطاء مدخلات " + response.getString("message"));

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            binding.progressBar3.setVisibility(View.INVISIBLE);

            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", token);
                return headers;
            }
        };
        binding.progressBar3.setVisibility(View.INVISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(objectRequest);
    } // end method postTokenToHome


}