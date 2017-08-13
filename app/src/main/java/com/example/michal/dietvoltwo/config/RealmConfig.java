package com.example.michal.dietvoltwo.config;


import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class RealmConfig extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("paramert.reaml")
                .build();
        Realm.setDefaultConfiguration(realmConfiguration);

    }
}
