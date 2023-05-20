package com.example.insta.service;

import com.example.insta.api.UserInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private static String BASE_URL = "http://192.168.119.103:3000"; //192.168.119.103  192.168.1.20
    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static UserInterface getUserInterface(){
        return retrofit.create(UserInterface.class);
    }
}
