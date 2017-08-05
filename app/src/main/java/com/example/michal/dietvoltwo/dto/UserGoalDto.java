package com.example.michal.dietvoltwo.dto;


import io.realm.RealmObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor(suppressConstructorProperties = true)
public class UserGoalDto extends RealmObject {

    public static UserGoalDto userPersonalDto;

    private String goal;
    private String health;
    private String diabetsType;
    private String typeDiet;

    public UserGoalDto() {
    }

    public static UserGoalDto getUserPersonalDto() {
        if (userPersonalDto == null)
            userPersonalDto = new UserGoalDto();

        return userPersonalDto;
    }


}
