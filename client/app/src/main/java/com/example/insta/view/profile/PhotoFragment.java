package com.example.insta.view.profile;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;

import com.bumptech.glide.Glide;
import com.example.insta.R;
import com.example.insta.databinding.FragmentPhotoBinding;
import com.example.insta.helpers.Utils;
import com.example.insta.model.Photo;
import com.example.insta.model.Tag;
import com.example.insta.viewModel.ProfileViewModel;
import com.google.android.material.chip.Chip;

import java.util.Arrays;
import java.util.Objects;


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
        Log.d(TAG, "onCreateView: " + photo.getUrl());
        Log.d(TAG, "onCreateView: " + Arrays.toString(photo.getUrl().split("\\.")));
        String format = photo.getUrl().split("\\.")[1];
        Log.d(TAG, "onCreateView: format " + format);

        if(Objects.equals(format, "mp4")){
            Log.d(TAG, "onCreateView: format mp4 444444444444444444");
            Uri uri = Uri.parse(Utils.adres+"/api/photos/getVideo/" + photo.getId());
//            binding.vvSelectedVideo.setMediaController(new MediaController(getContext()));
//            binding.vvSelectedVideo.setVideoURI(uri);
//            binding.vvSelectedVideo.requestFocus();
//            binding.vvSelectedVideo.start();

            try {
                // Start the MediaController
                MediaController mediacontroller = new MediaController(getContext());
                mediacontroller.setAnchorView(binding.vvSelectedVideo);
                // Get the URL from String videoUrl
                binding.vvSelectedVideo.setMediaController(mediacontroller);
                binding.vvSelectedVideo.setVideoURI(uri);

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }

            binding.vvSelectedVideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    binding.vvSelectedVideo.start();
                }
            });

        }else{
            Glide.with(getContext())
                    .load(Utils.adres+"/api/photos/getFile/" + photo.getId())
                    .into(binding.ivSelectedPhoto);
        }


        binding.tvAddress.setText(photo.getAddress());

        for(Tag t : photo.getTags()){
            Chip chip = (Chip) inflater.inflate(R.layout.chip, null, false);
            chip.setText(t.getName());
            binding.chipGroup.addView(chip);
        }

        return binding.getRoot();
    }
}