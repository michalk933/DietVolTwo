package com.example.michal.dietvoltwo.service.healthService;



import static com.example.michal.dietvoltwo.util.Constant.DIET_PARAMETERS_TEF_INDICATOR;

public class Tef {

    public static int TEFCalculate(int bmr) {
        return (int) (bmr * DIET_PARAMETERS_TEF_INDICATOR);
    }
}
