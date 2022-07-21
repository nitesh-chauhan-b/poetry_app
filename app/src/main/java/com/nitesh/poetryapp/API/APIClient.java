package com.nitesh.poetryapp.API;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    public static Retrofit retrofit=null;
    public static final String BASE_URL="http://10.0.2.2/poetry_app/";

    public static Retrofit getRetrofit() {

        if(retrofit==null)
        {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
            Gson gson = new GsonBuilder().create();
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson)).build();
        }

        return retrofit;
    }

    public static void setRetrofit(Retrofit retrofit) {
        APIClient.retrofit = retrofit;
    }
}
