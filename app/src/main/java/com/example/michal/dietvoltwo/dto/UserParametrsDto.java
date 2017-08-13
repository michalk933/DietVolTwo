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
public class UserParametrsDto extends RealmObject {

    public static UserParametrsDto userParametrsDto;

    @PrimaryKey
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getLvlActivity() {
        return lvlActivity;
    }

    public void setLvlActivity(int lvlActivity) {
        this.lvlActivity = lvlActivity;
    }
}
