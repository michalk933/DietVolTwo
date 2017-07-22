package com.example.michal.dietvoltwo.slajder;


import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.dto.UserGoalDto;

import static android.widget.RadioGroup.OnCheckedChangeListener;

public class GolFragment extends Fragment {
    public static final String TAG = "Cel diety";
    private RadioGroup golRadioGroup, healthFragmentRadioGrup, diabetsTypeFragmentRadioGrup, dietTypeFragmentRadioGrup;
    private RadioButton reductionRadioButton, keepRadioButton, massRadioButton, healtRadioButton, owerWeightRadioButton,
            diabetsRadioButton, diabetsTypOneRadioButton, diabetsTypTwoRadioButton, carboDietRadioButton,
            stabileDietRadioButton, proteinDietRadioButton, fatDietRadioButton;

    private static UserGoalDto userGoalDto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gol_fragment, container, false);

        golRadioGroup = (RadioGroup) view.findViewById(R.id.golFragmentRadioGrup);
        reductionRadioButton = (RadioButton) view.findViewById(R.id.reductionGoalRadioButton);
        keepRadioButton = (RadioButton) view.findViewById(R.id.stateGoalRadioButton);
        massRadioButton = (RadioButton) view.findViewById(R.id.massGoalRadioButton);

        golRadioGroup.setOnCheckedChangeListener(onCheckedChangeListener);

        healthFragmentRadioGrup = (RadioGroup) view.findViewById(R.id.healthFragmentRadioGrup);
        diabetsTypeFragmentRadioGrup = (RadioGroup) view.findViewById(R.id.DiabetsTypeFragmentRadioGrup);
        dietTypeFragmentRadioGrup = (RadioGroup) view.findViewById(R.id.DietTypeFragmentRadioGrup);

        healtRadioButton = (RadioButton) view.findViewById(R.id.healthRadioButton);
        owerWeightRadioButton = (RadioButton) view.findViewById(R.id.owerWeathTadioButton);
        diabetsRadioButton = (RadioButton) view.findViewById(R.id.diabetsRadioButton);
        diabetsTypOneRadioButton = (RadioButton) view.findViewById(R.id.typOneRadioButton);
        diabetsTypTwoRadioButton = (RadioButton) view.findViewById(R.id.typTwoRadioButton);
        carboDietRadioButton = (RadioButton) view.findViewById(R.id.carboDietRadioButton);
        stabileDietRadioButton = (RadioButton) view.findViewById(R.id.stabileDietRadioButton);
        proteinDietRadioButton = (RadioButton) view.findViewById(R.id.proteinDietRadioButton);
        fatDietRadioButton = (RadioButton) view.findViewById(R.id.fatDietRadioButton);

        healthFragmentRadioGrup.setOnCheckedChangeListener(onCheckedChangeListener);
        diabetsTypeFragmentRadioGrup.setOnCheckedChangeListener(onCheckedChangeListener);
        dietTypeFragmentRadioGrup.setOnCheckedChangeListener(onCheckedChangeListener);

        userGoalDto = UserGoalDto.getUserPersonalDto();
        userGoalDto.setGoal("TRZYMANIE");
        userGoalDto.setHealth("ZDROWY");
        userGoalDto.setDiabetsType("ONE");
        userGoalDto.setTypeDiet("ZROWNOWAZONA");



        changeIsDiabetOrHealth(false);

        return view;
    }

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch (i) {
                case R.id.reductionGoalRadioButton:
                    userGoalDto.setGoal("REDUKCJA");
                    break;
                case R.id.stateGoalRadioButton:
                    userGoalDto.setGoal("TRZYMANIE");
                    break;
                case R.id.massGoalRadioButton:
                    userGoalDto.setGoal("MASSA");
                    break;


                case R.id.healthRadioButton:
                    changeIsDiabetOrHealth(false);
                    userGoalDto.setHealth("ZDROWY");
                    break;
                case R.id.owerWeathTadioButton:
                    changeIsDiabetOrHealth(false);
                    userGoalDto.setHealth("OTYLY");
                    break;
                case R.id.diabetsRadioButton:
                    changeIsDiabetOrHealth(true);
                    userGoalDto.setHealth("DIABETYK");
                    break;


                case R.id.typOneRadioButton:
                    userGoalDto.setDiabetsType("ONE");
                    break;
                case R.id.typTwoRadioButton:
                    userGoalDto.setDiabetsType("TWO");
                    break;


                case R.id.carboDietRadioButton:
                    userGoalDto.setTypeDiet("WEGLOWODANY");
                    break;
                case R.id.stabileDietRadioButton:
                    userGoalDto.setTypeDiet("ZROWNOWAZONA");
                    break;
                case R.id.proteinDietRadioButton:
                    userGoalDto.setTypeDiet("BIALKOWA");
                    break;
                case R.id.fatDietRadioButton:
                    userGoalDto.setTypeDiet("TLUSZCZOWA");
                    break;
            }
        }
    };

    private void changeIsDiabetOrHealth(boolean isDiabetsOrHealth) {
        diabetsTypOneRadioButton.setEnabled(isDiabetsOrHealth);
        diabetsTypTwoRadioButton.setEnabled(isDiabetsOrHealth);
        carboDietRadioButton.setEnabled(!isDiabetsOrHealth);
        proteinDietRadioButton.setEnabled(!isDiabetsOrHealth);
        fatDietRadioButton.setEnabled(!isDiabetsOrHealth);
    }


    public static UserGoalDto getUserGoalDto() {
        return userGoalDto;
    }

}
