package com.example.michal.dietvoltwo.dto;


import io.realm.RealmObject;

public class MealDto extends RealmObject {

    private int numberMeal;
    private int b;
    private int t;
    private int w;
    private int kcalForMeal;

    public int getKcalForMeal() {
        return kcalForMeal;
    }

    public void setKcalForMeal(int kcalForMeal) {
        this.kcalForMeal = kcalForMeal;
    }

    public int getNumberMeal() {
        return numberMeal;
    }

    public void setNumberMeal(int numberMeal) {
        this.numberMeal = numberMeal;
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
}
