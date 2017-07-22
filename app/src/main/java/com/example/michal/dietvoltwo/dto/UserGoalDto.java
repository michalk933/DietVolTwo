package com.example.michal.dietvoltwo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties = true)
public class UserGoalDto {

    private static UserGoalDto userPersonalDto;

    private String goal;
    private String health;
    private String diabetsType;
    private String typeDiet;

    private UserGoalDto() {
    }

    public static UserGoalDto getUserPersonalDto() {
        if (userPersonalDto == null)
            userPersonalDto = new UserGoalDto();

        return userPersonalDto;
    }


}
