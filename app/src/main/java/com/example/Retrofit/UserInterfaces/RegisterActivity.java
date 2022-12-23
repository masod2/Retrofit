package com.example.Retrofit.UserInterfaces;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.Retrofit.R;
import com.example.Retrofit.ViewModel.WorkViewModel;
import com.example.Retrofit.databinding.ActivityRegisterBinding;
import com.example.Retrofit.model.Work;
import com.example.Retrofit.services.SpinAdapter;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding; //عمل بايندينج للعناصر بعد تفعيلها بالجريدل
    int id;
    SpinAdapter adapter;
    RequestQueue queue;
    String username, email, password, phone, name, url;
    boolean isValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = ActivityRegisterBinding.inflate(getLayoutInflater());//تعريف الباينديج على الواجهة
        setContentView(binding.getRoot()); // عمل فيو لها
        queue = Volley.newRequestQueue(this);
        binding.show.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                binding.show.setBackground(getDrawable(R.drawable.ic_baseline_lock_open));
                binding.edPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
            } else {
                binding.show.setBackground(getDrawable(R.drawable.ic_baseline_lock_24));
                binding.edPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
        });
        binding.checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (binding.checkBox.isChecked()) {
                getData();

                url = "https://studentucas.awamr.com/api/auth/register/delivery";

            } else {
                binding.spinner.setVisibility(View.INVISIBLE);
                url = " https://studentucas.awamr.com/api/auth/register/user";


            }
        });
        binding.gotoLogInBtn.setOnClickListener(v -> {
            binding.gotoLogInBtn.setBackground(this.getResources().getDrawable(R.drawable.btn_backgroundselected));
            binding.gotoSingUpBtn.setBackground(this.getResources().getDrawable(R.drawable.btn_backgrounselected));

            startActivity(new Intent(getApplicationContext(), LogInActivity.class));
            finish();
        });
        binding.signInText.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(), LogInActivity.class));
            finish();
        });

        binding.btnRegister.setOnClickListener(v -> {
            if (isValid()) {
//do Register req here



            } else {
                Toast.makeText(this, "please inter data in right method", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void getData() {
        WorkViewModel workViewModel = new ViewModelProvider(this).get(WorkViewModel.class);
        workViewModel.getWorks(getApplicationContext());
        workViewModel.listMutableLiveData.observe(this, works -> {
            adapter = new SpinAdapter(getApplicationContext() , android.R.layout.simple_spinner_item, (ArrayList<Work>) works);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            binding.spinner.setAdapter(adapter);
            binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    Work work = (Work) binding.spinner.getSelectedItem();
                    Toast.makeText(RegisterActivity.this, "Name: " + work.getName() + "\nid: " + work.getId() + "\n ", Toast.LENGTH_SHORT).show();
                }
                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                }
            });
            binding.spinner.setVisibility(View.VISIBLE);
        });
    }
    public boolean isValid() {


        if (binding.edEmail.getText().length() < 10) {
            binding.edEmail.setError("This field is required Do not leave it empty ");
            binding.edEmail.requestFocus();
            isValid = false;

        } else {
            email = binding.edEmail.getText().toString().trim();
            if (binding.edUsername.getText().length() < 3) {
                binding.edUsername.setError("This field is required Do not leave it empty ");
                binding.edUsername.requestFocus();
                isValid = false;

            } else {
                username = binding.edUsername.getText().toString().trim();
                if (binding.edPhone.getText().length() != 10) {
                    binding.edPhone.setError("This field is required Do not leave it empty The phone number must consist of 10 digits ");
                    binding.edPhone.requestFocus();
                    isValid = false;

                } else {
                    phone = binding.edPhone.getText().toString().trim();
                    if (binding.edPassword.getText().length() < 8) {
                        binding.edPassword.setError("  This field is required Do not leave it empty The password  must consist of 8 or more   digits ");
                        binding.edPassword.requestFocus();
                        isValid = false;
                    } else {
                        password = binding.edPassword.getText().toString().trim();
                        isValid = true;
                    }

                }
            }
        }
        return isValid;
    }


}
