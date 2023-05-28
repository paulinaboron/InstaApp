package com.example.insta.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.insta.R;
import com.example.insta.databinding.FragmentGalleryBinding;
import com.example.insta.helpers.Adapter;
import com.example.insta.model.Photo;
import com.example.insta.viewModel.PhotosViewModel;
import com.example.insta.viewModel.ProfileViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GalleryFragment extends Fragment {
    private String TAG = "xxx";
    private FragmentGalleryBinding binding;
    private ProfileViewModel profileViewModel;
    private PhotosViewModel photosViewModel;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(getLayoutInflater());

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.setupProfile();
        profileViewModel.getObservedProfile().observe(getViewLifecycleOwner(), s ->{
            binding.setProfile(profileViewModel);
        });

        photosViewModel = new ViewModelProvider(this).get(PhotosViewModel.class);
        photosViewModel.setupPhotos();

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
        binding.recyclerView.setLayoutManager(manager);


        getParentFragmentManager()
                .setFragmentResultListener("datafromactivity", this, (s, bundle) -> {
                    token = bundle.getString("token");
                    profileViewModel.getProfile(token);
                    profileViewModel.getProfilePicture(token);

                    photosViewModel.getPhotos("Email");
                    List<Photo> images = photosViewModel.getObservedPhotos().getValue();
                    Adapter adapter = new Adapter(photosViewModel.getObservedPhotos().getValue(), GalleryFragment.this);
                    binding.recyclerView.setAdapter(adapter);
                });

//        todo
        Glide.with(binding.ivProfilePic.getContext())
                .load("http://192.168.1.20:3000/api/photos/getfileURL/uploads/Email/upload_01d8fc45610db498920f5121201dd20d.jpg")
                .into(binding.ivProfilePic);

        return binding.getRoot();
    }
}