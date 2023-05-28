package com.example.insta.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
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
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(getLayoutInflater());

        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        profileViewModel.setup();
        profileViewModel.getObservedProfile().observe(getViewLifecycleOwner(), s ->{
            binding.setProfile(profileViewModel);
        });

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
        binding.recyclerView.setLayoutManager(manager);


        getParentFragmentManager()
                .setFragmentResultListener("datafromactivity", this, (s, bundle) -> {
                    token = bundle.getString("token");
                    profileViewModel.setToken(token);
                    profileViewModel.getProfile(token, binding.recyclerView);
                    profileViewModel.getProfilePicture(binding.ivProfilePic);
                });

        return binding.getRoot();
    }
}