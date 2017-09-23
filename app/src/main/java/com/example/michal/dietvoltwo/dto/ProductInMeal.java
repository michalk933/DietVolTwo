package com.example.michal.dietvoltwo.dto;


import java.util.List;

public class ProductInMeal {
    private int number;
    private List<String> nameProduct;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<String> getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(List<String> nameProduct) {
        this.nameProduct = nameProduct;
    }
}
