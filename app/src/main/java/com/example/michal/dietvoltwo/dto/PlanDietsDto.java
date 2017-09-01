package com.example.michal.dietvoltwo.dto;

import java.util.List;

public class PlanDietsDto {

    private int number;
    private List<String> name;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }
}
