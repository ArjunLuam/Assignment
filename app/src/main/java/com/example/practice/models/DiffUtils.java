package com.example.practice.models;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.List;

public class DiffUtils extends DiffUtil.Callback {
    List<ArrayData> oldlist;
    List<ArrayData>newlist;

    public DiffUtils(List<ArrayData> arrayList, List<ArrayData> data) {
        this.oldlist=oldlist;
        this.newlist=newlist;
    }

    @Override
    public int getOldListSize() {
        return oldlist.size();
    }

    @Override
    public int getNewListSize() {
        return newlist.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldlist.get(oldItemPosition).getpF()==newlist.get(newItemPosition).getpF();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldlist.get(oldItemPosition).getT().equals(newlist.get(newItemPosition).getT());
    }

    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        return super.getChangePayload(oldItemPosition, newItemPosition);
    }
}
