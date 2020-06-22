package com.example.practice.models;

public class ArrayData {

    private String t;
    private String cat;
    private String pF;

    public ArrayData(String t, String cat, String pF) {
        this.t = t;
        this.cat = cat;
        this.pF = pF;
    }

    public String getT() {
        return t;
    }

    public void setT(String t) {
        this.t = t;
    }

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getpF() {
        return pF;
    }

    public void setpF(String pF) {
        this.pF = pF;
    }
}
