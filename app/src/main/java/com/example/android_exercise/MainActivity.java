package com.example.android_exercise;

import android.content.DialogInterface;
import android.provider.SyncStateContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android_exercise.adapter.RetrofitAdapter;
import com.example.android_exercise.model.ModelRecycler;
import com.example.android_exercise.model.Pozo;
import com.example.android_exercise.rest.RecyclerInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RetrofitAdapter retrofitAdapter;
    private RecyclerView recyclerView;

    String JSONURL="https://gist.githubusercontent.com/t-reed/739df99e9d96700f17604a3971e701fa/raw/1d4dd9c5a0ec758ff5ae92b7b13fe4d57d34e1dc/";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
//
     //

        fetchJSON();


    }

    private void fetchJSON() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JSONURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RecyclerInterface api = retrofit.create(RecyclerInterface.class);

        Call<ArrayList<Pozo>>call=api.getCakes();
        call.enqueue(new Callback<ArrayList<Pozo>>() {
            @Override
            public void onResponse(Call<ArrayList<Pozo>> call, Response<ArrayList<Pozo>> response) {
                if (response.isSuccessful()){
                    if (response.body() !=null){
                        writeRecycler(response.body());

                    }else {
                        Toast.makeText(getApplicationContext(),"error in loading",Toast.LENGTH_LONG).show();
                        Log.i("onEmptyResponse", "Returned empty response");

                    }
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Pozo>> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Please check the network connection",Toast.LENGTH_LONG).show();


            }
        });
    }
    private void writeRecycler(ArrayList<Pozo> response) {

        try {
            ArrayList<ModelRecycler> model = new ArrayList<>();
            //getting the whole json object from the response
            for (int i = 0; i < response.size(); i++) {
                ModelRecycler modelRecycler = new ModelRecycler();
                modelRecycler.setTitle(response.get(i).getTitle());
                modelRecycler.setDesc(response.get(i).getDesc());
                modelRecycler.setImage(response.get(i).getImage());
                model.add(modelRecycler);
            }
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            retrofitAdapter = new RetrofitAdapter(getApplicationContext(), model);
            recyclerView.setAdapter(retrofitAdapter);


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}