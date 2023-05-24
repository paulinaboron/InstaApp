package com.example.insta.viewModel;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.insta.model.User;
import com.example.insta.service.RetrofitService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileViewModel extends ViewModel {
    private String TAG = "xxx";
    private User user = new User();
    private MutableLiveData<User> profileLiveData = new MutableLiveData<>();

    public MutableLiveData<User> getObservedProfile(){
        return profileLiveData;
    }

    public void setupProfile(){
        this.profileLiveData.setValue(this.user);
    }

    public void getProfile(String token){
        Call<User> call = RetrofitService.getProfileInterface().getProfile("Bearer " + token);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, String.valueOf(response.code()));
                }
                else
                {
                    user = response.body();
                    Log.d("xxx", "onResponse: " + user.toString());
                    profileLiveData.setValue(user);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });

    }

    public void getProfilePicture(String token){
        Call<ResponseBody> call = RetrofitService.getProfileInterface().getProfilePicture("Bearer " + token);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, String.valueOf(response.code()));
                }
                else
                {
                    Bitmap bmp = BitmapFactory.decodeStream(response.body().byteStream());
                    user.setProfilePicture(bmp);
                    profileLiveData.setValue(user);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}
