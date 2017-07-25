package com.example.michal.dietvoltwo.service.diatetsService;


public class IndicateBmi {

    public static int indicateBmi(int bmi) {
        int indicateBmi = 0;
        if (bmi <= 17) {
            indicateBmi = 30;
        } else if (bmi > 17 && bmi < 30) {
            indicateBmi = 35;
        } else if (bmi >= 30) {
            indicateBmi = 40;
        }
        return indicateBmi;
    }
}
