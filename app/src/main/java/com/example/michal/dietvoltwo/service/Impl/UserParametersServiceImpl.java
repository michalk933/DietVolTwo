package com.example.michal.dietvoltwo.service.Impl;


import com.example.michal.dietvoltwo.dto.UserParametrsDto;
import com.example.michal.dietvoltwo.service.UserParametersService;

import java.util.List;

import io.realm.Realm;

public class UserParametersServiceImpl implements UserParametersService {

    private Realm realm;

    public UserParametersServiceImpl() {
        this.realm = Realm.getDefaultInstance();
    }

    @Override
    public UserParametrsDto findOne(Long id) {


        return null;
    }

    @Override
    public List<UserParametrsDto> findAll() {
        return null;
    }

    @Override
    public UserParametrsDto save(UserParametrsDto userParametrsDto) {
        return null;
    }

    @Override
    public UserParametrsDto edit(UserParametrsDto userParametrsDto) {
        return null;
    }

    @Override
    public void delete(UserParametrsDto userParametrsDto) {

    }
}
