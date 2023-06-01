package com.example.insta.api;

import com.example.insta.model.Photo;
import com.example.insta.model.User;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ProfileInterface {
    @GET("/api/profile")
    Call<User> getProfile(@Header("Authorization") String token);

    @GET("/api/profile/picture")
    Call<ResponseBody> getProfilePicture(@Header("Authorization") String token);

    @GET("/api/profile/album/{album}")
    Call<List<Photo>> getProfilesPhotos(@Path("album") String album);

    @PATCH("/api/profile")
    Call<String> UpdateProfile(@Header("Authorization") String token, @Body User user);

    @Multipart
    @POST("api/profile")
    Call<String> postProfilePic(
            @Header("Authorization") String token,
            @Part MultipartBody.Part file
    );
}
