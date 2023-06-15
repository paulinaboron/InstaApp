package com.example.insta.view.login;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.insta.databinding.FragmentLoginBinding;
import com.example.insta.helpers.Utils;
import com.example.insta.model.User;
import com.example.insta.view.profile.ProfileActivity;
import com.example.insta.viewModel.UserViewModel;

import java.util.Objects;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;
    private UserViewModel userViewModel;
    private User user;

    private String TAG = "xxx";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("xxx", "login fragment");
        binding = FragmentLoginBinding.inflate(getLayoutInflater());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.btnSignIn.setOnClickListener(v->{
            login();
        });

        return binding.getRoot();
    }

    private void login(){
        String email = String.valueOf(binding.etEmail.getText());
        String pass = String.valueOf(binding.etPass.getText());
        user = new User(email, pass);

        userViewModel.login(user, this);
    }

    public void loginResponce(String res){
        if(Objects.equals(res, "błędne dane")) Toast.makeText(getContext(), res, Toast.LENGTH_SHORT).show();
        else{
            Utils.token = res;
            Utils.album = user.getEmail();
            Intent intent = new Intent(getContext(), ProfileActivity.class);
            startActivity(intent);
        }
    }


}