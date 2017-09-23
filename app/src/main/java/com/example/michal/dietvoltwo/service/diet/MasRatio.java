package com.example.michal.dietvoltwo.service.diet;


import static com.example.michal.dietvoltwo.util.Constant.INDICATOR_DIET_GOAL_MASS;

public class MasRatio {

    public static int massCalculate(int cmp) {
        return (int) (cmp * INDICATOR_DIET_GOAL_MASS);
    }
}
