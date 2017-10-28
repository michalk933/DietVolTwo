package com.example.michal.dietvoltwo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties=true)
@NoArgsConstructor
@Builder
public class DescriptionDietDto {

    private String image;
    private String name;
    private String unit;
    private int prodein;
    private int carbohydrate;
    private int fat;
    private int kcal;

}
