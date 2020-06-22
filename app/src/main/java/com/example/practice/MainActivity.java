package com.example.practice;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practice.adapter.VerticalAdapter;
import com.example.practice.models.ArrayData;
import com.example.practice.models.RetrofitModel;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    RecyclerView vrcv;
    VerticalAdapter verticalAdapter;
    List<RetrofitModel> verticalModelArrayList;
    RetrofitModel verticalModel;
    Retrofit retrofit;
    ArrayList<RetrofitModel> arrayList = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vrcv = findViewById(R.id.recyclerview);
        fetchData();
    }


    //Fetching data from retrofit
    private void fetchData() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);



    //Retrofit client instance
        Retrofit.Builder build = new Retrofit.Builder()
                .baseUrl("https://d51md7wh0hu8b.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = build.build();



        //Api call
        RetroApi retroApi = retrofit.create(RetroApi.class);
        Call<List<RetrofitModel>> call = retroApi.retrofitModel();
        call.enqueue(new Callback<List<RetrofitModel>>() {
            @Override
            public void onResponse(Call<List<RetrofitModel>> call, Response<List<RetrofitModel>> response) {
                if (response.isSuccessful()) {

                    verticalModelArrayList=response.body();
                    Log.e("sizee",String.valueOf(verticalModelArrayList.size()));
                    vrcv.setHasFixedSize(true);
                    verticalAdapter = new VerticalAdapter(MainActivity.this, verticalModelArrayList);
                    vrcv.setAdapter(verticalAdapter);
                    vrcv.setLayoutManager(new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false));
                }

            }

            @Override
            public void onFailure(Call<List<RetrofitModel>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Failed", t.getMessage());
            }
        });
    }

}