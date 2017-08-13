package com.example.michal.dietvoltwo.service.Impl;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.michal.dietvoltwo.dto.UserDto;
import com.example.michal.dietvoltwo.dto.UserGoalDto;
import com.example.michal.dietvoltwo.dto.UserParametrsDto;
import com.example.michal.dietvoltwo.service.UserGoalService;

import java.util.List;

import io.realm.Realm;

public class UserGoalServiceImpl implements UserGoalService {

    private static UserGoalServiceImpl instance;
    private Realm realm;

    public UserGoalServiceImpl(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static UserGoalServiceImpl with(Fragment fragment) {
        if (instance == null) {
            instance = new UserGoalServiceImpl(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static UserGoalServiceImpl with(Activity activity) {
        if (instance == null) {
            instance = new UserGoalServiceImpl(activity.getApplication());
        }
        return instance;
    }

    public static UserGoalServiceImpl with(Application application) {
        if (instance == null) {
            instance = new UserGoalServiceImpl(application);
        }
        return instance;
    }

    public static UserGoalServiceImpl getInstance() {
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
        realm.clear(UserGoalDto.class);
        realm.commitTransaction();
    }


    @Override
    public UserGoalDto findOne(int id) {
        return realm.where(UserGoalDto.class).equalTo("id", id).findFirst();
    }

    @Override
    public List<UserGoalDto> findAll() {
        return realm.where(UserGoalDto.class).findAll();
    }

    @Override
    public UserGoalDto save(UserGoalDto addUserGoal) {
        UserGoalDto newUserGoal = realm.createObject(UserGoalDto.class);

        newUserGoal.setId(addUserGoal.getId());
        newUserGoal.setDiabetsType(addUserGoal.getDiabetsType());
        newUserGoal.setGoal(addUserGoal.getGoal());
        newUserGoal.setHealth(addUserGoal.getHealth());

        realm.beginTransaction();
        realm.copyToRealm(newUserGoal);
        realm.commitTransaction();

        return newUserGoal;
    }

    @Override
    public UserGoalDto edit(UserGoalDto editUserGoal, int id) {

        UserGoalDto userGoal = findOne(id);
        userGoal.setId(editUserGoal.getId());
        userGoal.setDiabetsType(editUserGoal.getDiabetsType());
        userGoal.setTypeDiet(editUserGoal.getTypeDiet());
        userGoal.setHealth(editUserGoal.getHealth());
        userGoal.setGoal(editUserGoal.getGoal());

        realm.beginTransaction();
        realm.copyToRealmOrUpdate(userGoal);
        realm.commitTransaction();

        return userGoal;
    }

    @Override
    public void delete(UserGoalDto userGoalDto) {
        realm.beginTransaction();
        userGoalDto.removeFromRealm();
        realm.commitTransaction();

    }
}
