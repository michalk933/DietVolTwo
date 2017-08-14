package com.example.michal.dietvoltwo.dto;



import io.realm.RealmObject;

public class BtwDto extends RealmObject{

    private int b;
    private int w;
    private int t;

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }
}
