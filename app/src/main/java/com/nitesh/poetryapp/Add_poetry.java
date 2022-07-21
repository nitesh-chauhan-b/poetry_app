package com.nitesh.poetryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nitesh.poetryapp.API.APIClient;
import com.nitesh.poetryapp.API.APIInterface;
import com.nitesh.poetryapp.Response.AddPoetry;
import com.nitesh.poetryapp.databinding.ActivityAddPoetryBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Add_poetry extends AppCompatActivity {
    ActivityAddPoetryBinding binding;
    APIInterface apiInterface;
    Retrofit retrofit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPoetryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialization();

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        binding.addPoetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String poetry_data = binding.addingPoetry.getText().toString();
                String poetry_author = binding.addingPoetryAuthor.getText().toString();

                if(poetry_data.equals(""))
                {
                    binding.addingPoetry.setError("Field is empty");
                }
                else{
                    if(poetry_author.equals("")){
                        binding.addingPoetryAuthor.setError("Field is empty");
                    }
                    else{
                        add_poetry_data(poetry_data,poetry_author);
                    }
                }

            }
        });

    }

    private void initialization(){
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        retrofit = APIClient.getRetrofit();
        apiInterface = retrofit.create(APIInterface.class);
    }

    private void add_poetry_data(String poetry_data,String poetry_author){

        apiInterface.addPoetry(poetry_data,poetry_author).enqueue(new Callback<AddPoetry>() {
            @Override
            public void onResponse(Call<AddPoetry> call, Response<AddPoetry> response) {
                try{
                    if(response!=null)
                    {
                        if(response.body().getStatus().equals("1")){
                            Toast.makeText(Add_poetry.this, "Poetry Added Successfully!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                        else{
                            Toast.makeText(Add_poetry.this,"Poetry is not added successfully!",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                catch (Exception e){

                    Log.e("exception",e.getLocalizedMessage());
                }

            }

            @Override
            public void onFailure(Call<AddPoetry> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());
            }
        });
    }



}