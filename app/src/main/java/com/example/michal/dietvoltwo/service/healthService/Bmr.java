package com.example.michal.dietvoltwo.service.healthService;


public class Bmr {

    public static int BMRCalculat(char sex, int age, int weight, int height) {

        return (int) (sex == 'M' ?
                66.47 + ((13.75 * weight) + (5 * height) - (6.75 * age))
                :
                665.09 + ((9.56 * weight) + (1.85 * height) - (4.67 * age))
        );
    }

}
