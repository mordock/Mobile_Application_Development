package com.example.maxwe.recipeapp;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface RecipeApiService {
    String BASE_URL = "http://food2fork.com/api/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("search?key=bc7ac4ec5a11c70eac7b54f5bf980788&rId=35382&sort=r&count=3")
    Call<RecipeRespond> getRecipe();
}
