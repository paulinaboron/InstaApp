package com.example.insta.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.insta.R;
import com.example.insta.databinding.ActivityProfileBinding;
import com.example.insta.viewModel.ProfileViewModel;

public class ProfileActivity extends AppCompatActivity {
    private ActivityProfileBinding binding;
    private String TAG = "xxx";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        binding = ActivityProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        getSupportFragmentManager().setFragmentResult("datafromactivity", bundle);
    }
}