package com.example.michal.dietvoltwo.service.totalBtw;


import com.example.michal.dietvoltwo.dto.BtwDto;

import static com.example.michal.dietvoltwo.util.Constant.KCAL_IN_ONE_GRAMS_CARBOHYDRATE;
import static com.example.michal.dietvoltwo.util.Constant.KCAL_IN_ONE_GRAMS_FAT;
import static com.example.michal.dietvoltwo.util.Constant.KCAL_IN_ONE_GRAMS_PROTEIN;
import static com.example.michal.dietvoltwo.util.Constant.PROPORCION_CARBOHYDRATE_IN_DIET_CARBOHYDRATE;
import static com.example.michal.dietvoltwo.util.Constant.PROPORCION_FAT_IN_DIET_CARBOHYDRATE;
import static com.example.michal.dietvoltwo.util.Constant.PROPORCION_PROTEIN_IN_DIET_CARBOHYDRATE;

public class Carbohydrate {

    public static BtwDto createBtwSustainable(int kcal) {
        BtwDto btwDto = new BtwDto();
        btwDto.setB(ComponentBtw.component(PROPORCION_PROTEIN_IN_DIET_CARBOHYDRATE, kcal) / KCAL_IN_ONE_GRAMS_PROTEIN);
        btwDto.setT(ComponentBtw.component(PROPORCION_FAT_IN_DIET_CARBOHYDRATE, kcal) / KCAL_IN_ONE_GRAMS_FAT);
        btwDto.setW(ComponentBtw.component(PROPORCION_CARBOHYDRATE_IN_DIET_CARBOHYDRATE, kcal) / KCAL_IN_ONE_GRAMS_CARBOHYDRATE);

        return btwDto;
    }
}
