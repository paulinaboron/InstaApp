package com.example.insta.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.insta.R;
import com.example.insta.databinding.FragmentGalleryBinding;
import com.example.insta.databinding.FragmentPhotoBinding;
import com.example.insta.helpers.Utils;
import com.example.insta.model.Photo;
import com.example.insta.viewModel.ProfileViewModel;


public class PhotoFragment extends Fragment {
    private String TAG = "xxx";
    private FragmentPhotoBinding binding;
    private ProfileViewModel profileViewModel;
    private Photo photo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhotoBinding.inflate(getLayoutInflater());
        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        photo = ProfileViewModel.selectedPhoto;
        Log.d(TAG, "onCreateView: " + photo.toString());
        Glide.with(getContext())
                .load(Utils.adres+"/api/photos/getfile/" + photo.getId())
                .into(binding.ivSelectedPhoto);

        return binding.getRoot();
    }
}