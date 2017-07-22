package com.example.michal.dietvoltwo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
public class UserDto {

    private UserGoalDto userGoalDto;
    private UserDietChoiceDto userDietChoiceDto;
    private UserParametrsDto userParametrsDto;
    private UserPersonalDto userPersonalDto;

}
