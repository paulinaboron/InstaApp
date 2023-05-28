package com.example.insta.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.insta.R;
import com.example.insta.databinding.FragmentEditBinding;
import com.example.insta.databinding.FragmentGalleryBinding;
import com.example.insta.databinding.FragmentLoginBinding;
import com.example.insta.model.User;
import com.example.insta.viewModel.ProfileViewModel;


public class EditFragment extends Fragment {
    private String TAG = "xxx";
    private FragmentEditBinding binding;
    private ProfileViewModel profileViewModel;
    private String token;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEditBinding.inflate(getLayoutInflater());

        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);
        profileViewModel.getObservedProfile().observe(getViewLifecycleOwner(), s ->{
            binding.setProfile(profileViewModel);
        });

        token = profileViewModel.getToken();
        profileViewModel.getProfilePicture(binding.profilePic);

        binding.btnSave.setOnClickListener(v->{
            String newName = String.valueOf(binding.editName.getText());
            String newLastname = String.valueOf(binding.editLastname.getText());

            User user = new User();
            user.setName(newName);
            user.setLastname(newLastname);
            profileViewModel.updateProfile(user, EditFragment.this);
        });

        return binding.getRoot();
    }
}