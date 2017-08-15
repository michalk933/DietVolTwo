package com.example.michal.dietvoltwo.adapter;


import android.content.Context;

import com.example.michal.dietvoltwo.dto.MealDto;


import io.realm.RealmResults;

public class RealmMealAdapter extends RealmModelAdapter<MealDto> {

    public RealmMealAdapter(Context context, RealmResults<MealDto> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }
}
