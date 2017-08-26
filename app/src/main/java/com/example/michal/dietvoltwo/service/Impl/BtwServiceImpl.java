package com.example.michal.dietvoltwo.service.Impl;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.michal.dietvoltwo.dto.BtwDto;
import com.example.michal.dietvoltwo.dto.MealDto;

import java.util.List;

import io.realm.Realm;
import lombok.extern.log4j.Log4j;

@Log4j
public class BtwServiceImpl implements RealmBasisService<BtwDto> {

    private static BtwServiceImpl instance;
    private Realm realm;

    public BtwServiceImpl(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static BtwServiceImpl with(Fragment fragment) {
        log.trace("BtwServiceImpl : with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new BtwServiceImpl(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static BtwServiceImpl with(Activity activity) {
        log.trace("BtwServiceImpl : with(Activity activity) : get instance");
        if (instance == null) {
            instance = new BtwServiceImpl(activity.getApplication());
        }
        return instance;
    }

    public static BtwServiceImpl with(Application application) {
        log.trace("BtwServiceImpl : with(Application application) : get instance");
        if (instance == null) {
            instance = new BtwServiceImpl(application);
        }
        return instance;
    }

    public static BtwServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Realm getRealm() {
        log.trace("BtwServiceImpl : getRealm() : get realm");
        return this.realm;
    }

    @Override
    public void refresh() {
        log.trace("BtwServiceImpl : refresh() : refresh");
        realm.refresh();
    }

    @Override
    public void clearAll() {
        log.trace("BtwServiceImpl : clearAll() : clear");
        realm.beginTransaction();
        realm.clear(BtwDto.class);
        realm.commitTransaction();
    }

    @Override
    public BtwDto findOne(int id) {
        log.trace("BtwServiceImpl : findOne(int id) : find record in data base");
        return realm.where(BtwDto.class).equalTo("id", id).findFirst();
    }

    @Override
    public List<BtwDto> findAll() {
        log.trace("BtwServiceImpl : findAll() : find all records in data base");
        return realm.where(BtwDto.class).findAll();
    }

    @Override
    public BtwDto save(BtwDto addBtwDto) {
        log.trace("BtwServiceImpl : save(BtwDto addBtwDto) : save new object {}" + addBtwDto);
        realm.beginTransaction();
        BtwDto btwDto = realm.createObject(BtwDto.class);

        btwDto.setB(addBtwDto.getB());
        btwDto.setT(addBtwDto.getT());
        btwDto.setW(addBtwDto.getW());
        btwDto.setKcal(addBtwDto.getKcal());

        realm.copyToRealm(btwDto);
        realm.commitTransaction();

        return btwDto;
    }

    @Override
    public BtwDto edit(BtwDto editBtwDto, int id) {
        log.trace("BtwServiceImpl : edit(BtwDto editBtwDto, int id) : edit object {}" + editBtwDto);
        realm.beginTransaction();

        BtwDto btwDto = findOne(id);
        btwDto.setKcal(editBtwDto.getKcal());
        btwDto.setB(editBtwDto.getB());
        btwDto.setT(editBtwDto.getT());
        btwDto.setW(editBtwDto.getW());

        realm.copyToRealmOrUpdate(btwDto);
        realm.commitTransaction();

        return btwDto;
    }

    @Override
    public void delete(BtwDto btwDto) {
        log.trace("BtwServiceImpl : delete(BtwDto btwDto) : delete object {}" + btwDto);
        realm.beginTransaction();
        btwDto.removeFromRealm();
        realm.commitTransaction();
    }

}
