package com.example.practice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.practice.adapter.VerticalAdapter;
import com.example.practice.models.ArrayData;
import com.example.practice.models.HorizontalModel;
import com.example.practice.models.RetrofitModel;
import com.example.practice.models.VerticalModel;

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
    ArrayList<VerticalModel>verticalModelArrayList;
    VerticalModel verticalModel;
    Retrofit retrofit;
    ArrayList<RetrofitModel> arrayList = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vrcv=findViewById(R.id.recyclerview);
        verticalModelArrayList = new ArrayList<>();
        vrcv.setHasFixedSize(true);
        vrcv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        verticalAdapter=new VerticalAdapter(this,verticalModelArrayList);
        vrcv.setAdapter(verticalAdapter);
        //setData();
        fetchData();
    }

    private void fetchData() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);

        Retrofit.Builder build = new Retrofit.Builder()
                .baseUrl("https://d51md7wh0hu8b.cloudfront.net/")
                .addConverterFactory(GsonConverterFactory.create());

        retrofit = build.build();
        RetroApi retroApi = retrofit.create(RetroApi.class);
        Call<List<RetrofitModel>> call = retroApi.retrofitModel();
         call.enqueue(new Callback<List<RetrofitModel>>() {
             @Override
             public void onResponse(Call<List<RetrofitModel>> call, Response<List<RetrofitModel>> response) {
                 if (response.isSuccessful()){
                     ArrayList<HorizontalModel>horizontalModelArrayList=new ArrayList<>();
                     HorizontalModel mhorizontalModel=new HorizontalModel();
                  //  VerticalModel mverticalModel = null;

                     Log.e("Response",""+response.code());
                     //  RetrofitModel retrofitModel = response.body();

                     Log.e("Title1",""+response.body());
                     List<RetrofitModel> modelList = response.body();

                     for (RetrofitModel model:modelList){
                         Log.e("Title",""+model.getTitle());

                         List<ArrayData> data = model.getData();
                         for (ArrayData arrayData: data){
                             Log.e("Content",""+arrayData.getCat()+arrayData.getT()+arrayData.getpF());
                             mhorizontalModel.setName(""+arrayData.getCat());
                             mhorizontalModel.setImage(R.drawable.krishna);
                             horizontalModelArrayList.add(mhorizontalModel);
                           verticalModel=new VerticalModel(model.getTitle(),horizontalModelArrayList);
                             verticalModel.setTitle(model.getTitle());
                           verticalModel.setArrayList(horizontalModelArrayList);
                           verticalModelArrayList.add(verticalModel);

                         }
//                         mverticalModel.setArrayList(horizontalModelArrayList);
//                         verticalModelArrayList.add(mverticalModel);
                     }
                 }
             }

             @Override
             public void onFailure(Call<List<RetrofitModel>> call, Throwable t) {
                 Toast.makeText(MainActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                 Log.e("Failed",t.getMessage());
             }
         });
        verticalAdapter.notifyDataSetChanged();
    }

    private void setData() {
        for(int i=1;i<5;i++){
            //verticalModelArrayList.add(new VerticalModel("Title"+i,))
            ArrayList<HorizontalModel>horizontalModelArrayList=new ArrayList<>();

            VerticalModel mverticalModel=new VerticalModel("Title"+i,horizontalModelArrayList);
           // mverticalModel.setTitle("Title"+ i);
            for(int j=0;j<5;j++){
                HorizontalModel mhorizontalModel=new HorizontalModel();
                mhorizontalModel.setName("Desc"+j);
                mhorizontalModel.setImage(R.drawable.krishna);
                horizontalModelArrayList.add(mhorizontalModel);
            }

             mverticalModel.setArrayList(horizontalModelArrayList);
            verticalModelArrayList.add(mverticalModel);
        }
        verticalAdapter.notifyDataSetChanged();
    }
}