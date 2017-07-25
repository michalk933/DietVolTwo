package com.example.michal.dietvoltwo.service.healthService;


public class Tea {

    public static int TEACalculate(int bmr, int lvlActivity) {
        return (int) (lvlActivity >= 3 ? (bmr * 0.5) : (bmr * 0.3));
    }
}
