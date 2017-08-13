package com.example.michal.dietvoltwo.service;


import com.example.michal.dietvoltwo.dto.UserParametrsDto;

import java.util.List;

public interface UserParametersService {

    UserParametrsDto findOne(int id);

    List<UserParametrsDto> findAll();

    UserParametrsDto save(UserParametrsDto userParametrsDto);

    UserParametrsDto edit(UserParametrsDto userParametrsDto,int id);

    void delete(UserParametrsDto userParametrsDto);

}
