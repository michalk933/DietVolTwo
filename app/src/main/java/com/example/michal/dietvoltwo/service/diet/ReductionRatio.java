package com.example.michal.dietvoltwo.service.diet;


import static com.example.michal.dietvoltwo.util.Constant.INDICATOR_DIET_GOAL_REDUCTION;

public class ReductionRatio {

    public static int reductionCalculate(int cmp) {
        return (int) (cmp * INDICATOR_DIET_GOAL_REDUCTION);

    }

}
