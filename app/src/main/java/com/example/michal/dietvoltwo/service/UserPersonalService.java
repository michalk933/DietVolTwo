package com.example.michal.dietvoltwo.service;


import com.example.michal.dietvoltwo.dto.UserParametrsDto;
import com.example.michal.dietvoltwo.dto.UserPersonalDto;

import java.util.List;

public interface UserPersonalService {

    UserPersonalDto findOne();

    List<UserPersonalDto> findAll();

    UserPersonalDto save(UserPersonalDto userPersonalDto);

    UserPersonalDto edit(UserPersonalDto userPersonalDto);

    void delete(UserPersonalDto userPersonalDto);

}
