package com.example.michal.dietvoltwo.doamin;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserParametrs extends RealmObject {

    @PrimaryKey
    private Long id;
    private int age;
    private int height;
    private int weight;
    private String sex;
    private int lvlActivity;

}
