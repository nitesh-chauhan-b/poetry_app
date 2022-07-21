package com.nitesh.poetryapp.API;

import com.nitesh.poetryapp.Response.AddPoetry;
import com.nitesh.poetryapp.Response.DeletePoetry;
import com.nitesh.poetryapp.Response.GetPoetryResponse;
import com.nitesh.poetryapp.Response.updatePoetry;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {
    @GET("read_poetry.php")
    Call<GetPoetryResponse> getPoetry();

    @FormUrlEncoded
    @POST("delete_poetry.php")
    Call<DeletePoetry> deletePoetry(@Field("poetry_id")String poetry_id);

    @FormUrlEncoded
    @POST("add_poetry.php")
    Call<AddPoetry> addPoetry(@Field("poetry")String poetry,@Field("poet_name")String poet_name);

    @FormUrlEncoded
    @POST("updatepoetry.php")
    Call<updatePoetry> updatePoetry(@Field("poetry_data")String poetry_data, @Field("poet_name") String poet_name, @Field("id")String id);


}
