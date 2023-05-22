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

import com.example.insta.R;
import com.example.insta.databinding.FragmentGalleryBinding;
import com.example.insta.helpers.Adapter;
import com.example.insta.viewModel.ProfileViewModel;

import java.util.ArrayList;
import java.util.Arrays;

public class GalleryFragment extends Fragment {
    private String TAG = "xxx";
    private FragmentGalleryBinding binding;
    private ProfileViewModel profileViewModel;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGalleryBinding.inflate(getLayoutInflater());

        getParentFragmentManager()
                .setFragmentResultListener("datafromactivity", this, (s, bundle) -> {
                    token = bundle.getString("token");
                    profileViewModel.getProfile(token);
                });

        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.setupPerson();
        profileViewModel.getObservedProfile().observe(getViewLifecycleOwner(), s ->{
            binding.setProfile(profileViewModel);
        });

        ArrayList images = new ArrayList<>(Arrays.asList(
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_foreground
                ));

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
        binding.recyclerView.setLayoutManager(manager);
        Adapter adapter = new Adapter(images, GalleryFragment.this);
        binding.recyclerView.setAdapter(adapter);


        return binding.getRoot();
    }
}