package com.example.insta.api;

import com.example.insta.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;

public interface ProfileInterface {
    @GET("/api/profile")
    Call<User> getProfile(@Header("Authorization") String token);

    @GET("/api/profile/picture")
    Call<ResponseBody> getProfilePicture(@Header("Authorization") String token);
}
