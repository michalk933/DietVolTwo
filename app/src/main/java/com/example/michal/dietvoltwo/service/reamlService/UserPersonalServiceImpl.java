package com.example.michal.dietvoltwo.service.reamlService;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;


import com.example.michal.dietvoltwo.dto.UserPersonalDto;

import java.util.List;

import io.realm.Realm;
import lombok.extern.log4j.Log4j;

@Log4j
public class UserPersonalServiceImpl implements RealmBasisService<UserPersonalDto> {

    private static UserPersonalServiceImpl instance;
    private Realm realm;

    public UserPersonalServiceImpl(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static UserPersonalServiceImpl with(Fragment fragment) {
        log.error("UserPersonalServiceImpl : with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new UserPersonalServiceImpl(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static UserPersonalServiceImpl with(Activity activity) {
        log.error("UserPersonalServiceImpl : with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new UserPersonalServiceImpl(activity.getApplication());
        }
        return instance;
    }

    public static UserPersonalServiceImpl with(Application application) {
        log.error("UserPersonalServiceImpl : with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new UserPersonalServiceImpl(application);
        }
        return instance;
    }

    public static UserPersonalServiceImpl getInstance() {
        log.error("UserPersonalServiceImpl : getInstance : get instance");
        return instance;
    }

    @Override
    public Realm getRealm() {
        log.error("UserPersonalServiceImpl : getRealm() : get realm");
        return this.realm;
    }

    @Override
    public void refresh() {
        log.error("UserPersonalServiceImpl : refresh() : refresh");
        realm.refresh();
    }

    @Override
    public void clearAll() {
        log.error("UserPersonalServiceImpl : clearAll() : clear");
        realm.beginTransaction();
        realm.clear(UserPersonalDto.class);
        realm.commitTransaction();
    }

    @Override
    public UserPersonalDto findOne(int id) {
        log.error("UserPersonalServiceImpl : findOne(int id) : find record in data base");
        return realm.where(UserPersonalDto.class).equalTo("id", id).findFirst();
    }

    @Override
    public List<UserPersonalDto> findAll() {
        log.error("UserPersonalServiceImpl : findAll() : find all records in data base");
        return realm.where(UserPersonalDto.class).findAll();
    }

    @Override
    public UserPersonalDto save(UserPersonalDto addUserPersonalDto) {
        clearAll();
        log.error("UserPersonalServiceImpl : save(UserPersonalDto addUserPersonalDto) : save new object {}" + addUserPersonalDto);
        realm.beginTransaction();
        UserPersonalDto newUserPersonalDto = realm.createObject(UserPersonalDto.class);

        newUserPersonalDto.setId(addUserPersonalDto.getId());
        newUserPersonalDto.seteMail(addUserPersonalDto.geteMail());
        newUserPersonalDto.setRePassword(addUserPersonalDto.getRePassword());
        newUserPersonalDto.setPassword(addUserPersonalDto.getPassword());
        newUserPersonalDto.setLogin(addUserPersonalDto.getLogin());

        realm.copyToRealm(newUserPersonalDto);
        realm.commitTransaction();

        return newUserPersonalDto;
    }

    @Override
    public UserPersonalDto edit(UserPersonalDto editUserPersonalDto, int id) {
        log.error("UserPersonalServiceImpl : edit(UserPersonalDto editUserPersonalDto, int id) : edit object {}" + editUserPersonalDto);
        realm.beginTransaction();

        UserPersonalDto userParametrs = findOne(id);
        userParametrs.setLogin(editUserPersonalDto.getLogin());
        userParametrs.seteMail(editUserPersonalDto.geteMail());
        userParametrs.setRePassword(editUserPersonalDto.getRePassword());
        userParametrs.setPassword(editUserPersonalDto.getPassword());

        realm.copyToRealmOrUpdate(userParametrs);
        realm.commitTransaction();

        return userParametrs;
    }

    @Override
    public void delete(UserPersonalDto userPersonalDto) {
        log.error("UserPersonalServiceImpl : delete(UserPersonalDto userPersonalDto) : delete object {}" + userPersonalDto);
        realm.beginTransaction();
        userPersonalDto.removeFromRealm();
        realm.commitTransaction();
    }
}
