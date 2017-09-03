package com.example.michal.dietvoltwo.dto;


import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class ProductDto extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String producent;
    private String productTyp;
    private String timeOfDay;
    private int kcal;
    private int b;
    private int t;
    private int w;
    private int wW;
    private int ig;
    private int forDiabets;
    private Date create;
    private byte[] image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProducent() {
        return producent;
    }

    public void setProducent(String producent) {
        this.producent = producent;
    }

    public String getProductTyp() {
        return productTyp;
    }

    public void setProductTyp(String productTyp) {
        this.productTyp = productTyp;
    }

    public String getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(String timeOfDay) {
        this.timeOfDay = timeOfDay;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getT() {
        return t;
    }

    public void setT(int t) {
        this.t = t;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public int getwW() {
        return wW;
    }

    public void setwW(int wW) {
        this.wW = wW;
    }

    public int getIg() {
        return ig;
    }

    public void setIg(int ig) {
        this.ig = ig;
    }

    public int getForDiabets() {
        return forDiabets;
    }

    public void setForDiabets(int forDiabets) {
        this.forDiabets = forDiabets;
    }

    public Date getCreate() {
        return create;
    }

    public void setCreate(Date create) {
        this.create = create;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
