package com.example.michal.dietvoltwo.service.Impl;




import com.example.michal.dietvoltwo.dto.UserParametrsDto;
import com.example.michal.dietvoltwo.service.UserParametersService;

import java.util.List;

import io.realm.Realm;


public class UserParametersServiceImpl implements UserParametersService {

    private Realm realm;

    public UserParametersServiceImpl(Realm realm) {
        this.realm = realm;
    }



    public Realm getRealm() {
        return this.realm;
    }

    public void refresh() {
        realm.refresh();
    }

    public void clearAll() {
        realm.beginTransaction();
        realm.clear(UserParametrsDto.class);
        realm.commitTransaction();
    }


    @Override
    public UserParametrsDto findOne(int age) {
        return realm.where(UserParametrsDto.class).equalTo("age", age).findFirst();
    }

    @Override
    public List<UserParametrsDto> findAll() {
        return realm.where(UserParametrsDto.class).findAll();
    }

    @Override
    public UserParametrsDto save(UserParametrsDto userParametrsDto) {
        this.realm.beginTransaction();

        UserParametrsDto newUserParametrsDto = this.realm.createObject(UserParametrsDto.class);

        newUserParametrsDto.setId((int)System.currentTimeMillis());
        newUserParametrsDto.setWeight(userParametrsDto.getWeight());
        newUserParametrsDto.setHeight(userParametrsDto.getHeight());
        newUserParametrsDto.setAge(userParametrsDto.getAge());
        newUserParametrsDto.setLvlActivity(userParametrsDto.getLvlActivity());
        newUserParametrsDto.setSex(userParametrsDto.getSex());

        this.realm.commitTransaction();
        return userParametrsDto;
    }

    @Override
    public UserParametrsDto edit(UserParametrsDto userParametrsDtoEdit) {

////        UserParametrsDto userParametrsDto = findOne(Long.valueOf(userParametrsDtoEdit.getId()));
//        UserParametrsDto userParametrsDto = findOne(Long.valueOf(userParametrsDtoEdit.getId()));
//        userParametrsDto.setAge(userParametrsDtoEdit.getAge());
//        userParametrsDto.setHeight(userParametrsDtoEdit.getHeight());
//        userParametrsDto.setWeight(userParametrsDtoEdit.getWeight());
//        userParametrsDto.setLvlActivity(userParametrsDtoEdit.getLvlActivity());
//        userParametrsDto.setSex(userParametrsDtoEdit.getSex());
//
//        realm.beginTransaction();
//        realm.copyToRealmOrUpdate(userParametrsDto);
//        realm.commitTransaction();

        return null;
    }

    @Override
    public void delete(UserParametrsDto userParametrsDto) {
        realm.beginTransaction();
        userParametrsDto.removeFromRealm();
        realm.commitTransaction();
    }
}
