package com.example.michal.dietvoltwo.service.mealBtw;


import com.example.michal.dietvoltwo.dto.BtwDto;
import com.example.michal.dietvoltwo.dto.MealsDto;
import com.example.michal.dietvoltwo.dto.UserDto;
import com.example.michal.dietvoltwo.util.Constant;

import static com.example.michal.dietvoltwo.util.Constant.GOAL_HEALTH_DIABETS;


public class GenerateBtwMeal {

    public MealsDto createMeal(UserDto userDto, BtwDto btwDto) {
        MealsDto mealsDto = new MealsDto();
        if (userDto.getUserGoalDto().getHealth().equals(GOAL_HEALTH_DIABETS)) {
            mealsDto.setMealDtos(MealDiabets.createMeal(btwDto));
        } else {
            mealsDto.setMealDtos(MealDiabets.createMeal(btwDto));
        }
        return mealsDto;
    }
}
