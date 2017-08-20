package com.example.michal.dietvoltwo.service.Impl;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.michal.dietvoltwo.dto.UserParametrsDto;
import com.example.michal.dietvoltwo.service.UserParametersService;

import java.util.List;

import io.realm.Realm;


public class UserParametersServiceImpl implements UserParametersService {

    private static UserParametersServiceImpl instance;
    private Realm realm;

    public UserParametersServiceImpl(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static UserParametersServiceImpl with(Fragment fragment) {

        if (instance == null) {
            instance = new UserParametersServiceImpl(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static UserParametersServiceImpl with(Activity activity) {

        if (instance == null) {
            instance = new UserParametersServiceImpl(activity.getApplication());
        }
        return instance;
    }

    public static UserParametersServiceImpl with(Application application) {

        if (instance == null) {
            instance = new UserParametersServiceImpl(application);
        }
        return instance;
    }

    public static UserParametersServiceImpl getInstance() {

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
        realm.clear(UserParametrsDto.class);
        realm.commitTransaction();
    }


    @Override
    public UserParametrsDto findOne(int id) {
        return realm.where(UserParametrsDto.class).equalTo("id", id).findFirst();
    }

    @Override
    public List<UserParametrsDto> findAll() {
        return realm.where(UserParametrsDto.class).findAll();
    }

    @Override
    public UserParametrsDto save(UserParametrsDto userParametrsDto) {
        realm.beginTransaction();
        UserParametrsDto newUserParametrsDto = realm.createObject(UserParametrsDto.class);

        newUserParametrsDto.setId(userParametrsDto.getId());
        newUserParametrsDto.setWeight(userParametrsDto.getWeight());
        newUserParametrsDto.setHeight(userParametrsDto.getHeight());
        newUserParametrsDto.setAge(userParametrsDto.getAge());
        newUserParametrsDto.setLvlActivity(userParametrsDto.getLvlActivity());
        newUserParametrsDto.setSex(userParametrsDto.getSex());

        realm.copyToRealm(newUserParametrsDto);
        realm.commitTransaction();

        return userParametrsDto;
    }

    @Override
    public UserParametrsDto edit(UserParametrsDto editUserParametrsDto, int id) {

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
        realm.beginTransaction();
        userParametrsDto.removeFromRealm();
        realm.commitTransaction();
    }

}
