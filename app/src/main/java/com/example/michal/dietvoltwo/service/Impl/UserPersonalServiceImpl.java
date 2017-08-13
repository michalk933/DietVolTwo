package com.example.michal.dietvoltwo.service.Impl;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;


import com.example.michal.dietvoltwo.dto.UserPersonalDto;
import com.example.michal.dietvoltwo.service.UserPersonalService;

import java.util.List;

import io.realm.Realm;


public class UserPersonalServiceImpl implements UserPersonalService {

    private static UserPersonalServiceImpl instance;
    private Realm realm;

    public UserPersonalServiceImpl(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static UserPersonalServiceImpl with(Fragment fragment) {

        if (instance == null) {
            instance = new UserPersonalServiceImpl(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static UserPersonalServiceImpl with(Activity activity) {

        if (instance == null) {
            instance = new UserPersonalServiceImpl(activity.getApplication());
        }
        return instance;
    }

    public static UserPersonalServiceImpl with(Application application) {

        if (instance == null) {
            instance = new UserPersonalServiceImpl(application);
        }
        return instance;
    }

    public static UserPersonalServiceImpl getInstance() {

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
        realm.clear(UserPersonalDto.class);
        realm.commitTransaction();
    }


    @Override
    public UserPersonalDto findOne(int id) {
        return realm.where(UserPersonalDto.class).equalTo("id", id).findFirst();
    }

    @Override
    public List<UserPersonalDto> findAll() {
        return realm.where(UserPersonalDto.class).findAll();
    }

    @Override
    public UserPersonalDto save(UserPersonalDto userPersonalDto) {
        UserPersonalDto newUserPersonalDto = realm.createObject(UserPersonalDto.class);

        newUserPersonalDto.setId(userPersonalDto.getId());
        newUserPersonalDto.seteMail(userPersonalDto.geteMail());
        newUserPersonalDto.setRePassword(userPersonalDto.getRePassword());
        newUserPersonalDto.setPassword(userPersonalDto.getPassword());
        newUserPersonalDto.setLogin(userPersonalDto.getLogin());

        realm.beginTransaction();
        realm.copyToRealm(newUserPersonalDto);
        realm.commitTransaction();

        return newUserPersonalDto;
    }

    @Override
    public UserPersonalDto edit(UserPersonalDto editUserPersonalDto, int id) {
        UserPersonalDto userParametrs = findOne(id);
        userParametrs.setLogin(editUserPersonalDto.getLogin());
        userParametrs.seteMail(editUserPersonalDto.geteMail());
        userParametrs.setRePassword(editUserPersonalDto.getRePassword());
        userParametrs.setPassword(editUserPersonalDto.getPassword());

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(userParametrs);
        realm.commitTransaction();

        return userParametrs;
    }

    @Override
    public void delete(UserPersonalDto userPersonalDto) {
        realm.beginTransaction();
        userPersonalDto.removeFromRealm();
        realm.commitTransaction();
    }
}
