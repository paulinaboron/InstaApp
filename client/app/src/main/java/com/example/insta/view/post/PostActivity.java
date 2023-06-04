package com.example.insta.view.post;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.insta.R;
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

    public void replaceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }
}