package com.example.insta.api;

import com.example.insta.model.Photo;
import com.example.insta.model.Tag;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TagsInterface {
    @GET("/api/tags")
    Call<List<Tag>> getTags();
}
