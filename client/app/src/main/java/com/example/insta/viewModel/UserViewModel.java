package com.example.insta.viewModel;

import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.ViewModel;

import com.example.insta.model.User;
import com.example.insta.service.RetrofitService;
import com.example.insta.view.LoginFragment;
import com.example.insta.view.RegisterFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserViewModel extends ViewModel {
    public String TAG = "xxx";
    public UserViewModel() {
    }

    public void register(User user, RegisterFragment fragment){
        Call<String> call = RetrofitService.getUserInterface().register(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, String.valueOf(response.code()));
                }
                else
                {
                    String body = response.body();
                    Log.d("xxx", "onResponse: " + body);
                    fragment.displayToken(body);
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure: error " + t.getMessage());
            }
        });
    }

    public void confirm(String token, RegisterFragment fragment){
        Call<String> call = RetrofitService.getUserInterface().confirm(token);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, String.valueOf(response.code()));
                }
                else
                {
                    String body = response.body();
                    Log.d(TAG, "onResponse: " + body);
                    Toast.makeText(fragment.getContext(),body,Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    public void login(User user, LoginFragment fragment){
        Call<String> call = RetrofitService.getUserInterface().login(user);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (!response.isSuccessful()) {
                    Log.d(TAG, String.valueOf(response.code()));
                }
                else
                {
                    Log.d(TAG, "onResponse: " + response.body());
                    fragment.loginResponce(response.body());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

}
