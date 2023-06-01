package com.example.insta.view;

import static android.app.Activity.RESULT_OK;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.insta.R;
import com.example.insta.databinding.FragmentEditBinding;
import com.example.insta.databinding.FragmentGalleryBinding;
import com.example.insta.databinding.FragmentLoginBinding;
import com.example.insta.helpers.Utils;
import com.example.insta.model.User;
import com.example.insta.viewModel.ProfileViewModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


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
        profileViewModel.getObservedProfile().observe(getViewLifecycleOwner(), s -> {
            binding.setProfile(profileViewModel);
        });

        token = profileViewModel.getToken();
        profileViewModel.getProfilePicture(binding.profilePic);

        binding.btnSave.setOnClickListener(v -> {
            String newName = String.valueOf(binding.editName.getText());
            String newLastname = String.valueOf(binding.editLastname.getText());

            User user = new User();
            user.setName(newName);
            user.setLastname(newLastname);
            profileViewModel.updateProfile(user, EditFragment.this);
        });

        binding.profilePic.setOnClickListener(v -> {
            selectProfilePic();
        });

        return binding.getRoot();
    }

    private void selectProfilePic() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 100);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK)
            if (requestCode == 100) {
                Uri selectedImageUri = data.getData();
                String picturePath = Utils.getPath( getActivity( ).getApplicationContext( ), selectedImageUri );
                Log.d(TAG, "Picture Path " + picturePath);
                File file = new File(picturePath);
//                File file = new File(data.toURI());
//                Log.d(TAG, "onActivityResult: " + file.getPath());
                profileViewModel.postProfilePic(file, getContext());
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), selectedImageUri);
                    binding.profilePic.setImageBitmap(bitmap);
                } catch (IOException e) {
                    Log.i(TAG, "Some exception " + e);
                }
            }
    }

}