package com.example.michal.dietvoltwo.service.Impl;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.michal.dietvoltwo.dto.MealDto;

import java.util.List;

import io.realm.Realm;

public class MealServideImpl {

    private static MealServideImpl instance;
    private Realm realm;

    public MealServideImpl(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static MealServideImpl with(Fragment fragment) {
        if (instance == null) {
            instance = new MealServideImpl(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static MealServideImpl with(Activity activity) {
        if (instance == null) {
            instance = new MealServideImpl(activity.getApplication());
        }
        return instance;
    }

    public static MealServideImpl with(Application application) {
        if (instance == null) {
            instance = new MealServideImpl(application);
        }
        return instance;
    }

    public static MealServideImpl getInstance() {
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
        realm.clear(MealDto.class);
        realm.commitTransaction();
    }


    public MealDto findOne(int id) {
        return realm.where(MealDto.class).equalTo("id", id).findFirst();
    }

    public List<MealDto> findAll() {
        return realm.where(MealDto.class).findAll();
    }

    public MealDto save(MealDto addMealDto) {
        realm.beginTransaction();
        MealDto mealDto = realm.createObject(MealDto.class);

        mealDto.setKcalForMeal(addMealDto.getKcalForMeal());
        mealDto.setNumberMeal(addMealDto.getNumberMeal());
        mealDto.setB(addMealDto.getB());
        mealDto.setT(addMealDto.getT());
        mealDto.setW(addMealDto.getW());

        realm.copyToRealm(mealDto);
        realm.commitTransaction();

        return mealDto;
    }

    public MealDto edit(MealDto editMealDto, int id) {
        realm.beginTransaction();

        MealDto mealDto = findOne(id);
        mealDto.setKcalForMeal(editMealDto.getKcalForMeal());
        mealDto.setNumberMeal(editMealDto.getNumberMeal());
        mealDto.setB(editMealDto.getB());
        mealDto.setT(editMealDto.getT());
        mealDto.setW(editMealDto.getW());

        realm.copyToRealmOrUpdate(mealDto);
        realm.commitTransaction();

        return mealDto;
    }

    public void delete(MealDto mealDto) {
        realm.beginTransaction();
        mealDto.removeFromRealm();
        realm.commitTransaction();

    }

















}
