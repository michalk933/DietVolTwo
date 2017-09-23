package com.example.michal.dietvoltwo.service.totalBtw;


import com.example.michal.dietvoltwo.dto.BtwDto;
import com.example.michal.dietvoltwo.dto.UserDto;

import static com.example.michal.dietvoltwo.util.Constant.GOAL_TYPE_DIETS_CARBOHYDRATE;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_TYPE_DIETS_FAT;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_TYPE_DIETS_PROTEIN;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_TYPE_DIETS_STABILE;

public class GenerateBTW {

    private UserDto userDto;

    public GenerateBTW(UserDto userDto) {
        this.userDto = userDto;
    }

    public BtwDto createBtw(int kcal){
        BtwDto btwDto = new BtwDto();

        switch (userDto.getUserGoalDto().getTypeDiet()){
            case GOAL_TYPE_DIETS_CARBOHYDRATE:
                btwDto = Carbohydrate.createBtwSustainable(kcal);
                break;
            case GOAL_TYPE_DIETS_STABILE:
                btwDto = Sustainable.createBtwSustainable(kcal);
                break;
            case GOAL_TYPE_DIETS_PROTEIN:
                btwDto = Protein.createBtwProtein(kcal);
                break;
            case GOAL_TYPE_DIETS_FAT:
                btwDto = Fat.createBtwFat(kcal);
                break;
        }
        return btwDto;
    }
}
