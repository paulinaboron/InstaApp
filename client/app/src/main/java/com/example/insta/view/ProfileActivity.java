package com.example.insta.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
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

        binding.bottomNavigation.setOnItemSelectedListener(v -> {
            switch (v.getItemId()) {

                case R.id.itemEdit:
                    replaceFragment(new EditFragment());
                    break;
                case R.id.itemProfile:
                    replaceFragment(new GalleryFragment());
            }
            return true;
        });


    }

    void replaceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }

}