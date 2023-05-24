package com.example.insta.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Window;

import com.example.insta.R;
import com.example.insta.databinding.ActivityMainBinding;
import com.example.insta.viewModel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    boolean b = true;
    private UserViewModel userViewModel;
    public String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide(); //hide the title bar

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        replaceFragment(new RegisterFragment());

        binding.btnReplace.setOnClickListener(v->{
            if (b) {
                replaceFragment(new LoginFragment());
                binding.btnReplace.setText(R.string.dont);
            }
            else {
                replaceFragment(new RegisterFragment());
                binding.btnReplace.setText(R.string.already_have_an_account_sign_in);
            }
            b = !b;
        });
    }

    void replaceFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }
}