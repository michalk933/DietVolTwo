package com.example.michal.dietvoltwo.service.totalBtw;


import com.example.michal.dietvoltwo.dto.BtwDto;

public class Carbohydrate {

    public static BtwDto createBtwSustainable(int kcal){
        BtwDto btwDto = new BtwDto();
        btwDto.setB(ComponentBtw.component(0.20,kcal));
        btwDto.setT(ComponentBtw.component(0.15,kcal));
        btwDto.setW(ComponentBtw.component(0.65,kcal));

        return btwDto;
    }
}
