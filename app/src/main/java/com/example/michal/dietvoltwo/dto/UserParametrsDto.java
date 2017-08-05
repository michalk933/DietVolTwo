package com.example.michal.dietvoltwo.dto;


import io.realm.RealmObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor(suppressConstructorProperties = true)
public class UserParametrsDto extends RealmObject {

    public static UserParametrsDto userParametrsDto;

    private int age;
    private int height;
    private int weight;
    private String sex;
    private int lvlActivity;

    public UserParametrsDto(){}

    public static UserParametrsDto getUserParametrsDto(){
        if(userParametrsDto == null)
            userParametrsDto = new UserParametrsDto();

        return userParametrsDto;
    }

}
