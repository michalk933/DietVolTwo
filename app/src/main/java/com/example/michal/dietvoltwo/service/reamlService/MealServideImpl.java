package com.example.michal.dietvoltwo.service.reamlService;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.michal.dietvoltwo.dto.MealDto;
import com.example.michal.dietvoltwo.dto.MealsDto;

import io.realm.Realm;
import io.realm.RealmResults;
import lombok.extern.log4j.Log4j;

@Log4j
public class MealServideImpl implements RealmBasisService<MealDto> {

    private static MealServideImpl instance;
    private Realm realm;

    public MealServideImpl(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static MealServideImpl with(Fragment fragment) {
        log.error("MealServideImpl : with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new MealServideImpl(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static MealServideImpl with(Activity activity) {
        log.error("MealServideImpl : with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new MealServideImpl(activity.getApplication());
        }
        return instance;
    }

    public static MealServideImpl with(Application application) {
        log.error("MealServideImpl : with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new MealServideImpl(application);
        }
        return instance;
    }

    public static MealServideImpl getInstance() {
        log.error("MealServideImpl : getInstance : get instance");
        return instance;
    }

    @Override
    public Realm getRealm() {
        log.error("MealServideImpl : getRealm() : get realm");
        return this.realm;
    }

    @Override
    public void refresh() {
        log.error("MealServideImpl : refresh() : refresh");
        realm.refresh();
    }

    @Override
    public void clearAll() {
        log.error("MealServideImpl : clearAll() : clear");
        realm.beginTransaction();
        realm.clear(MealDto.class);
        realm.commitTransaction();
    }

    @Override
    public MealDto findOne(int id) {
        log.error("MealServideImpl : findOne(int id) : find record in data base");
        return realm.where(MealDto.class).equalTo("id", id).findFirst();
    }

    @Override
    public RealmResults<MealDto> findAll() {
        log.error("MealServideImpl : findAll() : find all records in data base");
        return realm.where(MealDto.class).findAll();
    }

    public MealsDto save(MealsDto addMealDto) {
        log.error("MealServideImpl : save(MealsDto addMealDto) : save new object {}" + addMealDto);
        clearAll();
        for (MealDto addMeals : addMealDto.getMealDtos()) {
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

    @Override
    public MealDto edit(MealDto editMealDto, int id) {
        log.error("MealServideImpl : edit(MealDto editMealDto, int id) : edit object {}" + editMealDto);
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

    @Override
    public MealDto save(MealDto mealDto) {
        return null;
    }

    @Override
    public void delete(MealDto mealDto) {
        log.error("MealServideImpl : delete(MealDto mealDto) : delete object {}" + mealDto);
        realm.beginTransaction();
        mealDto.removeFromRealm();
        realm.commitTransaction();

    }


}
