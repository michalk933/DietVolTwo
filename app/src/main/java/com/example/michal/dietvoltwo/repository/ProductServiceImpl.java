package com.example.michal.dietvoltwo.repository;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.michal.dietvoltwo.dto.ProductDto;

import io.realm.Realm;
import io.realm.RealmResults;
import lombok.extern.log4j.Log4j;

@Log4j
public class ProductServiceImpl implements RealmBasisService<ProductDto> {

    private static ProductServiceImpl instance;
    private Realm realm;

    public ProductServiceImpl(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static ProductServiceImpl with(Fragment fragment) {
        log.error("ProductServiceImpl : with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new ProductServiceImpl(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static ProductServiceImpl with(Activity activity) {
        log.error("ProductServiceImpl : with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new ProductServiceImpl(activity.getApplication());
        }
        return instance;
    }

    public static ProductServiceImpl with(Application application) {
        log.error("ProductServiceImpl : with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new ProductServiceImpl(application);
        }
        return instance;
    }

    public static ProductServiceImpl getInstance() {
        log.error("ProductServiceImpl : getInstance : get instance");
        return instance;
    }

    @Override
    public Realm getRealm() {
        log.error("ProductServiceImpl : getRealm() : get realm");
        return this.realm;
    }

    @Override
    public void refresh() {
        log.error("ProductServiceImpl : refresh() : refresh");
        realm.refresh();
    }

    @Override
    public void clearAll() {
        log.error("ProductServiceImpl : clearAll() : clear");
        realm.beginTransaction();
        realm.clear(ProductDto.class);
        realm.commitTransaction();
    }

    @Override
    public ProductDto findOne(int id) {
        log.error("ProductServiceImpl : findOne(int id) : find record in data base");
        return realm.where(ProductDto.class).equalTo("id", id).findFirst();
    }

    public RealmResults<ProductDto> findForName(String name1, String name2, String name3) {
        log.error("ProductServiceImpl : findOne(int id) : find record in data base");
        return realm.where(ProductDto.class).equalTo("name", name1).or().equalTo("name", name2).or().equalTo("name", name3).findAll();
    }

    @Override
    public RealmResults<ProductDto> findAll() {
        log.error("ProductServiceImpl : findAll() : find all records in data base");
        return realm.where(ProductDto.class).findAll();
    }

    @Override
    public ProductDto save(ProductDto addProductDto) {
        log.error("ProductServiceImpl : save(ProductDto addProductDto) : save new object {}" + addProductDto);
        realm.beginTransaction();
        ProductDto productDto = realm.createObject(ProductDto.class);
        productDto.setId((int) System.currentTimeMillis());
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

    @Override
    public ProductDto edit(ProductDto editProductDto, int id) {
        log.error("ProductServiceImpl : edit(ProductDto editProductDto, int id) : edit object {}" + editProductDto);
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

    @Override
    public void delete(ProductDto productDto) {
        log.error("ProductServiceImpl : delete(ProductDto productDto) : delete object {}" + productDto);
        realm.beginTransaction();
        productDto.removeFromRealm();
        realm.commitTransaction();
    }


}
