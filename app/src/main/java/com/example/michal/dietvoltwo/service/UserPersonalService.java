package com.example.michal.dietvoltwo.service;


import com.example.michal.dietvoltwo.dto.UserPersonalDto;

import java.util.List;

public interface UserPersonalService {

    UserPersonalDto findOne(int id);

    List<UserPersonalDto> findAll();

    UserPersonalDto save(UserPersonalDto userPersonalDto);

    UserPersonalDto edit(UserPersonalDto userPersonalDto,int id);

    void delete(UserPersonalDto userPersonalDto);

}
