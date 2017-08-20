package com.example.michal.dietvoltwo.service.Impl;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.michal.dietvoltwo.dto.MealDto;
import com.example.michal.dietvoltwo.dto.MealsDto;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

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

    public RealmResults<MealDto> findAll() {
        return realm.where(MealDto.class).findAll();
    }

    public MealsDto save(MealsDto addMealDto) {
        clearAll();
        for (MealDto addMeals : addMealDto.getMealDtos()) {

            Log.d("SERWIS ====== ",addMeals.getKcalForMeal() + " / " + addMeals.getNumberMeal());
            realm.beginTransaction();
            MealDto mealDto = realm.createObject(MealDto.class);

            mealDto.setKcalForMeal(addMeals.getKcalForMeal());
            mealDto.setNumberMeal(addMeals.getNumberMeal());
            mealDto.setB(addMeals.getB());
            mealDto.setT(addMeals.getT());
            mealDto.setW(addMeals.getW());

            realm.copyToRealm(mealDto);
            realm.commitTransaction();
        }

        return addMealDto;
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
