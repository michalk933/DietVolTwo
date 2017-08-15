package com.example.michal.dietvoltwo.service.diatetsService;


import com.example.michal.dietvoltwo.dto.UserDto;
import com.example.michal.dietvoltwo.service.diet.GoalDiet;

public class DiabetsDietGenerateService {

    private UserDto userDto;

    public DiabetsDietGenerateService(UserDto userDto) {
        this.userDto = userDto;
    }

    public int kcal() {
        int bmi = BmiService.bmi(userDto.getUserParametrsDto().getWeight(), userDto.getUserParametrsDto().getHeight());
        int ratioBmiAndLvlActivity = RatioActivityAndBmi.dependinkActivityAndBmiCalculate(userDto.getUserParametrsDto().getLvlActivity(), IndicateBmi.indicateBmi(bmi));
        int cpm = Cpm.cpmCalculate(userDto.getUserParametrsDto().getWeight(), ratioBmiAndLvlActivity);
        int cpmPastGoal = GoalDiet.goal(userDto.getUserGoalDto().getGoal(), cpm);

        return cpmPastGoal;
    }


}
