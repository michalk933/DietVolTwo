package com.example.michal.dietvoltwo.dto;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
@Builder
public class ProductDto extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String producent;
    private String productTyp;
    private String timeOfDay;
    private int kcal;
    private int b;
    private int t;
    private int w;
    private int wW;
    private int ig;
    private int forDiabets;
    private Date create;
    private byte[] image;

}
