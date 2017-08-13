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
public class UserGoalDto extends RealmObject {

    public static UserGoalDto userPersonalDto;

    @PrimaryKey
    private int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGoal() {
        return goal;
    }

    public void setGoal(String goal) {
        this.goal = goal;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getDiabetsType() {
        return diabetsType;
    }

    public void setDiabetsType(String diabetsType) {
        this.diabetsType = diabetsType;
    }

    public String getTypeDiet() {
        return typeDiet;
    }

    public void setTypeDiet(String typeDiet) {
        this.typeDiet = typeDiet;
    }
}
