package com.example.michal.dietvoltwo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.dto.BtwDto;
import com.example.michal.dietvoltwo.dto.MealsDto;
import com.example.michal.dietvoltwo.dto.UserDto;
import com.example.michal.dietvoltwo.dto.UserGoalDto;
import com.example.michal.dietvoltwo.dto.UserParametrsDto;
import com.example.michal.dietvoltwo.service.reamlService.BtwServiceImpl;
import com.example.michal.dietvoltwo.service.reamlService.MealServideImpl;
import com.example.michal.dietvoltwo.service.reamlService.UserGoalServiceImpl;
import com.example.michal.dietvoltwo.service.reamlService.UserParametersServiceImpl;
import com.example.michal.dietvoltwo.service.diet.DietGenerateService;
import com.example.michal.dietvoltwo.service.mealBtw.GenerateBtwMeal;
import com.example.michal.dietvoltwo.service.totalBtw.GenerateBTW;

import io.realm.Realm;

public class DataUserActivity extends AppCompatActivity {

    //Component view
    private SeekBar ageSeekBar, weightSeekbar, heightSeekBar;
    private RadioGroup lvlActivityFragmentRadioGrup, golRadioGroup, dietTypeFragmentRadioGrup;
    private TextView ageTextView, weightTextView, heightTextView;

    //Data to change
    private int age;
    private int weight;
    private int height;
    private int lvlActivity;
    private String goal;
    private String typeDiet;

    //Realm
    private Realm userGoalRealm;
    private Realm userParametrsRealm;

    //Object to change
    private UserGoalDto userGoalDto;
    private UserParametrsDto userParametrsDto;

    private BtwDto btwDto;
    private UserDto userDto;
    private MealsDto mealsDto;
    private Realm realmBtw;
    private Realm realmMeal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Get realm instance
        userGoalRealm = UserGoalServiceImpl.with(this).getRealm();
        UserGoalServiceImpl.with(this).refresh();
        userParametrsRealm = UserParametersServiceImpl.with(this).getRealm();
        UserParametersServiceImpl.with(this).refresh();

        realmBtw = BtwServiceImpl.with(this).getRealm();
        BtwServiceImpl.with(this).refresh();
        realmMeal = MealServideImpl.with(this).getRealm();
        MealServideImpl.with(this).refresh();

        //inicialize object
        userGoalDto = UserGoalServiceImpl.getInstance().findAll().get(0);
        userParametrsDto = UserParametersServiceImpl.getInstance().findAll().get(0);

        //get currency value
        age = userParametrsDto.getAge();
        lvlActivity = userParametrsDto.getLvlActivity();
        weight = userParametrsDto.getWeight();
        height = userParametrsDto.getHeight();
        goal = userGoalDto.getGoal();
        typeDiet = userGoalDto.getTypeDiet();

        //Create view
        createView();

        btwDto = new BtwDto();

        //floatButton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("FINISH TEST", age + " / " + lvlActivity + " / " + weight + " / " + height + " / " + goal + " / " + typeDiet);

                userGoalRealm.beginTransaction();
                userGoalDto.setTypeDiet(typeDiet);
                userGoalDto.setGoal(goal);
                userParametrsDto.setAge(age);
                userParametrsDto.setLvlActivity(lvlActivity);
                userParametrsDto.setHeight(height);
                userParametrsDto.setWeight(weight);
                userGoalRealm.commitTransaction();
                userDto = new UserDto();
                userDto.setUserGoalDto(userGoalDto);
                userDto.setUserParametrsDto(userParametrsDto);


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

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void createView() {
        weightSeekbar = (SeekBar) findViewById(R.id.weight_data_user_seek_bar);
        ageSeekBar = (SeekBar) findViewById(R.id.age_data_user_seek_bar);
        heightSeekBar = (SeekBar) findViewById(R.id.height_data_user_seek_bar);

        lvlActivityFragmentRadioGrup = (RadioGroup) findViewById(R.id.lvl_activity_data_user_radio_grup);
        golRadioGroup = (RadioGroup) findViewById(R.id.gol_fragment_data_user_radio_grup);
        dietTypeFragmentRadioGrup = (RadioGroup) findViewById(R.id.diet_type_data_user_radio_grup);

        ageTextView = (TextView) findViewById(R.id.age_data_user_text_view);
        weightTextView = (TextView) findViewById(R.id.weight_data_user_text_view);
        heightTextView = (TextView) findViewById(R.id.height_data_user_text_view);

        checkLvlActivity();
        checkGoal();
        checkTypeDiet();
        setSeekBar();

        ageSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        weightSeekbar.setOnSeekBarChangeListener(onSeekBarChangeListener);
        heightSeekBar.setOnSeekBarChangeListener(onSeekBarChangeListener);

        lvlActivityFragmentRadioGrup.setOnCheckedChangeListener(onCheckedChangeListener);
        golRadioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
        dietTypeFragmentRadioGrup.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    private void setSeekBar() {
        ageTextView.setText(getResources().getString(R.string.age_text_view) + " " + age + " lat");
        ageSeekBar.setProgress(age);
        weightTextView.setText(getResources().getString(R.string.weight_text_view) + " " + weight + " kg");
        weightSeekbar.setProgress(weight);
        heightTextView.setText(getResources().getString(R.string.height_text_view) + " " + height + " cm");
        heightSeekBar.setProgress(height);
    }

    private void checkTypeDiet() {
        if (typeDiet.equals("WEGLOWODANY")) {
            dietTypeFragmentRadioGrup.check(R.id.carbo_diet_data_user_radio_button);
        } else if (typeDiet.equals("ZROWNOWAZONA")) {
            dietTypeFragmentRadioGrup.check(R.id.stabile_diet_data_user_radio_button);
        } else if (typeDiet.equals("BIALKOWA")) {
            dietTypeFragmentRadioGrup.check(R.id.protein_diet_data_user_radio_button);
        } else {
            dietTypeFragmentRadioGrup.check(R.id.fat_diet_data_user_radio_button);
        }

    }

    private void checkGoal() {
        if (goal.equals("REDUKCJA")) {
            golRadioGroup.check(R.id.reduction_data_user_goal_radio_button);
        } else if (goal.equals("TRZYMANIE")) {
            golRadioGroup.check(R.id.state_data_user_goal_radio_button);
        } else {
            golRadioGroup.check(R.id.mass_data_user_goal_radio_button);
        }
    }

    private void checkLvlActivity() {
        if (lvlActivity == 30) {
            lvlActivityFragmentRadioGrup.check(R.id.lvl_low_activity_data_user_radio_button);
        } else if (lvlActivity == 35) {
            lvlActivityFragmentRadioGrup.check(R.id.lvl_medium_data_user_radio_button);
        } else {
            lvlActivityFragmentRadioGrup.check(R.id.lvl_height_data_user_radio_button);
        }
    }

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch (i) {
                case R.id.reduction_data_user_goal_radio_button:
                    goal = "REDUKCJA";
                    break;
                case R.id.state_data_user_goal_radio_button:
                    goal = "TRZYMANIE";
                    break;
                case R.id.mass_data_user_goal_radio_button:
                    goal = "MASSA";
                    break;


                case R.id.lvl_low_activity_data_user_radio_button:
                    lvlActivity = 30;
                    break;
                case R.id.lvl_medium_data_user_radio_button:
                    lvlActivity = 35;
                    break;
                case R.id.lvl_height_data_user_radio_button:
                    lvlActivity = 40;
                    break;


                case R.id.carbo_diet_data_user_radio_button:
                    typeDiet = "WEGLOWODANY";
                    break;
                case R.id.stabile_diet_data_user_radio_button:
                    typeDiet = "ZROWNOWAZONA";
                    break;
                case R.id.protein_diet_data_user_radio_button:
                    typeDiet = "BIALKOWA";
                    break;
                case R.id.fat_diet_data_user_radio_button:
                    typeDiet = "TLUSZCZOWA";
                    break;
            }
        }
    };

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            switch (seekBar.getId()) {
                case R.id.age_data_user_seek_bar:
                    if (b) {
                        age = ageSeekBar.getProgress();
                        ageTextView.setText(getResources().getString(R.string.age_text_view) + " " + age + " lat");
                    }
                    break;
                case R.id.weight_data_user_seek_bar:
                    if (b) {
                        weight = weightSeekbar.getProgress();
                        weightTextView.setText(getResources().getString(R.string.weight_text_view) + " " + weight + " kg");
                    }
                    break;
                case R.id.height_data_user_seek_bar:
                    if (b) {
                        height = heightSeekBar.getProgress();
                        heightTextView.setText(getResources().getString(R.string.height_text_view) + " " + height + " cm");
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
        userGoalRealm.close();
        userParametrsRealm.close();
    }
}
