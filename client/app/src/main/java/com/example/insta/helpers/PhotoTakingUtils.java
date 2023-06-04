package com.example.insta.helpers;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.VideoCapture;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.insta.R;
import com.example.insta.view.post.CameraFragment;
import com.example.insta.view.post.TagsFragment;
import com.example.insta.viewModel.PostViewModel;

import java.io.File;

public class PhotoTakingUtils {

    private static String TAG = "xxx";

    public static void takePhoto(Context context, ImageCapture imageCapture, CameraFragment cameraFragment){
        File dir = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "InstaPosts");
        boolean isDirectoryCreated = dir.exists() || dir.mkdirs();

        if (isDirectoryCreated) {
            File file = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + "/InstaPosts/", "photo" + System.currentTimeMillis() +".jpg");
            ImageCapture.OutputFileOptions outputFileOptions = new ImageCapture.OutputFileOptions.Builder(file).build();

            imageCapture.takePicture(outputFileOptions,
                    ContextCompat.getMainExecutor(context),
                    new ImageCapture.OnImageSavedCallback() {

                        @Override
                        public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                            Toast.makeText(context, "Photo taken", Toast.LENGTH_SHORT).show();
                            Log.d(TAG, outputFileOptions.toString());

                            AppCompatActivity activity = (AppCompatActivity) context;
                            PostViewModel.photoFile = file;
                            activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new TagsFragment()).addToBackStack(null).commit();
                        }

                        @Override
                        public void onError(@NonNull ImageCaptureException exception) {
                            Log.d(TAG, "onError: error");
                        }
                    });
        }
    }



    @SuppressLint("RestrictedApi")
    public static void recordVideo(ContentValues contentValues, Context context, VideoCapture videoCapture) {
        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        videoCapture.startRecording(
                new VideoCapture.OutputFileOptions.Builder(
                        context.getContentResolver(),
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                        contentValues
                ).build(),
                ContextCompat.getMainExecutor(context),
                new VideoCapture.OnVideoSavedCallback() {
                    @Override
                    public void onVideoSaved(@NonNull VideoCapture.OutputFileResults outputFileResults) {
                        Toast.makeText(context, "Video saved", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(int videoCaptureError, @NonNull String message, @Nullable Throwable cause) {
                        Log.d(TAG, "onError: error");
                    }
                });
    }
    }
