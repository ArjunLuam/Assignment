package com.example.practice.adapter;

import android.content.Context;
import android.service.autofill.Dataset;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.practice.R;
import com.example.practice.models.ArrayData;
import com.example.practice.models.DiffUtils;

import java.util.ArrayList;
import java.util.List;

public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.HorizontalViewHolder> {
    Context context;
    List<ArrayData> arrayList;


    //constructor of adapter class
    public HorizontalAdapter(Context context, ArrayList<ArrayData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }


    public void diffUtilsChangeData(List<ArrayData> data){
        DiffUtils diffUtils=new DiffUtils(this.arrayList,data);
        DiffUtil.DiffResult diffResult=DiffUtil.calculateDiff(diffUtils);
        this.arrayList.clear();
        this.arrayList.addAll(arrayList);
        diffResult.dispatchUpdatesTo(this);
    }


    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //create view and inflate it
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item, parent, false);
        return new HorizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {
        //setting up UI and adding functionality to it

        ArrayData horizontalModel = arrayList.get(position);
        holder.title.setText(horizontalModel.getT());
        Glide.with(context).load(horizontalModel.getpF()).into(holder.imgthmb);
        Glide.with(context).load("https://bolkar.s3.ap-south-1.amazonaws.com/demo/play_new.webp" ).into(holder.playicon);
        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               arrayList.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, arrayList.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class HorizontalViewHolder extends RecyclerView.ViewHolder  {

        //Declaring the views

        TextView title;
        ImageView imgthmb;
        ImageView playicon;
        CardView card;
        public HorizontalViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.show_title);
            imgthmb = itemView.findViewById(R.id.thumbnail);
            playicon=itemView.findViewById(R.id.play);
            card=itemView.findViewById(R.id.row_card);

        }

    }
}
