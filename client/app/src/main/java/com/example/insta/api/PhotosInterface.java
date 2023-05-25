package com.example.insta.api;

import com.example.insta.model.Photo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PhotosInterface {

    @GET("/api/photos/album/{album}")
    Call<List<Photo>> getPhotos(@Path("album") String album);
}
