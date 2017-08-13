package com.example.michal.dietvoltwo.service;


import com.example.michal.dietvoltwo.dto.UserParametrsDto;

import java.util.List;

public interface UserParametersService {

    UserParametrsDto findOne(int age);

    List<UserParametrsDto> findAll();

    UserParametrsDto save(UserParametrsDto userParametrsDto);

    UserParametrsDto edit(UserParametrsDto userParametrsDto);

    void delete(UserParametrsDto userParametrsDto);

}
