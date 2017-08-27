package com.example.michal.dietvoltwo.service.Impl;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.util.Log;

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
        Log.e("UserParametersService",": with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new UserParametersServiceImpl(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static UserParametersServiceImpl with(Activity activity) {
        Log.e("UserParametersService",": with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new UserParametersServiceImpl(activity.getApplication());
        }
        return instance;
    }

    public static UserParametersServiceImpl with(Application application) {
        Log.e("UserParametersService"," : with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new UserParametersServiceImpl(application);
        }
        return instance;
    }

    public static UserParametersServiceImpl getInstance() {
        Log.e("UserParametersService"," : getInstance : get instance");
        return instance;
    }

    @Override
    public Realm getRealm() {
        Log.e("UserParametersService"," : getRealm() : get realm");
        return this.realm;
    }

    @Override
    public void refresh() {
        Log.e("UserParametersService"," : refresh() : refresh");
        realm.refresh();
    }

    @Override
    public void clearAll() {
        Log.e("UserParametersService"," : clearAll() : clear");
        realm.beginTransaction();
        realm.clear(UserParametrsDto.class);
        realm.commitTransaction();
    }


    @Override
    public UserParametrsDto findOne(int id) {
        Log.e("UserParametersService"," : findOne(int id) : find record in data base");
        return realm.where(UserParametrsDto.class).equalTo("id", id).findFirst();
    }

    @Override
    public List<UserParametrsDto> findAll() {
        Log.e("UserParametersService"," : findAll() : find all records in data base");
        return realm.where(UserParametrsDto.class).findAll();
    }

    @Override
    public UserParametrsDto save(UserParametrsDto addUserParametrsDto) {
        clearAll();
        Log.e("UserParametersService"," : save(UserParametrsDto addUserParametrsDto) : save new object" + addUserParametrsDto);
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
        Log.e("UserParametersService"," : edit(UserParametrsDto editUserParametrsDto, int id) : edit object" + editUserParametrsDto);
        realm.beginTransaction();
        UserParametrsDto userParametrsDto = findOne(id);
        userParametrsDto.setAge(editUserParametrsDto.getAge());
        userParametrsDto.setHeight(editUserParametrsDto.getHeight());
        userParametrsDto.setWeight(editUserParametrsDto.getWeight());
        userParametrsDto.setLvlActivity(editUserParametrsDto.getLvlActivity());
        userParametrsDto.setSex(editUserParametrsDto.getSex());


        realm.copyToRealmOrUpdate(userParametrsDto);
        realm.commitTransaction();

        return userParametrsDto;
    }

    @Override
    public void delete(UserParametrsDto userParametrsDto) {
        Log.e("UserParametersService"," : delete(UserParametrsDto userParametrsDto) : delete object" + userParametrsDto);
        realm.beginTransaction();
        userParametrsDto.removeFromRealm();
        realm.commitTransaction();
    }

}
