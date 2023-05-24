package com.example.insta.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.insta.helpers.Album;
import com.example.insta.model.Photo;
import com.example.insta.service.RetrofitService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PhotosViewModel extends ViewModel {
    private String TAG = "xxx";
    private List<Photo> photos = new ArrayList<>();
    private MutableLiveData<List<Photo>> photosLiveData = new MutableLiveData<>();

    public MutableLiveData<List<Photo>> getObservedPhotos(){
        return photosLiveData;
    }

    public void setupPhotos(){
        this.photosLiveData.setValue(this.photos);
    }

    public void getPhotos(String email){
        Album album = new Album(email);
        Call<List<Photo>> call = RetrofitService.getPhotosInterface().getPhotos(album);
        call.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, String.valueOf(response.code()));
                }
                else
                {
                    photos = response.body();
                    Log.d(TAG, "onResponse: " + photos.size());
                    photosLiveData.setValue(photos);
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}
