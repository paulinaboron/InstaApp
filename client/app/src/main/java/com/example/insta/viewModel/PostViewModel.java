package com.example.insta.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.insta.model.Tag;
import com.example.insta.service.RetrofitService;
import com.example.insta.view.post.TagsFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    private String TAG = "xxx";
    public static File photoFile;
    public List<Tag> tags = new ArrayList<>();
    public ArrayList<Tag> selectedTags = new ArrayList<>();
    public String address;


    public void getTags(){
        Call<List<Tag>> call = RetrofitService.getTagsInterface().getTags();
        call.enqueue(new Callback<List<Tag>>() {
            @Override
            public void onResponse(Call<List<Tag>> call, Response<List<Tag>> response) {
                if (!response.isSuccessful() || response.body() == null)  {
                    Log.d(TAG, "not successful " + response.code());
                }
                else
                {
                    Log.d(TAG, "onResponse: " + response.body());
                    tags = response.body();
                    TagsFragment.displayTags(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Tag>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
