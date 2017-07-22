package com.example.michal.dietvoltwo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor(suppressConstructorProperties = true)
@NoArgsConstructor
public class UserDietChoiceDto {

    private String health;
    private String diabetsType;
    private String typeDiet;

}
