package com.example.michal.dietvoltwo.service;

import com.example.michal.dietvoltwo.dto.UserGoalDto;


import java.util.List;


public interface UserGoalService {

    UserGoalDto findOne();

    List<UserGoalDto> findAll();

    UserGoalDto save(UserGoalDto userGoalDto);

    UserGoalDto edit(UserGoalDto userGoalDto);

    void delete(UserGoalDto userGoalDto);
}
