package com.example.insta.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.insta.R;
import com.example.insta.databinding.FragmentGalleryBinding;
import com.example.insta.databinding.FragmentPhotoBinding;


public class PhotoFragment extends Fragment {
    private FragmentPhotoBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhotoBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }
}