package com.example.michal.dietvoltwo.service.totalBtw;


import com.example.michal.dietvoltwo.dto.BtwDto;
import com.example.michal.dietvoltwo.dto.UserDto;

public class GenerateBTW {

    private UserDto userDto;

    public GenerateBTW(UserDto userDto) {
        this.userDto = userDto;
    }

    public BtwDto createBtw(int kcal){
        BtwDto btwDto = new BtwDto();

        switch (userDto.getUserGoalDto().getTypeDiet()){
            case "WEGLOWODANY":
                btwDto = Carbohydrate.createBtwSustainable(kcal);
                break;
            case "ZROWNOWAZONA":
                btwDto = Sustainable.createBtwSustainable(kcal);
                break;
            case "BIALKOWA":
                btwDto = Protein.createBtwProtein(kcal);
                break;
            case "TLUSZCZOWA":
                btwDto = Fat.createBtwFat(kcal);
                break;
        }
        return btwDto;
    }
}
