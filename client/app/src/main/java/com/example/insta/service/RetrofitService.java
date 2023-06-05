package com.example.insta.service;

import com.example.insta.api.PhotosInterface;
import com.example.insta.api.ProfileInterface;
import com.example.insta.api.TagsInterface;
import com.example.insta.api.UserInterface;
import com.example.insta.helpers.Utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Utils.adres)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static UserInterface getUserInterface(){
        return retrofit.create(UserInterface.class);
    }

    public static ProfileInterface getProfileInterface(){
        return retrofit.create(ProfileInterface.class);
    }

    public static TagsInterface getTagsInterface(){
        return retrofit.create(TagsInterface.class);
    }

    public static PhotosInterface getPhotosInterface(){
        return retrofit.create(PhotosInterface.class);
    }
}
