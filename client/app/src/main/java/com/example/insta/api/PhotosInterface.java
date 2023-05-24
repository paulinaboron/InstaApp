package com.example.insta.api;

import com.example.insta.helpers.Album;
import com.example.insta.model.Photo;
import com.example.insta.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface PhotosInterface {
    @POST("/api/photos/album")
    Call<List<Photo>> getPhotos(@Body Album album);
}
