package com.example.michal.dietvoltwo.service.mealBtw;


import com.example.michal.dietvoltwo.dto.BtwDto;
import com.example.michal.dietvoltwo.dto.MealDto;

import java.util.ArrayList;
import java.util.List;

public class MealDiabets {

    public static List<MealDto> createMeal(BtwDto btwDto) {
        List<MealDto> mealDtos = new ArrayList<>();


        for (int i = 0; i <= 4; i++) {

            switch (i) {
                case 0:
                    MealDto mealDto1 = mealData(btwDto,0.25,i);
                    mealDtos.add(mealDto1);
                    break;
                case 1:
                    MealDto mealDto2 = mealData(btwDto,0.10,i);
                    mealDtos.add(mealDto2);
                    break;
                case 2:
                    MealDto mealDto3 = mealData(btwDto,0.35,i);
                    mealDtos.add(mealDto3);
                    break;
                case 3:
                    MealDto mealDto4 = mealData(btwDto,0.10,i);
                    mealDtos.add(mealDto4);
                    break;
                case 4:
                    MealDto mealDto5 = mealData(btwDto,0.20,i);
                    mealDtos.add(mealDto5);
                    break;
            }
        }

        return mealDtos;
    }

    private static MealDto mealData(BtwDto btwDto, double persent, int numberMeal) {
        int b = gramsOfMeal(persent, btwDto.getB());
        int w = gramsOfMeal(persent, btwDto.getW());
        int t = gramsOfMeal(persent, btwDto.getT());

        MealDto mealDto = new MealDto();
        mealDto.setB(b);
        mealDto.setT(t);
        mealDto.setW(w);

        mealDto.setNumberMeal(numberMeal);
        mealDto.setKcalForMeal(kcalForMeal(b, t, w));

        return mealDto;
    }

    private static int gramsOfMeal(double persent, int macros) {
        return (int) (macros * persent);
    }

    private static int kcalForMeal(int b, int t, int w) {
        int totalKcal = 0;
        totalKcal += (b *4);
        totalKcal += (t *9);
        totalKcal += (w *4);
        return totalKcal;
    }


}
