package com.example.insta.viewModel;

import static com.example.insta.helpers.Utils.album;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.insta.helpers.AddressRequest;
import com.example.insta.helpers.TagsRequest;
import com.example.insta.helpers.Utils;
import com.example.insta.model.Tag;
import com.example.insta.service.RetrofitService;
import com.example.insta.view.post.TagsFragment;
import com.example.insta.view.post.UploadFragment;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostViewModel extends ViewModel {
    private String TAG = "xxx";
    public static File photoFile;
    public List<Tag> tags = new ArrayList<>();
    public ArrayList<Tag> selectedTags = new ArrayList<>();
    public String address;
    private String photoId;
    public static UploadFragment uploadFragment;


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
                Log.d(TAG, "onFailure 1: " + t.getMessage());
            }
        });
    }

    public void uploadPhoto(){
        RequestBody fileRequest = RequestBody.create(MediaType.parse("multipart/form-data"), photoFile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", photoFile.getName(), fileRequest);
        RequestBody album = RequestBody.create(MultipartBody.FORM, Utils.album);

        Call<String> call = RetrofitService.getPhotosInterface().postPhoto(body, album);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful() || response.body() == null || response.body() == "not found")  {
                    Log.d(TAG, "not successful " + response.code());
                }
                else
                {
                    Log.d(TAG, "onResponse: " + response.body());
                    photoId = response.body();
                    addTags();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure 2: " + t.getMessage());
            }
        });

    }

    private void addTags(){
        TagsRequest tagsRequest = new TagsRequest(photoId, selectedTags);
        Call<String> call = RetrofitService.getPhotosInterface().addTags(tagsRequest);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse: " + response.body());
                addAddress();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure 3: " + t.getMessage());
            }
        });
    }

    private void addAddress(){
        AddressRequest addressRequest = new AddressRequest(photoId, address);
        Call<String> call = RetrofitService.getPhotosInterface().addAddress(addressRequest);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.d(TAG, "onResponse: " + response.body());
                uploadFragment.goToProfileActivity();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure 4: " + t.getMessage());
            }
        });

    }
}
