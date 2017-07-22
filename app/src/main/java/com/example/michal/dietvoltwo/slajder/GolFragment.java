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

public class GolFragment extends Fragment {
    public static final String TAG = "Cel diety";
    private RadioGroup golRadioGroup;
    private RadioButton reductionRadioButton, keepRadioButton, massRadioButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.gol_fragment, container, false);

        golRadioGroup = (RadioGroup) view.findViewById(R.id.golFragmentRadioGrup);
        reductionRadioButton = (RadioButton) view.findViewById(R.id.reductionGoalRadioButton);
        keepRadioButton = (RadioButton) view.findViewById(R.id.stateGoalRadioButton);
        massRadioButton = (RadioButton) view.findViewById(R.id.massGoalRadioButton);

        golRadioGroup.setOnCheckedChangeListener(onCheckedChangeListener);

        return view;
    }

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch (i) {
                case R.id.reductionGoalRadioButton:
                    Toast.makeText(getActivity(), "redukcja", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.stateGoalRadioButton:
                    Toast.makeText(getActivity(), "utrzymanie", Toast.LENGTH_SHORT).show();
                    break;
                case R.id.massGoalRadioButton:
                    Toast.makeText(getActivity(), "masa", Toast.LENGTH_SHORT).show();
                    break;

            }
        }
    };


}
