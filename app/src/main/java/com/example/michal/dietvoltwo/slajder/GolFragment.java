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
import com.example.michal.dietvoltwo.util.Constant;

import static android.widget.RadioGroup.OnCheckedChangeListener;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_DIET_KEEP;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_DIET_MASS;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_DIET_REDUCTION;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_HEALTH_DIABETS;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_HEALTH_HEALTH;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_HEALTH_OWER_WEIGHT;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_TYPE_DIABETS_ONE;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_TYPE_DIABETS_TWO;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_TYPE_DIETS_CARBOHYDRATE;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_TYPE_DIETS_FAT;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_TYPE_DIETS_PROTEIN;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_TYPE_DIETS_STABILE;

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
        userGoalDto.setGoal(GOAL_DIET_KEEP);
        userGoalDto.setHealth(GOAL_HEALTH_HEALTH);
        userGoalDto.setDiabetsType(GOAL_TYPE_DIABETS_ONE);
        userGoalDto.setTypeDiet(GOAL_TYPE_DIETS_STABILE);


        changeIsDiabetOrHealth(false);

        return view;
    }

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch (i) {
                case R.id.reductionGoalRadioButton:
                    userGoalDto.setGoal(GOAL_DIET_REDUCTION);
                    break;
                case R.id.stateGoalRadioButton:
                    userGoalDto.setGoal(GOAL_DIET_KEEP);
                    break;
                case R.id.massGoalRadioButton:
                    userGoalDto.setGoal(GOAL_DIET_MASS);
                    break;


                case R.id.healthRadioButton:
                    changeIsDiabetOrHealth(false);
                    userGoalDto.setHealth(GOAL_HEALTH_HEALTH);
                    break;
                case R.id.owerWeathTadioButton:
                    changeIsDiabetOrHealth(false);
                    userGoalDto.setHealth(GOAL_HEALTH_OWER_WEIGHT);
                    break;
                case R.id.diabetsRadioButton:
                    changeIsDiabetOrHealth(true);
                    userGoalDto.setHealth(GOAL_HEALTH_DIABETS);
                    break;


                case R.id.typOneRadioButton:
                    userGoalDto.setDiabetsType(GOAL_TYPE_DIABETS_ONE);
                    break;
                case R.id.typTwoRadioButton:
                    userGoalDto.setDiabetsType(GOAL_TYPE_DIABETS_TWO);
                    break;


                case R.id.carboDietRadioButton:
                    userGoalDto.setTypeDiet(GOAL_TYPE_DIETS_CARBOHYDRATE);
                    break;
                case R.id.stabileDietRadioButton:
                    userGoalDto.setTypeDiet(GOAL_TYPE_DIETS_STABILE);
                    break;
                case R.id.proteinDietRadioButton:
                    userGoalDto.setTypeDiet(GOAL_TYPE_DIETS_PROTEIN);
                    break;
                case R.id.fatDietRadioButton:
                    userGoalDto.setTypeDiet(GOAL_TYPE_DIETS_FAT);
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
}
