package com.example.insta.view.profile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.example.insta.R;
import com.example.insta.databinding.ActivityProfileBinding;
import com.example.insta.helpers.Utils;
import com.example.insta.view.post.PostActivity;

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
                case R.id.itemCamera:
                    Intent intent = new Intent(ProfileActivity.this, PostActivity.class);
                    startActivity(intent);
            }
            return true;
        });

        Utils.checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, 100, ProfileActivity.this, ProfileActivity.this);
    }

    public void replaceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }



}