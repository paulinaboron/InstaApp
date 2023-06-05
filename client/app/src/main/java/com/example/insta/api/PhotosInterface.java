package com.example.insta.api;

import com.example.insta.helpers.AddressRequest;
import com.example.insta.helpers.TagsRequest;
import com.example.insta.model.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PhotosInterface {
    @Multipart
    @POST("api/photos")
    Call<String> postPhoto(
            @Part MultipartBody.Part file,
            @Part("album") RequestBody album
            );

    @PATCH("api/photos/tags")
    Call<String> addTags(@Body TagsRequest tagsRequest);

    @PATCH("api/photos/address")
    Call<String> addAddress(@Body AddressRequest addressRequest);
}
