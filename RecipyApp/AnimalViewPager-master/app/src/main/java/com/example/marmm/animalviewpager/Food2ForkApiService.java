package com.example.marmm.animalviewpager;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Food2ForkApiService {
    String BASE_URL = "http://food2fork.com/api/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    //the recipy id's to use
    //35382, 47024, 47319

    @GET("get?key=bc7ac4ec5a11c70eac7b54f5bf980788&rId={recipyID}")
    Call<Recipy> getRecipy(@Query("recipyID") int recipyID);

    @GET
    Call<Recipe_Response> getsearchList(@Url String url);
}
