package com.example.michal.dietvoltwo.service.diet;


import com.example.michal.dietvoltwo.dto.UserDto;
import com.example.michal.dietvoltwo.service.diatetsService.DiabetsDietGenerateService;
import com.example.michal.dietvoltwo.service.healthService.HealthDietGenerateService;
import com.example.michal.dietvoltwo.util.Constant;

import static com.example.michal.dietvoltwo.util.Constant.GOAL_HEALTH_DIABETS;

public class DietGenerateService {


    public int createDietForUser(UserDto userDto) {
        int kcal = 0;
        if (userDto.getUserGoalDto().getHealth().equals(GOAL_HEALTH_DIABETS)) {
            DiabetsDietGenerateService diabetsDietGenerateService = new DiabetsDietGenerateService(userDto);
            kcal = diabetsDietGenerateService.kcal();
        } else {
            HealthDietGenerateService healthDietGenerateService = new HealthDietGenerateService(userDto);
            kcal = healthDietGenerateService.kcal();
        }
        return kcal;
    }

}
