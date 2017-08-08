package com.example.michal.dietvoltwo.slajder;


import android.content.pm.ProviderInfo;
import android.icu.lang.UScript;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.dto.UserDto;
import com.example.michal.dietvoltwo.dto.UserGoalDto;
import com.example.michal.dietvoltwo.dto.UserParametrsDto;
import com.example.michal.dietvoltwo.dto.UserPersonalDto;

import io.realm.Realm;
import io.realm.RealmResults;

import static android.widget.SeekBar.OnSeekBarChangeListener;

public class ParametersFragment extends Fragment {
    public static final String TAG = "Kondycja organizmu";
    private RadioGroup sexFragmentRadioGrup, lvlActivityFragmentRadioGrup;
    private SeekBar ageSeekBar, weightSeekbar, heightSeekBar;
    private RadioButton famleRadioButton, maleRadioButton, lowLvlActivityRadioButton, mediumLvlActivityRadioButton, heightLvlActivityRadioButton;
    private TextView ageTextView, weightTextView, heightTextView;
    private Button buttonNext;

    private int age = 25;
    private int weight = 75;
    private int height = 178;

    private UserParametrsDto userParametrsDto;
    private UserDto userDto;

    private Realm realm;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.parameters_fragment, container, false);

        sexFragmentRadioGrup = (RadioGroup) view.findViewById(R.id.sexFragmentRadioGrup);
        lvlActivityFragmentRadioGrup = (RadioGroup) view.findViewById(R.id.lvlActivityFragmentRadioGrup);

        ageTextView = (TextView) view.findViewById(R.id.ageTextView);
        weightTextView = (TextView) view.findViewById(R.id.weightTextView);
        heightTextView = (TextView) view.findViewById(R.id.heightTextView);

        ageSeekBar = (SeekBar) view.findViewById(R.id.ageSeekBar);
        weightSeekbar = (SeekBar) view.findViewById(R.id.weightSeekBar);
        heightSeekBar = (SeekBar) view.findViewById(R.id.heightSeekBar);

        famleRadioButton = (RadioButton) view.findViewById(R.id.famleRadioButton);
        maleRadioButton = (RadioButton) view.findViewById(R.id.maleRadioButton);
        lowLvlActivityRadioButton = (RadioButton) view.findViewById(R.id.lvlLowActivityRadioButton);
        mediumLvlActivityRadioButton = (RadioButton) view.findViewById(R.id.lvlMediumRadioButton);
        heightLvlActivityRadioButton = (RadioButton) view.findViewById(R.id.lvlHeightRadioButton);

        buttonNext = (Button) view.findViewById(R.id.buttonNext);
        buttonNext.setOnClickListener(onClickListener);

        sexFragmentRadioGrup.setOnCheckedChangeListener(onCheckedChangeListener);
        lvlActivityFragmentRadioGrup.setOnCheckedChangeListener(onCheckedChangeListener);
        ageSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        weightSeekbar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        heightSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);

        ageSeekBar.setProgress(age);
        weightSeekbar.setProgress(weight);
        heightSeekBar.setProgress(height);

        ageTextView.setText(getResources().getString(R.string.age_text_view) + " " + age + " lat");
        heightTextView.setText(getResources().getString(R.string.height_text_view) + " " + height + " cm");
        weightTextView.setText(getResources().getString(R.string.weight_text_view) + " " + weight + " kg");


        userDto = new UserDto();
        userParametrsDto = UserParametrsDto.getUserParametrsDto();

        realm = Realm.getDefaultInstance();

        return view;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            UserPersonalDto userPersonalDto = UserPersonalDto.createUserPersonalDto();
            UserGoalDto userGoalDto = UserGoalDto.getUserPersonalDto();
            try {
                userDto.setUserPersonalDto(userPersonalDto);
                userDto.setUserGoalDto(userGoalDto);
                userDto.setUserParametrsDto(userParametrsDto);
            } catch (NullPointerException e) {
                Log.d("NULL", e.getLocalizedMessage());
            }

            saveInDataBase(userDto);
            refreshView();

        }
    };

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch (i) {
                case R.id.famleRadioButton:
                    userParametrsDto.setSex("K");
                    break;
                case R.id.maleRadioButton:
                    userParametrsDto.setSex("M");
                    break;


                case R.id.lvlLowActivityRadioButton:
                    userParametrsDto.setLvlActivity(30);
                    break;
                case R.id.lvlMediumRadioButton:
                    userParametrsDto.setLvlActivity(35);
                    break;
                case R.id.lvlHeightRadioButton:
                    userParametrsDto.setLvlActivity(40);
                    break;
            }
        }
    };

    private OnSeekBarChangeListener onSeekBarChangeListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            switch (seekBar.getId()) {
                case R.id.ageSeekBar:
                    if (b) {
                        age = ageSeekBar.getProgress();
                        ageTextView.setText(getResources().getString(R.string.age_text_view) + " " + age + " lat");
                        userParametrsDto.setAge(age);
                    }
                    break;
                case R.id.heightSeekBar:
                    if (b) {
                        height = heightSeekBar.getProgress();
                        heightTextView.setText(getResources().getString(R.string.height_text_view) + " " + height + " cm");
                        userParametrsDto.setHeight(height);
                    }
                    break;
                case R.id.weightSeekBar:
                    if (b) {
                        weight = weightSeekbar.getProgress();
                        weightTextView.setText(getResources().getString(R.string.weight_text_view) + " " + weight + " kg");
                        userParametrsDto.setWeight(weight);
                    }
                    break;
            }
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };

    private void saveInDataBase(final UserDto userDto) {

//        realm.delete(UserDto.class);
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {

                UserPersonalDto userPersonalDto = bgRealm.createObject(UserPersonalDto.class);
                userPersonalDto.setLogin(userDto.getUserPersonalDto().getLogin());
                userPersonalDto.setPassword(userDto.getUserPersonalDto().getPassword());
                userPersonalDto.setRePassword(userDto.getUserPersonalDto().getRePassword());
                userPersonalDto.setEMail(userDto.getUserPersonalDto().getEMail());

                UserParametrsDto userParametrsDto = bgRealm.createObject(UserParametrsDto.class);
                userParametrsDto.setLvlActivity(userDto.getUserParametrsDto().getLvlActivity());
                userParametrsDto.setAge(userDto.getUserParametrsDto().getAge());
                userParametrsDto.setWeight(userDto.getUserParametrsDto().getWeight());
                userParametrsDto.setHeight(userDto.getUserParametrsDto().getHeight());
                userParametrsDto.setSex(userDto.getUserParametrsDto().getSex());

                UserGoalDto userGoalDto = bgRealm.createObject(UserGoalDto.class);
                userGoalDto.setDiabetsType(userDto.getUserGoalDto().getDiabetsType());
                userGoalDto.setGoal(userDto.getUserGoalDto().getGoal());
                userGoalDto.setHealth(userDto.getUserGoalDto().getHealth());
                userGoalDto.setTypeDiet(userDto.getUserGoalDto().getTypeDiet());

            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                // Transaction was a success.
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                // Transaction failed and was automatically canceled.
            }
        });

    }


    private void refreshView() {
//        UserDto first = realm.where(UserDto.class).findFirst();
//        Toast.makeText(getContext(), first.toString(), Toast.LENGTH_LONG).show();

        RealmResults<UserDto> all = realm.where(UserDto.class).findAll();
        for (UserDto dto : all) {
            Log.d("KURWA", dto.getUserGoalDto().toString());
            Log.d("KURWA", dto.getUserPersonalDto().toString());
            Log.d("KURWA", dto.getUserParametrsDto().toString());
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
