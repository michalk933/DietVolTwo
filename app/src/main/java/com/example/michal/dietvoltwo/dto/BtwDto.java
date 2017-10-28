package com.example.michal.dietvoltwo.dto;

import io.realm.RealmObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties=true)
@NoArgsConstructor
@Builder
public class BtwDto extends RealmObject{

    private int id;
    private int b;
    private int w;
    private int t;
    private int kcal;

}
