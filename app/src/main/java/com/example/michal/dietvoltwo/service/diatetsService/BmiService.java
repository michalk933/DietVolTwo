package com.example.michal.dietvoltwo.service.diatetsService;


public class BmiService {

    public static int bmi(int weight, int height){
        return (int) ( weight / ( height * height ) );
    }

}
