package com.example.insta.view.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.insta.databinding.FragmentGalleryBinding;
import com.example.insta.viewModel.ProfileViewModel;

public class GalleryFragment extends Fragment {
    private String TAG = "xxx";
    private FragmentGalleryBinding binding;
    private ProfileViewModel profileViewModel;

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

                    profileViewModel.getProfile(binding.recyclerView);
                    profileViewModel.getProfilePicture(binding.ivProfilePic);

        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");

        profileViewModel.getProfile(binding.recyclerView);
        profileViewModel.getProfilePicture(binding.ivProfilePic);
    }

}