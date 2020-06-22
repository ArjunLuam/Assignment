package com.example.practice;

import com.example.practice.models.RetrofitModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetroApi {

    @GET("/android/v1/prod/Radio/hi/show.json")
    Call<List<RetrofitModel>> retrofitModel();

}
