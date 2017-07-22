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

import static android.widget.RadioGroup.OnCheckedChangeListener;

public class HealthFragment extends Fragment {
    public static final String TAG = "Stan zdrowia";
    private RadioGroup healthFragmentRadioGrup, diabetsTypeFragmentRadioGrup, dietTypeFragmentRadioGrup;
    private RadioButton healtRadioButton, owerWeightRadioButton, diabetsRadioButton, diabetsTypOneRadioButton, diabetsTypTwoRadioButton, carboDietRadioButton, stabileDietRadioButton, proteinDietRadioButton, fatDietRadioButton;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.health_fragment, container, false);

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



        changeIsDiabetOrHealth(false);

        return view;
    }

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch (i) {
                case R.id.healthRadioButton:
                    changeIsDiabetOrHealth(false);
                    break;
                case R.id.owerWeathTadioButton:
                    changeIsDiabetOrHealth(false);
                    break;
                case R.id.diabetsRadioButton:
                    changeIsDiabetOrHealth(true);
                    break;


                case R.id.typOneRadioButton:
                    Toast.makeText(getActivity(), "typ 1", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.typTwoRadioButton:
                    Toast.makeText(getActivity(), "typ 2", Toast.LENGTH_SHORT).show();
                    break;


                case R.id.carboDietRadioButton:
                    Toast.makeText(getActivity(), "weglowodoanowa", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.stabileDietRadioButton:
                    Toast.makeText(getActivity(), "zrównoważona", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.proteinDietRadioButton:
                    Toast.makeText(getActivity(), "bialkowa", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.fatDietRadioButton:
                    Toast.makeText(getActivity(), "tłuszczowa", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    };

    private void changeIsDiabetOrHealth(boolean isDiabetsOrHealth){
        diabetsTypOneRadioButton.setEnabled(isDiabetsOrHealth);
        diabetsTypTwoRadioButton.setEnabled(isDiabetsOrHealth);
        carboDietRadioButton.setEnabled(!isDiabetsOrHealth);
        proteinDietRadioButton.setEnabled(!isDiabetsOrHealth);
        fatDietRadioButton.setEnabled(!isDiabetsOrHealth);
    }



}
