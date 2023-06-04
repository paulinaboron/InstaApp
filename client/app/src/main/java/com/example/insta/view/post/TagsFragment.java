package com.example.insta.view.post;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.insta.R;
import com.example.insta.databinding.FragmentTagsBinding;
import com.example.insta.model.Tag;
import com.example.insta.viewModel.PostViewModel;
import com.google.android.material.chip.Chip;

import java.util.List;


public class TagsFragment extends Fragment {
    private String TAG = "xxx";
    private static FragmentTagsBinding binding;
    private PostViewModel postViewModel;
    private static LayoutInflater lInflater;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTagsBinding.inflate(getLayoutInflater());
        postViewModel = new ViewModelProvider(requireActivity()).get(PostViewModel.class);
        lInflater = inflater;
        Bitmap bitmap = BitmapFactory.decodeFile(PostViewModel.photoFile.getAbsolutePath());
        binding.ivPhoto.setImageBitmap(bitmap);

        postViewModel.getTags();

        binding.btnNext.setOnClickListener(v->{
            Log.d(TAG, "onCreateView: " + binding.chipGroup.getCheckedChipIds());
            for(int i : binding.chipGroup.getCheckedChipIds()){
                postViewModel.selectedTags.add(postViewModel.tags.get(i-1));
            }
            Log.d(TAG, String.valueOf(postViewModel.selectedTags));

            AppCompatActivity activity = (AppCompatActivity) getActivity();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new MapFragment()).addToBackStack(null).commit();

        });


        return binding.getRoot();
    }

    public static void displayTags(List<Tag> tags){
        for(Tag t : tags){
            Chip chip = (Chip) lInflater.inflate(R.layout.chip, null, false);
            chip.setText(t.getName());
            binding.chipGroup.addView(chip);
        }
    }
}