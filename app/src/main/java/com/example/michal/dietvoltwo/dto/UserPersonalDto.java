package com.example.michal.dietvoltwo.dto;


import io.realm.RealmObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
//@AllArgsConstructor(suppressConstructorProperties = true)
public class UserPersonalDto {

    public static UserPersonalDto userPersonalDto;

    private String login;
    private String password;
    private String rePassword;
    private String eMail;

    public UserPersonalDto() {
    }

    public static UserPersonalDto createUserPersonalDto() {
        if (userPersonalDto == null)
            userPersonalDto = new UserPersonalDto();

        return userPersonalDto;

    }


}
