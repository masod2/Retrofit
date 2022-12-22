package com.example.Retrofit;

import static com.android.volley.Request.Method.GET;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.AuthFailureError;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.Retrofit.databinding.ActivityCustumerHomeBinding;
import com.example.Retrofit.model.authenticationViewModel;
import com.example.Retrofit.serr.TokenSaver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class    CustomerHome extends AppCompatActivity {
    ActivityCustumerHomeBinding binding; //عمل بايندينج للعناصر بعد تفعيلها بالجريدل
     String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustumerHomeBinding.inflate(getLayoutInflater());//تعريف الباينديج على الواجهة
        setContentView(binding.getRoot());

        binding.logOut.setOnClickListener(v -> {
            authenticationViewModel authenticationViewModel = new ViewModelProvider(this).get(authenticationViewModel.class);
            authenticationViewModel.logout(getApplicationContext());
            startActivity(new Intent(getApplicationContext(), LogInActivity.class)); // يتم الانتقال لشاشة تسجيل الدخول بكل الحالات
            finish();
        });
        binding.loadcu.setOnClickListener(v -> {
            url = "https://studentucas.awamr.com/api/order/un/complete/user";
            postTokenToHome();
        });
    }


    private void postTokenToHome() {
        String token = TokenSaver.getToken(this);

        Toast.makeText(this, "on postTokenToHome", Toast.LENGTH_SHORT).show();
        binding.progressBar4.setVisibility(View.VISIBLE);
        //انشاء ريكويست جديد
        //end onResponse
        JsonObjectRequest objectRequest = new JsonObjectRequest(GET, url, null
                , response -> {
            binding.progressBar4.setVisibility(View.INVISIBLE);
            //فحص حالة استجابة السيرفر
            try {
                if (response.getBoolean("success")) {                    //ما يحدث عند نجاح الاستقبال

                    JSONArray jsonArray = response.getJSONArray("data");//قراءة الاري الموجود بالرد الذى تم استلامه
                    binding.reesponnse.setText(response.toString());
                    //    for (int i = 0; i < jsonArray.length(); i++) { // قراءة عناصر المصفوفة الموجودة بالرد
                    JSONObject jsonObject1 = jsonArray.getJSONObject(1); // قراءة العنصر iمن المصفوفة
                    //     String name = jsonObject1.getString("created_at");//قراءة كل قيمة بالاوبجيتك عل حدة حسب ال key الموجود بالسيرفر وتخزينها بمتغير محلى
                    //   int id = jsonObject1.getInt("user_id");
                    binding.data.setText(jsonObject1.toString());


                    //  }
                    Toast.makeText(getApplicationContext(), " " + response.getString("message"), Toast.LENGTH_SHORT).show();

                } else {

                    // (اخطاء مدخلات )ما يحدث عند فشل  الاستقبال
                    Toast.makeText(getApplicationContext(), " " + response.getString("error"), Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }, error -> {
            binding.progressBar4.setVisibility(View.INVISIBLE);

            Toast.makeText(this, error.getMessage(), Toast.LENGTH_SHORT).show();
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", token);
                return headers;

            }
        };
        binding.progressBar4.setVisibility(View.INVISIBLE);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(objectRequest);
    } // end method postTokenToHome


}