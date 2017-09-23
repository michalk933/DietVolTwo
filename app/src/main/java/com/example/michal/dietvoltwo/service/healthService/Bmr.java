package com.example.michal.dietvoltwo.service.healthService;


import static com.example.michal.dietvoltwo.util.Constant.DIET_PARAMETERS_BMR_MAN_FOUR;
import static com.example.michal.dietvoltwo.util.Constant.DIET_PARAMETERS_BMR_MAN_ONE;
import static com.example.michal.dietvoltwo.util.Constant.DIET_PARAMETERS_BMR_MAN_THREE;
import static com.example.michal.dietvoltwo.util.Constant.DIET_PARAMETERS_BMR_MAN_TWO;
import static com.example.michal.dietvoltwo.util.Constant.DIET_PARAMETERS_BMR_WOMAN_FOUR;
import static com.example.michal.dietvoltwo.util.Constant.DIET_PARAMETERS_BMR_WOMAN_ONE;
import static com.example.michal.dietvoltwo.util.Constant.DIET_PARAMETERS_BMR_WOMAN_THREE;
import static com.example.michal.dietvoltwo.util.Constant.DIET_PARAMETERS_BMR_WOMAN_TWO;
import static com.example.michal.dietvoltwo.util.Constant.PARAMETER_SEX_MAN;

public class Bmr {

    public static int BMRCalculat(String sex, int age, int weight, int height) {

        return (int) (sex.equals(PARAMETER_SEX_MAN) ?
                DIET_PARAMETERS_BMR_MAN_ONE +
                        ((DIET_PARAMETERS_BMR_MAN_TWO * weight) +
                                (DIET_PARAMETERS_BMR_MAN_THREE * height) -
                                (DIET_PARAMETERS_BMR_MAN_FOUR * age))
                :
                DIET_PARAMETERS_BMR_WOMAN_ONE +
                        ((DIET_PARAMETERS_BMR_WOMAN_TWO * weight) +
                                (DIET_PARAMETERS_BMR_WOMAN_THREE * height) -
                                (DIET_PARAMETERS_BMR_WOMAN_FOUR * age))
        );
    }

}
