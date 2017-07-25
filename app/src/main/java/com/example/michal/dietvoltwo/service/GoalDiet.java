package com.example.michal.dietvoltwo.service;


import com.example.michal.dietvoltwo.service.MasRatio;
import com.example.michal.dietvoltwo.service.ReductionRatio;

public class GoalDiet {

    public static int goal(String goalDiet, int cpm) {
        int valuePastGoal = 0;
        if (goalDiet.equals("REDUKCJA")) {
            valuePastGoal = ReductionRatio.reductionCalculate(cpm);
        } else if (goalDiet.equals("MASSA")) {
            valuePastGoal = MasRatio.massCalculate(cpm);
        }
        return valuePastGoal;
    }

}
