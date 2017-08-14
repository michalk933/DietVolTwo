package com.example.michal.dietvoltwo.service.mealBtw;


import com.example.michal.dietvoltwo.dto.BtwDto;
import com.example.michal.dietvoltwo.dto.MealsDto;
import com.example.michal.dietvoltwo.dto.UserDto;


public class GenerateBtwMeal {

    public MealsDto createMeal(UserDto userDto, BtwDto btwDto) {

        MealsDto mealsDto = new MealsDto();

        if (userDto.getUserGoalDto().getHealth().equals("DIABETYK")) {
            mealsDto.setMealDtos(MealDiabets.createMeal(btwDto));
        } else {
            mealsDto.setMealDtos(MealDiabets.createMeal(btwDto));
        }

        return mealsDto;
    }
}
