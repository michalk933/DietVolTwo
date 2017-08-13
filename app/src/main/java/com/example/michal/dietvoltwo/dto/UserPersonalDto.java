package com.example.michal.dietvoltwo.dto;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
//@Builder
//@AllArgsConstructor(suppressConstructorProperties = true)
public class UserPersonalDto extends RealmObject{

    public static UserPersonalDto userPersonalDto;

    @PrimaryKey
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }
}
