package com.example.michal.dietvoltwo.dto;


import io.realm.RealmObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor(suppressConstructorProperties = true)
public class UserPersonalDto extends RealmObject {

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
