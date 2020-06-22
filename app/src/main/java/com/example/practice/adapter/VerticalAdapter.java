package com.example.practice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.models.HorizontalModel;
import com.example.practice.models.VerticalModel;

import java.util.ArrayList;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.VerticalViewHolder> {

    Context context;
    ArrayList<VerticalModel>arrayList;

    //Constructor of vertical adapter class

    public VerticalAdapter(Context context,ArrayList<VerticalModel> arrayList){
        this.arrayList=arrayList;
        this.context=context;
    }


    @NonNull
    @Override
    public VerticalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating layout
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical,parent,false);
        return new VerticalViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull VerticalViewHolder holder, int position) {
        VerticalModel verticalModel=arrayList.get(position);
        String title=verticalModel.getTitle();
        ArrayList<HorizontalModel>singleitem=verticalModel.getArrayList();
        holder.textView.setText(title);
        HorizontalAdapter horizontalAdapter=new HorizontalAdapter(context,singleitem);
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView.setAdapter(horizontalAdapter);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class VerticalViewHolder extends RecyclerView.ViewHolder{

        RecyclerView recyclerView;
        TextView textView;
        public VerticalViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.rv1);
            textView=itemView.findViewById(R.id.txttitle1);
        }
    }
}
