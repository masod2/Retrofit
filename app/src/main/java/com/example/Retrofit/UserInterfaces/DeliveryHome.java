package com.example.Retrofit.UserInterfaces;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.Retrofit.ViewModel.AuthenticationViewModel;
import com.example.Retrofit.ViewModel.HomeDeliverReqViewModel;
import com.example.Retrofit.databinding.ActivityDeleveryHomeBinding;
import com.example.Retrofit.services.ReAdapter;
import com.example.Retrofit.services.TokenSaver;

public class DeliveryHome extends AppCompatActivity {
    ActivityDeleveryHomeBinding binding; //عمل بايندينج للعناصر بعد تفعيلها بالجريدل

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDeleveryHomeBinding.inflate(getLayoutInflater());//تعريف الباينديج على الواجهة
        setContentView(binding.getRoot());
        Log.d("Statee", " on DeliveryHome");
        // التحقق من وجود توكن
        if (!TokenSaver.getToken(this).equals("")) {
            HomeDeliverReqViewModel homeDeliverReqViewModel = new ViewModelProvider(this).get(HomeDeliverReqViewModel.class);
            homeDeliverReqViewModel.homeDeliverReq(getApplicationContext());
            homeDeliverReqViewModel.MutableLiveData.observe(this, homeDeliverReqs -> {
                if (homeDeliverReqs != null) {
                    ReAdapter adapter = new ReAdapter(homeDeliverReqs);
                    adapter.setOnItemClickListener(position -> {
                        TokenSaver.setPositionLong(getApplicationContext(), homeDeliverReqs.get(position).getLat());
                        TokenSaver.setPositionLong(getApplicationContext(), homeDeliverReqs.get(position).getLong());

                        startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                    });
                    binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    binding.recyclerView.setAdapter(adapter);

                }
            });


            Toast.makeText(this, "DONE", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "token not exist please log in to load data", Toast.LENGTH_SHORT).show();


        }
        binding.logout.setOnClickListener(v -> {
            AuthenticationViewModel authenticationViewModel = new ViewModelProvider(this).get(AuthenticationViewModel.class);
            authenticationViewModel.logout(getApplicationContext());

            authenticationViewModel.StringMutableLiveData.observe(this, res -> {
                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                finish();

            });
        });


    }


}