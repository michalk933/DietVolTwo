package com.example.michal.dietvoltwo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
public class UserParametrsDto {

    private int age;
    private int height;
    private int weight;
    private char sex;
    private String lvlActivity;

}
