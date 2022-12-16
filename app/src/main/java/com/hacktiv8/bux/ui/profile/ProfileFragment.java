package com.hacktiv8.bux.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.hacktiv8.bux.databinding.FragmentProfileBinding;
import com.hacktiv8.bux.model.User;
import com.hacktiv8.bux.ui.MainActivity;
import com.hacktiv8.bux.ui.loginregister.LoginRegisterActivity;

public class ProfileFragment extends Fragment {

    private FragmentProfileBinding binding;
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    private FirebaseFirestore db;
    private String phoneNumber, email;
    private static User user;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        currentUser = mAuth.getCurrentUser();

        if (currentUser!=null) {
            binding.emailProfile.setText(currentUser.getEmail());
            binding.nameProfile.setText(String.valueOf(currentUser.getDisplayName()));
            Glide.with(this)
                    .load(currentUser.getPhotoUrl())
                    .centerCrop()
                    .into(binding.img);
        }

        db = FirebaseFirestore.getInstance();

        email = currentUser.getEmail();
        getUserData(email);

        binding.btnSingOut.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            startActivity(new Intent(requireContext(), LoginRegisterActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    private void getUserData(String email) {
        db.collection("user").whereEqualTo("email", email)
                .get().addOnCompleteListener(task -> {
                    if(task.isSuccessful()){
                        for(QueryDocumentSnapshot documentSnapshot: task.getResult()){
                            user = documentSnapshot.toObject(User.class);
                            getPhoneNumber(user);
                        }

                    }
                });

    }

    private void getPhoneNumber(User user){
        phoneNumber = user.getPhoneNumber();
    }
}