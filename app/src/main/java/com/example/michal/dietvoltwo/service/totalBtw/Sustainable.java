package com.example.michal.dietvoltwo.service.totalBtw;


import com.example.michal.dietvoltwo.dto.BtwDto;

public class Sustainable {

    public static BtwDto createBtwSustainable(int kcal) {
        BtwDto btwDto = new BtwDto();
        btwDto.setB(ComponentBtw.component(0.20, kcal));
        btwDto.setT(ComponentBtw.component(0.30, kcal));
        btwDto.setW(ComponentBtw.component(0.50, kcal));

        return btwDto;
    }
}
