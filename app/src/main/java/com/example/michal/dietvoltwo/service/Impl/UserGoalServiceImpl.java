package com.example.michal.dietvoltwo.service.Impl;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.michal.dietvoltwo.dto.UserGoalDto;




import java.util.List;

import io.realm.Realm;


public class UserGoalServiceImpl implements RealmBasisService<UserGoalDto> {

    private static UserGoalServiceImpl instance;
    private Realm realm;

    public UserGoalServiceImpl(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static UserGoalServiceImpl with(Fragment fragment) {
        Log.e("UserGoalServiceImpl : "," with(Fragment fragment) :get instance");
        if (instance == null) {
            instance = new UserGoalServiceImpl(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static UserGoalServiceImpl with(Activity activity) {
        Log.e("UserGoalServiceImpl ",": with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new UserGoalServiceImpl(activity.getApplication());
        }
        return instance;
    }

    public static UserGoalServiceImpl with(Application application) {
        Log.e("UserGoalServiceImpl ",": with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new UserGoalServiceImpl(application);
        }
        return instance;
    }

    public static UserGoalServiceImpl getInstance() {
        Log.e("UserGoalServiceImpl ", ": getInstance : get instance");
        return instance;
    }

    @Override
    public Realm getRealm() {
        Log.e("UserGoalServiceImpl ",": getRealm() : get realm");
        return this.realm;
    }

    @Override
    public void refresh() {
        Log.e("UserGoalServiceImpl ",": refresh() : refresh");
        realm.refresh();
    }

    @Override
    public void clearAll() {
        Log.e("UserGoalServiceImpl ",": clearAll() : clear");
        realm.beginTransaction();
        realm.clear(UserGoalDto.class);
        realm.commitTransaction();
    }

    @Override
    public UserGoalDto findOne(int id) {
        Log.e("UserGoalServiceImpl ",": findOne(int id) : find record in data base");
        return realm.where(UserGoalDto.class).equalTo("id", id).findFirst();
    }

    @Override
    public List<UserGoalDto> findAll() {
        Log.e("UserGoalServiceImpl ",": findAll() : find all records in data base");
        return realm.where(UserGoalDto.class).findAll();
    }

    @Override
    public UserGoalDto save(UserGoalDto addUserGoal) {
        clearAll();
        Log.e("UserGoalServiceImpl ", ": save(UserGoalDto addUserGoal) : save new object" + addUserGoal);
        realm.beginTransaction();
        UserGoalDto newUserGoal = realm.createObject(UserGoalDto.class);

        newUserGoal.setId(addUserGoal.getId());
        newUserGoal.setDiabetsType(addUserGoal.getDiabetsType());
        newUserGoal.setTypeDiet(addUserGoal.getTypeDiet());
        newUserGoal.setGoal(addUserGoal.getGoal());
        newUserGoal.setHealth(addUserGoal.getHealth());

        realm.copyToRealm(newUserGoal);
        realm.commitTransaction();

        return newUserGoal;
    }

    @Override
    public UserGoalDto edit(UserGoalDto editUserGoal, int id) {
        Log.e("UserGoalServiceImpl ",": edit(UserGoalDto editUserGoal, int id) : edit object " + editUserGoal);
        realm.beginTransaction();

        UserGoalDto userGoal = findOne(id);
        userGoal.setId(editUserGoal.getId());
        userGoal.setDiabetsType(editUserGoal.getDiabetsType());
        userGoal.setTypeDiet(editUserGoal.getTypeDiet());
        userGoal.setHealth(editUserGoal.getHealth());
        userGoal.setGoal(editUserGoal.getGoal());

        realm.copyToRealmOrUpdate(userGoal);
        realm.commitTransaction();

        return userGoal;
    }

    @Override
    public void delete(UserGoalDto userGoalDto) {
        Log.e("UserGoalServiceImpl ",": delete(UserGoalDto userGoalDto) : delete object" + userGoalDto);
        realm.beginTransaction();
        userGoalDto.removeFromRealm();
        realm.commitTransaction();

    }
}
