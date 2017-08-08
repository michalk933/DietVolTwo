package com.example.michal.dietvoltwo.dto;

import io.realm.RealmObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor(suppressConstructorProperties = true)
//@NoArgsConstructor
public class UserDto extends RealmObject {

    private UserGoalDto userGoalDto;
    private UserParametrsDto userParametrsDto;
    private  UserPersonalDto userPersonalDto;

    public UserDto() {}

    public UserDto(UserGoalDto userGoalDto, UserParametrsDto userParametrsDto, UserPersonalDto userPersonalDto) {
        this.userGoalDto = userGoalDto;
        this.userParametrsDto = userParametrsDto;
        this.userPersonalDto = userPersonalDto;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userGoalDto=" + userGoalDto +
                ", userParametrsDto=" + userParametrsDto +
                ", userPersonalDto=" + userPersonalDto +
                '}';
    }
}
