package com.example.michal.dietvoltwo.service;


import com.example.michal.dietvoltwo.dto.UserDto;
import com.example.michal.dietvoltwo.service.diatetsService.DiabetsDietGenerateService;
import com.example.michal.dietvoltwo.service.healthService.HealthDietGenerateService;

public class DietGenerateService {


    public int createDietForUser(UserDto userDto) {

        int kcal = 0;
        if (userDto.getUserGoalDto().getHealth().equals("DIABETYK")) {
            DiabetsDietGenerateService diabetsDietGenerateService = new DiabetsDietGenerateService(userDto);
            kcal = diabetsDietGenerateService.kcal();
        } else {
            HealthDietGenerateService healthDietGenerateService = new HealthDietGenerateService(userDto);
            kcal = healthDietGenerateService.kcal();
        }
        return kcal;
    }

}
