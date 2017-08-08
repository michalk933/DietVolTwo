package com.example.michal.dietvoltwo.doamin;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserPersonal extends RealmObject {

    @PrimaryKey
    private Long id;
    private String login;
    private String password;
    private String rePassword;
    private String eMail;

}
