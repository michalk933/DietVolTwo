package com.example.michal.dietvoltwo.service.reamlService;


import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.example.michal.dietvoltwo.dto.BtwDto;

import java.util.List;

import io.realm.Realm;
import lombok.extern.log4j.Log4j;

@Log4j
public class BtwServiceImpl implements RealmBasisService<BtwDto> {

    private static BtwServiceImpl instance;
    private Realm realm;

    public BtwServiceImpl(Application application) {
        realm = Realm.getDefaultInstance();
    }

    public static BtwServiceImpl with(Fragment fragment) {
         Log.e("BtwServiceImpl ",": with(Fragment fragment) : get instance");
        if (instance == null) {
            instance = new BtwServiceImpl(fragment.getActivity().getApplication());
        }
        return instance;
    }

    public static BtwServiceImpl with(Activity activity) {
         Log.e("BtwServiceImpl ",": with(Activity activity) : get instance");
        if (instance == null) {
            instance = new BtwServiceImpl(activity.getApplication());
        }
        return instance;
    }

    public static BtwServiceImpl with(Application application) {
         Log.e("BtwServiceImpl ",": with(Application application) : get instance");
        if (instance == null) {
            instance = new BtwServiceImpl(application);
        }
        return instance;
    }

    public static BtwServiceImpl getInstance() {
        return instance;
    }

    @Override
    public Realm getRealm() {
         Log.e("BtwServiceImpl ",": getRealm() : get realm");
        return this.realm;
    }

    @Override
    public void refresh() {
         Log.e("BtwServiceImpl ",": refresh() : refresh");
        realm.refresh();
    }

    @Override
    public void clearAll() {
         Log.e("BtwServiceImpl ",": clearAll() : clear");
        realm.beginTransaction();
        realm.clear(BtwDto.class);
        realm.commitTransaction();
    }

    @Override
    public BtwDto findOne(int id) {
         Log.e("BtwServiceImpl ",": findOne(int id) : find record in data base");
        return realm.where(BtwDto.class).equalTo("id", id).findFirst();
    }

    @Override
    public List<BtwDto> findAll() {
         Log.e("BtwServiceImpl ",": findAll() : find all records in data base");
        return realm.where(BtwDto.class).findAll();
    }

    @Override
    public BtwDto save(BtwDto addBtwDto) {
        clearAll();
         Log.e("BtwServiceImpl ",": save(BtwDto addBtwDto) : save new object" + addBtwDto);
        realm.beginTransaction();
        BtwDto btwDto = realm.createObject(BtwDto.class);

        btwDto.setB(addBtwDto.getB());
        btwDto.setT(addBtwDto.getT());
        btwDto.setW(addBtwDto.getW());
        btwDto.setKcal(addBtwDto.getKcal());

        realm.copyToRealm(btwDto);
        realm.commitTransaction();

        return btwDto;
    }

    @Override
    public BtwDto edit(BtwDto editBtwDto, int id) {
         Log.e("BtwServiceImpl ",": edit(BtwDto editBtwDto, int id) : edit object" + editBtwDto);
        realm.beginTransaction();

        BtwDto btwDto = findOne(id);
        btwDto.setKcal(editBtwDto.getKcal());
        btwDto.setB(editBtwDto.getB());
        btwDto.setT(editBtwDto.getT());
        btwDto.setW(editBtwDto.getW());

        realm.copyToRealmOrUpdate(btwDto);
        realm.commitTransaction();

        return btwDto;
    }

    @Override
    public void delete(BtwDto btwDto) {
         Log.e("BtwServiceImpl ",": delete(BtwDto btwDto) : delete object" + btwDto);
        realm.beginTransaction();
        btwDto.removeFromRealm();
        realm.commitTransaction();
    }

}
