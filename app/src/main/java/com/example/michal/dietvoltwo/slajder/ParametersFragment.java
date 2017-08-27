package com.example.michal.dietvoltwo.slajder;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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



import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.activity.MealActivity;
import com.example.michal.dietvoltwo.dto.BtwDto;
import com.example.michal.dietvoltwo.dto.MealDto;
import com.example.michal.dietvoltwo.dto.MealsDto;
import com.example.michal.dietvoltwo.dto.UserDto;
import com.example.michal.dietvoltwo.dto.UserGoalDto;
import com.example.michal.dietvoltwo.dto.UserParametrsDto;
import com.example.michal.dietvoltwo.dto.UserPersonalDto;
import com.example.michal.dietvoltwo.service.Impl.BtwServiceImpl;
import com.example.michal.dietvoltwo.service.Impl.MealServideImpl;
import com.example.michal.dietvoltwo.service.Impl.UserGoalServiceImpl;
import com.example.michal.dietvoltwo.service.Impl.UserPersonalServiceImpl;
import com.example.michal.dietvoltwo.service.diet.DietGenerateService;
import com.example.michal.dietvoltwo.service.Impl.UserParametersServiceImpl;
import com.example.michal.dietvoltwo.service.mealBtw.GenerateBtwMeal;
import com.example.michal.dietvoltwo.service.totalBtw.GenerateBTW;


import java.util.List;

import io.realm.Realm;


import static android.widget.SeekBar.OnSeekBarChangeListener;

public class ParametersFragment extends Fragment {
    public static final String TAG = "Kondycja organizmu";
    private RadioGroup sexFragmentRadioGrup, lvlActivityFragmentRadioGrup;
    private SeekBar ageSeekBar, weightSeekbar, heightSeekBar;
    private RadioButton famleRadioButton, maleRadioButton, lowLvlActivityRadioButton, mediumLvlActivityRadioButton, heightLvlActivityRadioButton;
    private TextView ageTextView, weightTextView, heightTextView;
    private Button buttonNext;

    private UserParametrsDto userParametrsDto;
    private UserDto userDto;
    private BtwDto btwDto;
    private MealsDto mealsDto;

    private Realm realm;
    private Realm realmUserGoal;
    private Realm realmUserPersonal;
    private Realm realmBtw;
    private Realm realmMeal;

    private int age = 25;
    private int weight = 75;
    private int height = 178;
    private String sex = "K";
    private int lvlActivity = 35;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.realm = UserParametersServiceImpl.with(this).getRealm();
        UserParametersServiceImpl.with(this).refresh();

        this.realmUserPersonal = UserPersonalServiceImpl.with(this).getRealm();
        UserPersonalServiceImpl.with(this).refresh();

        this.realmUserGoal = UserGoalServiceImpl.with(this).getRealm();
        UserGoalServiceImpl.with(this).refresh();

        this.realmBtw = BtwServiceImpl.with(getActivity()).getRealm();
        BtwServiceImpl.with(getActivity()).refresh();

        this.realmMeal = MealServideImpl.with(this).getRealm();
        MealServideImpl.with(this).refresh();

        btwDto = new BtwDto();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.parameters_fragment, container, false);

        sexFragmentRadioGrup = (RadioGroup) view.findViewById(R.id.sex_fragment_radio_grup);
        lvlActivityFragmentRadioGrup = (RadioGroup) view.findViewById(R.id.lvl_activity_fragment_radioGrup);

        ageTextView = (TextView) view.findViewById(R.id.age_text_view);
        weightTextView = (TextView) view.findViewById(R.id.weight_text_view);
        heightTextView = (TextView) view.findViewById(R.id.height_text_view);

        ageSeekBar = (SeekBar) view.findViewById(R.id.age_seek_bar);
        weightSeekbar = (SeekBar) view.findViewById(R.id.weight_seek_bar);
        heightSeekBar = (SeekBar) view.findViewById(R.id.height_seek_bar);

        famleRadioButton = (RadioButton) view.findViewById(R.id.famle_radio_button);
        maleRadioButton = (RadioButton) view.findViewById(R.id.male_radio_button);
        lowLvlActivityRadioButton = (RadioButton) view.findViewById(R.id.lvl_low_activity_radio_button);
        mediumLvlActivityRadioButton = (RadioButton) view.findViewById(R.id.lvl_medium_radio_button);
        heightLvlActivityRadioButton = (RadioButton) view.findViewById(R.id.lvl_height_radio_button);

        buttonNext = (Button) view.findViewById(R.id.button_next);
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

        userParametrsDto = UserParametrsDto.getUserParametrsDto();
        userParametrsDto.setLvlActivity(lvlActivity);
        userParametrsDto.setSex(sex);
        userParametrsDto.setWeight(weight);
        userParametrsDto.setHeight(height);
        userParametrsDto.setAge(age);

        userDto = new UserDto();

        return view;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            UserPersonalDto userPersonalDto = UserPersonalDto.createUserPersonalDto();
            final UserGoalDto userGoalDto = UserGoalDto.getUserPersonalDto();
            try {
                userDto.setUserPersonalDto(userPersonalDto);
                userDto.setUserGoalDto(userGoalDto);
                userDto.setUserParametrsDto(userParametrsDto);
            } catch (NullPointerException e) {
                Log.d("NULL", e.getLocalizedMessage());
            }


            userParametrsDto.setId((int) System.currentTimeMillis());
            UserParametersServiceImpl.getInstance().save(userParametrsDto);

            userGoalDto.setId((int) System.currentTimeMillis());
            UserGoalServiceImpl.getInstance().save(userGoalDto);

            userPersonalDto.setId((int) System.currentTimeMillis());
            UserPersonalServiceImpl.getInstance().save(userPersonalDto);


            new Handler().post(new Runnable() {
                @Override
                public void run() {
                    try {
                        DietGenerateService dietGenerateService = new DietGenerateService();
                        int dietForUser = dietGenerateService.createDietForUser(userDto);
                        GenerateBTW generateBTW = new GenerateBTW(userDto);
                        BtwDto btw = generateBTW.createBtw(dietForUser);

                        btwDto.setKcal(dietForUser);
                        btwDto.setB(btw.getB());
                        btwDto.setT(btw.getT());
                        btwDto.setW(btw.getW());
                        btwDto.setId((int) System.currentTimeMillis());

                        BtwServiceImpl.getInstance().save(btwDto);

                        GenerateBtwMeal generateBtwMeal = new GenerateBtwMeal();
                        mealsDto = generateBtwMeal.createMeal(userDto,btwDto);
                        MealServideImpl.getInstance().save(mealsDto);

                    } finally {
                        if (realmBtw != null && realmMeal != null) {
                            realmBtw.close();
                            realmMeal.close();
                        }
                    }
                }
            });

            Intent intent = new Intent(getActivity(),MealActivity.class);
            startActivity(intent);
        }
    };

    private OnCheckedChangeListener onCheckedChangeListener = new OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch (i) {
                case R.id.famle_radio_button:
                    sex = "K";
                    userParametrsDto.setSex(sex);
                    break;
                case R.id.male_radio_button:
                    sex = "M";
                    userParametrsDto.setSex(sex);
                    break;


                case R.id.lvl_low_activity_radio_button:
                    lvlActivity = 30;
                    userParametrsDto.setLvlActivity(lvlActivity);
                    break;
                case R.id.lvl_medium_radio_button:
                    lvlActivity = 35;
                    userParametrsDto.setLvlActivity(lvlActivity);
                    break;
                case R.id.lvl_height_radio_button:
                    lvlActivity = 40;
                    userParametrsDto.setLvlActivity(lvlActivity);
                    break;
            }
        }
    };

    private OnSeekBarChangeListener onSeekBarChangeListener = new OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            switch (seekBar.getId()) {
                case R.id.age_seek_bar:
                    if (b) {
                        age = ageSeekBar.getProgress();
                        ageTextView.setText(getResources().getString(R.string.age_text_view) + " " + age + " lat");
                        userParametrsDto.setAge(age);
                    }
                    break;
                case R.id.height_seek_bar:
                    if (b) {
                        height = heightSeekBar.getProgress();
                        heightTextView.setText(getResources().getString(R.string.height_text_view) + " " + height + " cm");
                        userParametrsDto.setHeight(height);
                    }
                    break;
                case R.id.weight_seek_bar:
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
        realmUserGoal.close();
        realmUserPersonal.close();
    }
}
