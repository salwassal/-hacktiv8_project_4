package com.hacktiv8.bux.ui.loginregister;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hacktiv8.bux.databinding.ActivitySetupPhoneBinding;
import com.hacktiv8.bux.model.User;

public class SetupPhoneActivity extends AppCompatActivity {

    private ActivitySetupPhoneBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetupPhoneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        if (currentUser==null) {
            finish();
        }
        db = FirebaseFirestore.getInstance();

        binding.btnSaveNumber.setOnClickListener(v -> {
            postData();
        });
    }

    private void postData() {
        User user = new User();

        String phoneNumber = binding.inputPhoneNumber.getText().toString().trim();

        if (!phoneNumber.equals("")) {
            user.setUid(currentUser.getUid());
            user.setName(currentUser.getDisplayName());
            user.setPhoneNumber(phoneNumber);
            user.setEmail(currentUser.getEmail());

            db.collection("user").document(phoneNumber).set(user)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Intent intent = new Intent(SetupPhoneActivity.this, FinishRegisActivity.class);
                            startActivity(intent);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(SetupPhoneActivity.this, "failed to setup number", Toast.LENGTH_SHORT).show();
                        }
                    });

        } else {
            Toast.makeText(SetupPhoneActivity.this, "Please enter your phone number", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        FirebaseAuth.getInstance().signOut();
    }
}