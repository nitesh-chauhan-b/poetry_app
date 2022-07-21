package com.nitesh.poetryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.nitesh.poetryapp.API.APIClient;
import com.nitesh.poetryapp.API.APIInterface;
import com.nitesh.poetryapp.Response.updatePoetry;
import com.nitesh.poetryapp.databinding.ActivityUpdatePoetryBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UpdatePoetry extends AppCompatActivity {
    ActivityUpdatePoetryBinding binding;
    APIInterface apiInterface;
    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdatePoetryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initialization();

        Intent i = getIntent();
        String poetry_data = i.getStringExtra("poetry_data");
        String poet_name = i.getStringExtra("poet_name");
        int id = i.getIntExtra("id", 0);

        binding.updatingPoetry.setText(poetry_data);
        binding.updatingPoetryAuthor.setText(poet_name);

        binding.UpdatePoetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String p_data = binding.updatingPoetry.getText().toString();
                String p_author = binding.updatingPoetryAuthor.getText().toString();

                if (p_data.equals("")) {
                    binding.updatingPoetry.setError("Field is Empty");
                } else {
                    if (p_author.equals("")) {
                        binding.updatingPoetryAuthor.setError("Field is Empty");
                    } else {
                        updatePoetry(p_data, p_author, String.valueOf(id));
                        finish();
                    }
                }
            }
        });


    }

    private void initialization() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        retrofit = APIClient.getRetrofit();
        apiInterface = retrofit.create(APIInterface.class);

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void updatePoetry(String poetry_data, String poet_name, String id) {
        apiInterface.updatePoetry(poetry_data, poet_name, id).enqueue(new Callback<updatePoetry>() {
            @Override
            public void onResponse(Call<updatePoetry> call, Response<updatePoetry> response) {
                try {
                    if (response != null) {
                        assert response.body() != null;
                        if (response.body().getStatus().equals("1")) {
                            Toast.makeText(UpdatePoetry.this, "Poetry Updated Successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(UpdatePoetry.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception e) {
                    Log.e("exp", e.getLocalizedMessage());
                }

            }

            @Override
            public void onFailure(Call<updatePoetry> call, Throwable t) {
                Log.e("exp", t.getLocalizedMessage());
            }
        });
    }
}