package com.example.michal.dietvoltwo.doamin;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserGoal extends RealmObject {

    @PrimaryKey
    private Long id;
    private String goal;
    private String health;
    private String diabetsType;
    private String typeDiet;

}
