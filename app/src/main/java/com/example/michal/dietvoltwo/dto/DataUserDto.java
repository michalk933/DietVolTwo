package com.example.michal.dietvoltwo.dto;


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
public class DataUserDto {

    private String name;
    private String surname;
    private int age;
    private int weight;
    private int height;
    private int lvlActivity;
    private String goal;
    private String typeDiet;



}
