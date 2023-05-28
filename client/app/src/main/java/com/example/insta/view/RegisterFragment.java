package com.example.insta.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.insta.databinding.FragmentRegisterBinding;
import com.example.insta.model.User;
import com.example.insta.viewModel.UserViewModel;

public class RegisterFragment extends Fragment {

    private FragmentRegisterBinding binding;
    private UserViewModel userViewModel;

    private String TAG = "xxx";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("xxx", "register fragment");
        binding = FragmentRegisterBinding.inflate(getLayoutInflater());
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.btnSignUp.setOnClickListener(v->{
            register();
        });

        return binding.getRoot();
    }

    private void register(){
        String name = String.valueOf(binding.etName.getText());
        String lastname = String.valueOf(binding.etLastname.getText());
        String email = String.valueOf(binding.etEmail.getText());
        String pass = String.valueOf(binding.etPass.getText());
        User user = new User(name, lastname, email, pass);
        Log.d(TAG, user.toString());

        userViewModel.register(user, this);
    }

    public void displayToken(String token){
        String confirmLink = "http://192.168.1.20:3000/api/users/confirm/" + token; //192.168.119.103  192.168.1.20
        binding.tvConfirm.setText(confirmLink);

        binding.tvConfirm.setOnClickListener(v->{
            userViewModel.confirm(token, this);
        });
    }

}