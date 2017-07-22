package com.example.michal.dietvoltwo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties = true)
public class UserDto {

    private static UserDto userDto;

    private UserGoalDto userGoalDto;
    private UserParametrsDto userParametrsDto;
    private UserPersonalDto userPersonalDto;

    private UserDto() {
    }

    public static UserDto getUserDto() {
        if (userDto == null)
            userDto = new UserDto();

        return userDto;
    }


}
