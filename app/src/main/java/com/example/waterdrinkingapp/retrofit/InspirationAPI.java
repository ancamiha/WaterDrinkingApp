package com.example.waterdrinkingapp.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InspirationAPI {

    @GET("/")
    Call<Post> getPost();
}
