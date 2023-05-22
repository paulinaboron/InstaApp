package com.example.insta.api;

import com.example.insta.model.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserInterface {

    @POST("/api/users/register")
    Call<String> register(@Body User user);

    @GET("/api/users/confirm/{token}")
    Call<String> confirm(@Path("token") String token);

    @POST("/api/users/login")
    Call<String> login(@Body User user);
}
