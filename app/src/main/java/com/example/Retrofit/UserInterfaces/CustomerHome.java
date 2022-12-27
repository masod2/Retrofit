package com.example.Retrofit.UserInterfaces;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.Retrofit.ViewModel.AuthenticationViewModel;
import com.example.Retrofit.databinding.ActivityCustumerHomeBinding;

public class CustomerHome extends AppCompatActivity {
    ActivityCustumerHomeBinding binding; //عمل بايندينج للعناصر بعد تفعيلها بالجريدل

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCustumerHomeBinding.inflate(getLayoutInflater());//تعريف الباينديج على الواجهة
        setContentView(binding.getRoot());

        binding.logout.setOnClickListener(v -> {
            binding.progressBar4.setVisibility(View.VISIBLE);
            AuthenticationViewModel authenticationViewModel = new ViewModelProvider(this).get(AuthenticationViewModel.class);
            authenticationViewModel.logout(getApplicationContext());
            authenticationViewModel.StringMutableLiveData.observe(this, res -> {
                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                finish();
            });
            binding.progressBar4.setVisibility(View.INVISIBLE);


        });


    }
}