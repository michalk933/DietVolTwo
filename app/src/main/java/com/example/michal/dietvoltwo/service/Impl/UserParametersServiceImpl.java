package com.example.michal.dietvoltwo.service.Impl;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.michal.dietvoltwo.dto.UserParametrsDto;

import java.util.List;

import io.realm.Realm;
import lombok.extern.log4j.Log4j;

@Log4j
public class UserParametersServiceImpl implements RealmBasisService<UserParametrsDto> {

    private static UserParametersServiceImpl instance;
    private Realm realm;

    public UserParametersServiceImpl(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static UserParametersServiceImpl with(Fragment fragment) {
        log.trace("UserParametersServiceImpl : with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new UserParametersServiceImpl(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static UserParametersServiceImpl with(Activity activity) {
        log.trace("UserParametersServiceImpl : with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new UserParametersServiceImpl(activity.getApplication());
        }
        return instance;
    }

    public static UserParametersServiceImpl with(Application application) {
        log.trace("UserParametersServiceImpl : with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new UserParametersServiceImpl(application);
        }
        return instance;
    }

    public static UserParametersServiceImpl getInstance() {
        log.trace("UserParametersServiceImpl : getInstance : get instance");
        return instance;
    }

    @Override
    public Realm getRealm() {
        log.trace("UserParametersServiceImpl : getRealm() : get realm");
        return this.realm;
    }

    @Override
    public void refresh() {
        log.trace("UserParametersServiceImpl : refresh() : refresh");
        realm.refresh();
    }

    @Override
    public void clearAll() {
        log.trace("UserParametersServiceImpl : clearAll() : clear");
        realm.beginTransaction();
        realm.clear(UserParametrsDto.class);
        realm.commitTransaction();
    }


    @Override
    public UserParametrsDto findOne(int id) {
        log.trace("UserParametersServiceImpl : findOne(int id) : find record in data base");
        return realm.where(UserParametrsDto.class).equalTo("id", id).findFirst();
    }

    @Override
    public List<UserParametrsDto> findAll() {
        log.trace("UserParametersServiceImpl : findAll() : find all records in data base");
        return realm.where(UserParametrsDto.class).findAll();
    }

    @Override
    public UserParametrsDto save(UserParametrsDto addUserParametrsDto) {
        log.trace("UserParametersServiceImpl : save(UserParametrsDto addUserParametrsDto) : save new object {}" + addUserParametrsDto);
        realm.beginTransaction();
        UserParametrsDto newUserParametrsDto = realm.createObject(UserParametrsDto.class);

        newUserParametrsDto.setId(addUserParametrsDto.getId());
        newUserParametrsDto.setWeight(addUserParametrsDto.getWeight());
        newUserParametrsDto.setHeight(addUserParametrsDto.getHeight());
        newUserParametrsDto.setAge(addUserParametrsDto.getAge());
        newUserParametrsDto.setLvlActivity(addUserParametrsDto.getLvlActivity());
        newUserParametrsDto.setSex(addUserParametrsDto.getSex());

        realm.copyToRealm(newUserParametrsDto);
        realm.commitTransaction();

        return addUserParametrsDto;
    }

    @Override
    public UserParametrsDto edit(UserParametrsDto editUserParametrsDto, int id) {
        log.trace("UserParametersServiceImpl : edit(UserParametrsDto editUserParametrsDto, int id) : edit object {}" + editUserParametrsDto);
        UserParametrsDto userParametrsDto = findOne(id);
        userParametrsDto.setAge(editUserParametrsDto.getAge());
        userParametrsDto.setHeight(editUserParametrsDto.getHeight());
        userParametrsDto.setWeight(editUserParametrsDto.getWeight());
        userParametrsDto.setLvlActivity(editUserParametrsDto.getLvlActivity());
        userParametrsDto.setSex(editUserParametrsDto.getSex());

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(userParametrsDto);
        realm.commitTransaction();

        return userParametrsDto;
    }

    @Override
    public void delete(UserParametrsDto userParametrsDto) {
        log.trace("UserParametersServiceImpl : delete(UserParametrsDto userParametrsDto) : delete object {}" + userParametrsDto);
        realm.beginTransaction();
        userParametrsDto.removeFromRealm();
        realm.commitTransaction();
    }

}
