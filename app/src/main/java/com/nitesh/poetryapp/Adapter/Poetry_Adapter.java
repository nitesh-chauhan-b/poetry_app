package com.nitesh.poetryapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.nitesh.poetryapp.API.APIClient;
import com.nitesh.poetryapp.API.APIInterface;
import com.nitesh.poetryapp.Models.Poetry_Model;
import com.nitesh.poetryapp.R;
import com.nitesh.poetryapp.Response.DeletePoetry;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Poetry_Adapter extends RecyclerView.Adapter<Poetry_Adapter.ViewHolder>{
    Context context;
    List<Poetry_Model> list;
    APIInterface apiInterface;
    public Poetry_Adapter(Context context, List<Poetry_Model> list){
        this.context=context;
        this.list=list;

        Retrofit retrofit = APIClient.getRetrofit();
        apiInterface = retrofit.create(APIInterface.class);

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.poetry_style,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.poet_name.setText(list.get(position).getPoet_name());
        holder.poetry.setText(list.get(position).getPoetry_data());
        holder.time.setText(list.get(position).getTime());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deletepoetry(list.get(position).getId()+"",position);

            }
        });

        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String poetry_data = list.get(position).getPoetry_data();
                String poet_name = list.get(position).getPoet_name();
                int id = list.get(position).getId();
                Intent i = new Intent(context, com.nitesh.poetryapp.UpdatePoetry.class);
                i.putExtra("poetry_data",list.get(position).getPoetry_data());
                i.putExtra("poet_name",list.get(position).getPoet_name());
                i.putExtra("id",list.get(position).getId());

                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView poet_name,poetry,time;
        Button update,delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            poet_name = itemView.findViewById(R.id.poet_name);
            poetry = itemView.findViewById(R.id.poetry);
            time = itemView.findViewById(R.id.poetry_time);
            update = itemView.findViewById(R.id.update_btn);
            delete = itemView.findViewById(R.id.delete_btn);

        }
    }

    private void deletepoetry(String id, int position) {
        apiInterface.deletePoetry(id).enqueue(new Callback<DeletePoetry>() {
            @Override
            public void onResponse(Call<DeletePoetry> call, Response<DeletePoetry> response) {

                try{
                if(response!=null){

                    if(response.body().getStatus().equals("1")){
                        list.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
                }catch(Exception e)
                {
                    Log.e("exp",e.getLocalizedMessage());
                }

            }

            @Override
            public void onFailure(Call<DeletePoetry> call, Throwable t) {
                Log.e("failure",t.getLocalizedMessage());

            }
        });
    }





}
