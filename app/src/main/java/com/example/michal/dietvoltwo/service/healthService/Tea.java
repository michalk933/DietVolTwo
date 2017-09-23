package com.example.michal.dietvoltwo.service.healthService;


import static com.example.michal.dietvoltwo.util.Constant.DIET_PARAMETERS_HEIGHT_LVL_ACTIVITY;
import static com.example.michal.dietvoltwo.util.Constant.DIET_PARAMETERS_LOW_LVL_ACTIVITY;
import static com.example.michal.dietvoltwo.util.Constant.PARAMETER_LVL_ACTIVITY_MEDIUM;

public class Tea {

    public static int TEACalculate(int bmr, int lvlActivity) {
        return (int) (lvlActivity >= PARAMETER_LVL_ACTIVITY_MEDIUM ?
                (bmr * DIET_PARAMETERS_HEIGHT_LVL_ACTIVITY) :
                (bmr * DIET_PARAMETERS_LOW_LVL_ACTIVITY));
    }
}
