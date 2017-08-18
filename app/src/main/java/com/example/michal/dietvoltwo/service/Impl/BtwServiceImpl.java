package com.example.michal.dietvoltwo.service.Impl;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.michal.dietvoltwo.dto.BtwDto;
import com.example.michal.dietvoltwo.dto.MealDto;

import java.util.List;

import io.realm.Realm;

public class BtwServiceImpl {

    private static BtwServiceImpl instance;
    private Realm realm;

    public BtwServiceImpl(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static BtwServiceImpl with(Fragment fragment) {
        if (instance == null) {
            instance = new BtwServiceImpl(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static BtwServiceImpl with(Activity activity) {
        if (instance == null) {
            instance = new BtwServiceImpl(activity.getApplication());
        }
        return instance;
    }

    public static BtwServiceImpl with(Application application) {
        if (instance == null) {
            instance = new BtwServiceImpl(application);
        }
        return instance;
    }

    public static BtwServiceImpl getInstance() {
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
        realm.clear(BtwDto.class);
        realm.commitTransaction();
    }


    public BtwDto findOne(int id) {
        return realm.where(BtwDto.class).equalTo("id", id).findFirst();
    }

    public List<BtwDto> findAll() {
        return realm.where(BtwDto.class).findAll();
    }

    public BtwDto save(BtwDto addBtwDto) {
        realm.beginTransaction();
        BtwDto btwDto = realm.createObject(BtwDto.class);

        btwDto.setB(addBtwDto.getB());
        btwDto.setT(addBtwDto.getT());
        btwDto.setW(addBtwDto.getW());

        realm.copyToRealm(btwDto);
        realm.commitTransaction();

        return btwDto;
    }

    public BtwDto edit(BtwDto editBtwDto, int id) {
        realm.beginTransaction();

        BtwDto btwDto = findOne(id);
        btwDto.setB(editBtwDto.getB());
        btwDto.setT(editBtwDto.getT());
        btwDto.setW(editBtwDto.getW());

        realm.copyToRealmOrUpdate(btwDto);
        realm.commitTransaction();

        return btwDto;
    }

    public void delete(BtwDto btwDto) {
        realm.beginTransaction();
        btwDto.removeFromRealm();
        realm.commitTransaction();

    }




}
