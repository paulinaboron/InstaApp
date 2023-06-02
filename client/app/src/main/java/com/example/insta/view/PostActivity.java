package com.example.insta.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.insta.databinding.ActivityPostBinding;
import com.example.insta.databinding.ActivityProfileBinding;

public class PostActivity extends AppCompatActivity {
    private ActivityPostBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}