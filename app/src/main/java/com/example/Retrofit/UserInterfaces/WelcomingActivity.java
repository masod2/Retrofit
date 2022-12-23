package com.example.Retrofit.UserInterfaces;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.Retrofit.R;
import com.example.Retrofit.services.TokenSaver;
import com.example.Retrofit.databinding.ActivityWelcomingBinding;


public class WelcomingActivity extends AppCompatActivity {
    ActivityWelcomingBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWelcomingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        final int[] i = {1};
        binding.button2.setOnClickListener(v -> {
            if (i[0] == 1) {
                binding.imageView6.setImageDrawable(getDrawable(R.drawable.fbng));
                binding.workname.setText("   Fast reservation with technicians \n" +
                        "               and craftsmen");
                i[0]++;
            } else if (i[0] == 2) {
                binding.imageView6.setImageDrawable(getDrawable(R.drawable.ppp));
                binding.workname.setText("   Sign in to take advantage of the  \n" +
                        "              app's unique features");
                i[0]++;
            } else {
                startActivity(new Intent(getApplicationContext(), LogInActivity.class));
                finish();
                TokenSaver.setIsFirst(this, false);
                // تخزين العملية  لاستخدامها بشاشة السبلاش
            }

        });
    }
}