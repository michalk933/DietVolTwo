package com.example.michal.dietvoltwo.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties = true)
public class UserPersonalDto {

    private static UserPersonalDto userPersonalDto;

    private String login;
    private String password;
    private String rePassword;
    private String eMail;

    private UserPersonalDto() {
    }

    public static UserPersonalDto createUserPersonalDto() {
        if (userPersonalDto == null)
            userPersonalDto = new UserPersonalDto();

        return userPersonalDto;

    }


}
