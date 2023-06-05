package com.example.insta.view.post;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.insta.R;
import com.example.insta.databinding.FragmentTagsBinding;
import com.example.insta.databinding.FragmentUploadBinding;
import com.example.insta.model.Tag;
import com.example.insta.view.profile.ProfileActivity;
import com.example.insta.viewModel.PostViewModel;
import com.google.android.material.chip.Chip;


public class UploadFragment extends Fragment {
    private String TAG = "xxx";
    private static FragmentUploadBinding binding;
    private PostViewModel postViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentUploadBinding.inflate(getLayoutInflater());
        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);

        Bitmap bitmap = BitmapFactory.decodeFile(PostViewModel.photoFile.getAbsolutePath());
        binding.ivPhoto.setImageBitmap(bitmap);

        binding.tvAddress.setText(postViewModel.address);
        for(Tag t : postViewModel.selectedTags){
            Chip chip = (Chip) inflater.inflate(R.layout.chip, null, false);
            chip.setText(t.getName());
            binding.chipGroup.addView(chip);
        }

        binding.btnCancel.setOnClickListener(v->{
            goToProfileActivity();
        });

        binding.btnUpload.setOnClickListener(v->{
            PostViewModel.uploadFragment = this;
            postViewModel.uploadPhoto();

            goToProfileActivity();
        });

        return binding.getRoot();
    }

    public void goToProfileActivity(){
        Intent intent = new Intent(getContext(), ProfileActivity.class);
        startActivity(intent);
    }
}