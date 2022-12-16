package com.hacktiv8.bux.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.hacktiv8.bux.databinding.ActivitySplashScreenBinding;
import com.hacktiv8.bux.ui.loginregister.LoginRegisterActivity;

public class SplashScreen extends AppCompatActivity {

    private ActivitySplashScreenBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashScreenBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int time = 3000;
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreen.this, LoginRegisterActivity.class);
            startActivity(intent);
            finish();
        }, time);

    }
}