package com.example.michal.dietvoltwo.service.diet;


import static com.example.michal.dietvoltwo.util.Constant.GOAL_DIET_MASS;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_DIET_REDUCTION;

public class GoalDiet {

    public static int goal(String goalDiet, int cpm) {
        int valuePastGoal = 0;
        if (goalDiet.equals(GOAL_DIET_REDUCTION)) {
            valuePastGoal = ReductionRatio.reductionCalculate(cpm);
        } else if (goalDiet.equals(GOAL_DIET_MASS)) {
            valuePastGoal = MasRatio.massCalculate(cpm);
        }
        return valuePastGoal;
    }

}
