package com.example.insta.viewModel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.example.insta.helpers.Adapter;
import com.example.insta.model.Photo;
import com.example.insta.model.User;
import com.example.insta.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {
    private String TAG = "xxx";
    private User user = new User();
    private MutableLiveData<User> profileLiveData = new MutableLiveData<>();
    private List<Photo> photos = new ArrayList<>();
    private MutableLiveData<List<Photo>> photosLiveData = new MutableLiveData<>();

    public MutableLiveData<User> getObservedProfile(){
        return profileLiveData;
    }
    public MutableLiveData<List<Photo>> getObservedPhotos(){
        return photosLiveData;
    }

    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setup(){
        this.profileLiveData.setValue(this.user);
        this.photosLiveData.setValue(this.photos);
    }

    public void getProfile(String token, RecyclerView recyclerView){
        Call<User> call = RetrofitService.getProfileInterface().getProfile("Bearer " + token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, String.valueOf(response.code()));
                }
                else
                {
                    user = response.body();
                    Log.d("xxx", "onResponse: " + user.toString());
                    profileLiveData.setValue(user);
                    getProfilesPhotos(user.getEmail(), recyclerView);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    public void getProfilesPhotos(String email, RecyclerView recyclerView){
        Log.d(TAG, "getPhotos: email " + email);
        Call<List<Photo>> call = RetrofitService.getProfileInterface().getProfilesPhotos(email);
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, String.valueOf(response.code()));
                }
                else
                {
                    photos = response.body();
                    photosLiveData.setValue(photos);
                    Log.d(TAG, "onResponse: " + photos.size());
                    Log.d(TAG, "onResponse: " + photos.toString());

                    Adapter adapter = new Adapter(photos);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void getProfilePicture(ImageView ivProfilePic){

        String url = "http://192.168.1.20:3000/api/profile/picture";

        GlideUrl glideUrl = new GlideUrl(url,
                new LazyHeaders.Builder()
                        .addHeader("Authorization", "Bearer " + token)
                        .build());

        Glide.with(ivProfilePic.getContext())
                .load(glideUrl)
                .into(ivProfilePic);
    }

    public void updateProfile(User newUser, Fragment fragment){
        Call<String> call = RetrofitService.getProfileInterface().UpdateProfile("Bearer " + token, newUser);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, String.valueOf(response.code()));
                }
                else
                {
                    String body = response.body();
                    Log.d(TAG, "onResponse: " + body);
                    user.setName(newUser.getName());
                    user.setLastname(newUser.getLastname());
                    profileLiveData.setValue(user);
                    Toast.makeText(fragment.getContext(),body,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }


}
