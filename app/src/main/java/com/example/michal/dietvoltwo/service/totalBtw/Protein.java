package com.example.michal.dietvoltwo.service.totalBtw;


import com.example.michal.dietvoltwo.dto.BtwDto;

public class Protein {

    public static BtwDto createBtwProtein(int kcal) {
        BtwDto btwDto = new BtwDto();
        btwDto.setB(ComponentBtw.component(0.35, kcal) * 4);
        btwDto.setT(ComponentBtw.component(0.15, kcal) * 9);
        btwDto.setW(ComponentBtw.component(0.50, kcal) * 4);

        return btwDto;
    }
}
