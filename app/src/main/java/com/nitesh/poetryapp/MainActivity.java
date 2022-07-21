package com.nitesh.poetryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.Toast;

import com.nitesh.poetryapp.API.APIClient;
import com.nitesh.poetryapp.API.APIInterface;
import com.nitesh.poetryapp.Adapter.Poetry_Adapter;
import com.nitesh.poetryapp.Models.Poetry_Model;
import com.nitesh.poetryapp.Response.GetPoetryResponse;
import com.nitesh.poetryapp.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    Poetry_Adapter adapter;
    APIInterface apiInterface;
    Retrofit retrofit;
    List<Poetry_Model> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initialization();
        getData();
        setSupportActionBar(binding.toolbar);
    }

    @Override
    protected void onResume() {
        getData();
        super.onResume();
    }

    public void initialization() {
        retrofit = APIClient.getRetrofit();
        apiInterface = retrofit.create(APIInterface.class);
        setAdapter(list);

    }

    public void getData() {

        apiInterface.getPoetry().enqueue(new Callback<GetPoetryResponse>() {
            @Override
            public void onResponse(@NonNull Call<GetPoetryResponse> call, @NonNull Response<GetPoetryResponse> response) {

                try {
                    assert response.body() != null;
                    if (response.body().getStatus().equals("1")) {
                        setAdapter(response.body().getData());
                    } else {
                        Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Log.e("failure", e.getLocalizedMessage());
                }
            }

            @Override
            public void onFailure(@NonNull Call<GetPoetryResponse> call, @NonNull Throwable t) {
                Toast.makeText(MainActivity.this, "Status is 0 and Failure", Toast.LENGTH_SHORT).show();
                Log.e("failure", t.getLocalizedMessage());
            }
        });
    }

    public void setAdapter(List<Poetry_Model> list) {
        adapter = new Poetry_Adapter(MainActivity.this, list);
        LinearLayoutManager lm = new LinearLayoutManager(MainActivity.this);
        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(lm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tollbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.add_poetry:
                Intent i = new Intent(MainActivity.this, Add_poetry.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}