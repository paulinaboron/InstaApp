package com.example.insta.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

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


}


