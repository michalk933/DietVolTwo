package com.example.michal.dietvoltwo.service.planDietService;


import com.example.michal.dietvoltwo.dto.ProductDto;
import com.example.michal.dietvoltwo.dto.ProductInMeal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.example.michal.dietvoltwo.util.Constant.CARBOHYDRATE_TYPE;
import static com.example.michal.dietvoltwo.util.Constant.FAT_TYPE;
import static com.example.michal.dietvoltwo.util.Constant.PROTEIN_TYPE;

public class createPlanDietWithProduct {

    public static ProductInMeal create(List<ProductDto> products, int numberMeal) {
        ProductInMeal productInMeal = new ProductInMeal();
        List<String> listProduct = new ArrayList<>();
        listProduct.add(findProdeinProduct(filterProduct(products, PROTEIN_TYPE)).getName());
        listProduct.add(findFatProduct(filterProduct(products, FAT_TYPE)).getName());
        listProduct.add(findCarboHydrateProduct(filterProduct(products, CARBOHYDRATE_TYPE)).getName());
        productInMeal.setNumber(numberMeal);
        productInMeal.setNameProduct(listProduct);
        return productInMeal;
    }

    private static ProductDto findProdeinProduct(List<ProductDto> proteinProducts) {
        return proteinProducts.get(new Random().nextInt((proteinProducts.size() - 0) - 0));
    }

    private static ProductDto findCarboHydrateProduct(List<ProductDto> carboHydrateProducts) {
        return carboHydrateProducts.get(new Random().nextInt((carboHydrateProducts.size() - 0) - 0));
    }

    private static ProductDto findFatProduct(List<ProductDto> fatProducts) {
        return fatProducts.get(new Random().nextInt((fatProducts.size() - 0) - 0));
    }

    private static List<ProductDto> filterProduct(List<ProductDto> products, String typProduct) {
        List<ProductDto> fatProducts = new ArrayList<>();
        for (ProductDto product : products) {
            if (product.getProductTyp().equals(typProduct)) {
                fatProducts.add(product);
            }
        }
        return fatProducts;
    }

}
