package com.hacktiv8.bux.ui.loginregister;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.hacktiv8.bux.databinding.ActivityFinishRegisBinding;
import com.hacktiv8.bux.ui.MainActivity;

public class FinishRegisActivity extends AppCompatActivity {

    private ActivityFinishRegisBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFinishRegisBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnToBux.setOnClickListener(v -> {
            Intent intent = new Intent(FinishRegisActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}