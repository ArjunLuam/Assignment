package com.example.practice.models;

import java.util.ArrayList;

public class RetrofitModel {

    private String title;
    private ArrayList<ArrayData> data;

    public RetrofitModel(String title, ArrayList<ArrayData> data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        if(title.equals("")){
            return "NO TITLE";
        }
        else{
            return title;
        }
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<ArrayData> getData() {
        return data;
    }

    public void setData(ArrayList<ArrayData> data) {
        this.data = data;
    }
}
