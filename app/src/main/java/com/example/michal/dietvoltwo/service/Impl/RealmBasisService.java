package com.example.michal.dietvoltwo.service.Impl;


import java.util.List;

import io.realm.Realm;

public interface RealmBasisService<T> {

    void delete(T t);

    T edit(T t, int id);

    T save(T t);

    List<T> findAll();

    T findOne(int id);

    void clearAll();

    void refresh();

    Realm getRealm();
}
