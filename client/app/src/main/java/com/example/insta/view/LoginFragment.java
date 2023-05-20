package com.example.insta.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.insta.R;
import com.example.insta.databinding.FragmentLoginBinding;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding fragmentLoginBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("xxx", "login fragment");
        fragmentLoginBinding = FragmentLoginBinding.inflate(getLayoutInflater());
        return fragmentLoginBinding.getRoot();
    }

}