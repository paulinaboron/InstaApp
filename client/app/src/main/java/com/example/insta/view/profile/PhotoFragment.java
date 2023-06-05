package com.example.insta.view.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.insta.R;
import com.example.insta.databinding.FragmentPhotoBinding;
import com.example.insta.helpers.Utils;
import com.example.insta.model.Photo;
import com.example.insta.model.Tag;
import com.example.insta.viewModel.ProfileViewModel;
import com.google.android.material.chip.Chip;


public class PhotoFragment extends Fragment {
    private String TAG = "xxx";
    private FragmentPhotoBinding binding;
    private Photo photo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentPhotoBinding.inflate(getLayoutInflater());
        photo = ProfileViewModel.selectedPhoto;
        Log.d(TAG, "onCreateView: " + photo.toString());
        Glide.with(getContext())
                .load(Utils.adres+"/api/photos/getfile/" + photo.getId())
                .into(binding.ivSelectedPhoto);

        binding.tvAddress.setText(photo.getAddress());

        for(Tag t : photo.getTags()){
            Chip chip = (Chip) inflater.inflate(R.layout.chip, null, false);
            chip.setText(t.getName());
            binding.chipGroup.addView(chip);
        }

        return binding.getRoot();
    }
}