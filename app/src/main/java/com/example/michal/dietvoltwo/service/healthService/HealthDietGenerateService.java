package com.example.michal.dietvoltwo.service.healthService;

import com.example.michal.dietvoltwo.dto.UserDto;
import com.example.michal.dietvoltwo.service.diet.GoalDiet;

public class HealthDietGenerateService {

    private UserDto userDto;

    public HealthDietGenerateService(UserDto userDto) {
        this.userDto = userDto;
    }

    public int kcal() {
        int bmr = Bmr.BMRCalculat(userDto.getUserParametrsDto().getSex(),
                userDto.getUserParametrsDto().getAge(),
                userDto.getUserParametrsDto().getWeight(),
                userDto.getUserParametrsDto().getHeight());

        int tef = Tef.TEFCalculate(bmr);
        int tea = Tea.TEACalculate(bmr, userDto.getUserParametrsDto().getLvlActivity());
        int neat = Neat.NEATCalculate();
        int cpm = Cpm.CMPCalculate(bmr, tef, tea, neat);
        int cpmPastGoal = GoalDiet.goal(userDto.getUserGoalDto().getGoal(), cpm);

        return cpmPastGoal;
    }
}
