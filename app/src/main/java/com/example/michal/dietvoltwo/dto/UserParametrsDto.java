package com.example.michal.dietvoltwo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties = true)
public class UserParametrsDto {

    private static UserParametrsDto userParametrsDto;

    private int age;
    private int height;
    private int weight;
    private char sex;
    private String lvlActivity;

    private UserParametrsDto(){}

    public static UserParametrsDto getUserParametrsDto(){
        if(userParametrsDto == null)
            userParametrsDto = new UserParametrsDto();

        return userParametrsDto;
    }

}
