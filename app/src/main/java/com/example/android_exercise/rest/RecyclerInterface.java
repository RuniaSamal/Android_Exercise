package com.example.android_exercise.rest;

import com.example.android_exercise.model.Pozo;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecyclerInterface {
   // String JSONURL="https://gist.githubusercontent.com/t-reed/739df99e9d96700f17604a3971e701fa/raw/1d4dd9c5a0ec758ff5ae92b7b13fe4d57d34e1dc";

    @GET("waracle_cake-android-client")
  Call<ArrayList<Pozo>>getCakes();
  //  Call<String> getString();
}
