package com.example.michal.dietvoltwo.service.totalBtw;


import com.example.michal.dietvoltwo.dto.BtwDto;

public class Fat {

    public static BtwDto createBtwFat(int kcal){
        BtwDto btwDto = new BtwDto();
        btwDto.setB(ComponentBtw.component(0.20,kcal));
        btwDto.setT(ComponentBtw.component(0.70,kcal));
        btwDto.setW(ComponentBtw.component(0.10,kcal));

        return btwDto;
    }

}
