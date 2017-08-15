package com.example.michal.dietvoltwo.service.Impl;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.michal.dietvoltwo.dto.ProductDto;

import java.util.List;

import io.realm.Realm;

public class ProductServiceImpl {

    private static ProductServiceImpl instance;
    private Realm realm;

    public ProductServiceImpl(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static ProductServiceImpl with(Fragment fragment) {
        if (instance == null) {
            instance = new ProductServiceImpl(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static ProductServiceImpl with(Activity activity) {
        if (instance == null) {
            instance = new ProductServiceImpl(activity.getApplication());
        }
        return instance;
    }

    public static ProductServiceImpl with(Application application) {
        if (instance == null) {
            instance = new ProductServiceImpl(application);
        }
        return instance;
    }

    public static ProductServiceImpl getInstance() {
        return instance;
    }

    public Realm getRealm() {
        return this.realm;
    }

    public void refresh() {
        realm.refresh();
    }

    public void clearAll() {
        realm.beginTransaction();
        realm.clear(ProductDto.class);
        realm.commitTransaction();
    }


    public ProductDto findOne(int id) {
        return realm.where(ProductDto.class).equalTo("id", id).findFirst();
    }

    public List<ProductDto> findAll() {
        return realm.where(ProductDto.class).findAll();
    }

    public ProductDto save(ProductDto addProductDto) {
        realm.beginTransaction();
        ProductDto productDto = realm.createObject(ProductDto.class);

        productDto.setId(addProductDto.getId());
        productDto.setName(addProductDto.getName());
        productDto.setProducent(addProductDto.getProducent());
        productDto.setProductTyp(addProductDto.getProductTyp());
        productDto.setTimeOfDay(addProductDto.getTimeOfDay());
        productDto.setKcal(addProductDto.getKcal());
        productDto.setB(addProductDto.getB());
        productDto.setT(addProductDto.getT());
        productDto.setW(addProductDto.getW());
        productDto.setwW(addProductDto.getwW());
        productDto.setIg(addProductDto.getIg());
        productDto.setForDiabets(addProductDto.getForDiabets());
        productDto.setCreate(addProductDto.getCreate());
        productDto.setImage(addProductDto.getImage());

        realm.copyToRealm(productDto);
        realm.commitTransaction();

        return productDto;
    }

    public ProductDto edit(ProductDto editProductDto, int id) {
        realm.beginTransaction();

        ProductDto productDto = findOne(id);
        productDto.setName(editProductDto.getName());
        productDto.setProducent(editProductDto.getProducent());
        productDto.setProductTyp(editProductDto.getProductTyp());
        productDto.setTimeOfDay(editProductDto.getTimeOfDay());
        productDto.setKcal(editProductDto.getKcal());
        productDto.setB(editProductDto.getB());
        productDto.setT(editProductDto.getT());
        productDto.setW(editProductDto.getW());
        productDto.setwW(editProductDto.getwW());
        productDto.setIg(editProductDto.getIg());
        productDto.setForDiabets(editProductDto.getForDiabets());
        productDto.setCreate(editProductDto.getCreate());
        productDto.setImage(editProductDto.getImage());

        realm.copyToRealmOrUpdate(productDto);
        realm.commitTransaction();

        return productDto;
    }

    public void delete(ProductDto productDto) {
        realm.beginTransaction();
        productDto.removeFromRealm();
        realm.commitTransaction();

    }


}
