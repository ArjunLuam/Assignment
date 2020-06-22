package com.example.practice.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.R;
import com.example.practice.models.HorizontalModel;

import java.util.ArrayList;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder> {
    Context context;
    ArrayList<HorizontalModel>arrayList;
    //constructor of adapter class
    public HorizontalAdapter(Context context, ArrayList<HorizontalModel> arrayList){
        this.context=context;
        this.arrayList=arrayList;
    }

    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create view and inflate it
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {
        HorizontalModel horizontalModel=arrayList.get(position);
        holder.title.setText(horizontalModel.getName());
        holder.imgthmb.setImageResource(horizontalModel.getImage());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ImageView imgthmb;
        public HorizontalViewHolder(View itemView){
            super(itemView);
            title=itemView.findViewById(R.id.show_title);
            imgthmb=itemView.findViewById(R.id.thumbnail);
        }
    }
}
