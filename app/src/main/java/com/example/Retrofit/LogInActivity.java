package com.example.Retrofit;


import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.Retrofit.databinding.ActivityLogInBinding;
import com.example.Retrofit.model.authenticationViewModel;
import com.example.Retrofit.serr.TokenSaver;


public class LogInActivity extends AppCompatActivity {
    ActivityLogInBinding binding;
    RequestQueue queue;
     String   password, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLogInBinding.inflate(getLayoutInflater());//تعريف الباينديج على الواجهة
        setContentView(binding.getRoot()); // عمل فيو لها
        queue = Volley.newRequestQueue(this);
        binding.gotoSingUpBtn.setOnClickListener(v -> {
            binding.gotoSingUpBtn.setBackground(this.getResources().getDrawable(R.drawable.btn_backgroundselected));
            binding.gotoLogInBtn.setBackground(this.getResources().getDrawable(R.drawable.btn_backgrounselected));
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            finish();
        });
        binding.signUpText.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
            finish();
        });
        binding.show.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.show.setBackground(getDrawable(R.drawable.ic_baseline_lock_open));
                binding.edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                binding.show.setBackground(getDrawable(R.drawable.ic_baseline_lock_24));
                binding.edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }

        });
        binding.btnLogin.setOnClickListener(v -> {
            Toast.makeText(this, "Try login     ", Toast.LENGTH_SHORT).show();
            authenticationViewModel authenticationViewModel = new ViewModelProvider(this).get(authenticationViewModel.class);
            if (isValid()) {

            if (binding.checkBox2.isChecked()) {
                authenticationViewModel.loginAsDelivery(getApplicationContext(),binding.edEmail.getText().toString().trim(),binding.edPassword.getText().toString().trim());
                authenticationViewModel.MutableLiveData.observe(this, loginResponseBaseResponse ->  {} );
                if (!TokenSaver.getToken(this).equals("")) {
                    startActivity(new Intent(getApplicationContext(), DeliveryHome.class));
                    finish();
                }

            } else {
                authenticationViewModel.loginAsUser(getApplicationContext(),binding.edEmail.getText().toString().trim(),binding.edPassword.getText().toString().trim());
                authenticationViewModel.MutableLiveData.observe(this, loginResponseBaseResponse ->  {} );
                if (!TokenSaver.getToken(this).equals("")) {
                    Toast.makeText(this, TokenSaver.getToken(getApplicationContext()), Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(), CustomerHome.class));
                    finish();
                }
            }
             }
        });

    }
    private boolean isValid() {
        boolean isValid = false;
        if (binding.edEmail.getText().length() < 10 || binding.edEmail.getText().toString().equals("")) {
            binding.edEmail.setError("This field is required Do not leave it empty ");
            binding.edEmail.requestFocus();
            isValid = false;

        } else {
            email = binding.edEmail.getText().toString().trim();
            if (binding.edPassword.getText().length() < 3 || binding.edPassword.getText().toString().equals("")) {
                binding.edPassword.setError("This field is required Do not leave it empty ");
                binding.edPassword.requestFocus();
                isValid = false;

            } else {
                password = binding.edPassword.getText().toString().trim();
                isValid = true;
            }
        }
        return isValid;
    }
}//end class LogInActivity