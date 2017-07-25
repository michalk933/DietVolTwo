package com.example.michal.dietvoltwo.service.diatetsService;


public class Cpm {

    public static int cpmCalculate(int weight, int ratioBmiAndLvlActivity) {
        return (weight * ratioBmiAndLvlActivity);
    }
}
