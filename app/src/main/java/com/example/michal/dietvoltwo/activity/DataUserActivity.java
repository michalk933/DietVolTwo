package com.example.michal.dietvoltwo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;

import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.dto.BtwDto;
import com.example.michal.dietvoltwo.dto.MealDto;
import com.example.michal.dietvoltwo.dto.UserDto;
import com.example.michal.dietvoltwo.dto.UserGoalDto;
import com.example.michal.dietvoltwo.dto.UserParametrsDto;

import com.example.michal.dietvoltwo.service.diet.DietGenerateService;
import com.example.michal.dietvoltwo.service.mealBtw.GenerateBtwMeal;
import com.example.michal.dietvoltwo.service.totalBtw.GenerateBTW;

import java.util.List;

import io.realm.Realm;

import static com.example.michal.dietvoltwo.util.Constant.GOAL_DIET_KEEP;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_DIET_MASS;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_DIET_REDUCTION;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_TYPE_DIETS_CARBOHYDRATE;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_TYPE_DIETS_FAT;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_TYPE_DIETS_PROTEIN;
import static com.example.michal.dietvoltwo.util.Constant.GOAL_TYPE_DIETS_STABILE;
import static com.example.michal.dietvoltwo.util.Constant.PARAMETER_LVL_ACTIVITY_HEIGHT;
import static com.example.michal.dietvoltwo.util.Constant.PARAMETER_LVL_ACTIVITY_LOW;
import static com.example.michal.dietvoltwo.util.Constant.PARAMETER_LVL_ACTIVITY_MEDIUM;

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

    //Object to change
    private UserGoalDto userGoalDto;
    private UserParametrsDto userParametrsDto;

    private UserDto userDto;

    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        realm = Realm.getDefaultInstance();

        //inicialize object
        userGoalDto = realm.where(UserGoalDto.class).findFirst();
        userParametrsDto = realm.where(UserParametrsDto.class).findFirst();

        //get currency value
        age = userParametrsDto.getAge();
        lvlActivity = userParametrsDto.getLvlActivity();
        weight = userParametrsDto.getWeight();
        height = userParametrsDto.getHeight();
        goal = userGoalDto.getGoal();
        typeDiet = userGoalDto.getTypeDiet();

        //Create view
        createView();


        //floatButton
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                realm.beginTransaction();
                userGoalDto.setTypeDiet(typeDiet);
                userGoalDto.setGoal(goal);
                userParametrsDto.setAge(age);
                userParametrsDto.setLvlActivity(lvlActivity);
                userParametrsDto.setHeight(height);
                userParametrsDto.setWeight(weight);
                realm.commitTransaction();
                userDto = new UserDto();
                userDto.setUserGoalDto(userGoalDto);
                userDto.setUserParametrsDto(userParametrsDto);

                new Handler().post(new Runnable() {
                    @Override
                    public void run() {

                        DietGenerateService dietGenerateService = new DietGenerateService();
                        int dietForUser = dietGenerateService.createDietForUser(userDto);
                        GenerateBTW generateBTW = new GenerateBTW(userDto);

                        BtwDto btw = generateBTW.createBtw(dietForUser);
                        realm.beginTransaction();
                        BtwDto newBtwDto = realm.createObject(BtwDto.class);
                        newBtwDto.setKcal(dietForUser);
                        newBtwDto.setB(btw.getB());
                        newBtwDto.setT(btw.getT());
                        newBtwDto.setW(btw.getW());
                        newBtwDto.setId((int) System.currentTimeMillis());

                        GenerateBtwMeal generateBtwMeal = new GenerateBtwMeal();
                        List<MealDto> mealDtoList = generateBtwMeal.createMeal(userDto, btw).getMealDtos();
                        for (MealDto meal : mealDtoList) {
                            MealDto mealDto = realm.createObject(MealDto.class);
                            mealDto.setB(meal.getB());
                            mealDto.setKcalForMeal(meal.getKcalForMeal());
                            mealDto.setNumberMeal(meal.getNumberMeal());
                            mealDto.setT(meal.getT());
                            mealDto.setW(meal.getW());
                        }
                        realm.commitTransaction();
                    }
                });
                Toast.makeText(DataUserActivity.this, "Zmieniono dane użytkownika", Toast.LENGTH_SHORT).show();
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
        if (typeDiet.equals(GOAL_TYPE_DIETS_CARBOHYDRATE)) {
            dietTypeFragmentRadioGrup.check(R.id.carbo_diet_data_user_radio_button);
        } else if (typeDiet.equals(GOAL_TYPE_DIETS_STABILE)) {
            dietTypeFragmentRadioGrup.check(R.id.stabile_diet_data_user_radio_button);
        } else if (typeDiet.equals(GOAL_TYPE_DIETS_PROTEIN)) {
            dietTypeFragmentRadioGrup.check(R.id.protein_diet_data_user_radio_button);
        } else if (typeDiet.equals(GOAL_TYPE_DIETS_FAT)) {
            dietTypeFragmentRadioGrup.check(R.id.fat_diet_data_user_radio_button);
        }

    }

    private void checkGoal() {
        if (goal.equals(GOAL_DIET_REDUCTION)) {
            golRadioGroup.check(R.id.reduction_data_user_goal_radio_button);
        } else if (goal.equals(GOAL_DIET_KEEP)) {
            golRadioGroup.check(R.id.state_data_user_goal_radio_button);
        } else if (goal.equals(GOAL_DIET_MASS)) {
            golRadioGroup.check(R.id.mass_data_user_goal_radio_button);
        }
    }

    private void checkLvlActivity() {
        if (lvlActivity == PARAMETER_LVL_ACTIVITY_LOW) {
            lvlActivityFragmentRadioGrup.check(R.id.lvl_low_activity_data_user_radio_button);
        } else if (lvlActivity == PARAMETER_LVL_ACTIVITY_MEDIUM) {
            lvlActivityFragmentRadioGrup.check(R.id.lvl_medium_data_user_radio_button);
        } else if (lvlActivity == PARAMETER_LVL_ACTIVITY_HEIGHT) {
            lvlActivityFragmentRadioGrup.check(R.id.lvl_height_data_user_radio_button);
        }
    }

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch (i) {
                case R.id.reduction_data_user_goal_radio_button:
                    goal = GOAL_DIET_REDUCTION;
                    break;
                case R.id.state_data_user_goal_radio_button:
                    goal = GOAL_DIET_KEEP;
                    break;
                case R.id.mass_data_user_goal_radio_button:
                    goal = GOAL_DIET_MASS;
                    break;


                case R.id.lvl_low_activity_data_user_radio_button:
                    lvlActivity = PARAMETER_LVL_ACTIVITY_LOW;
                    break;
                case R.id.lvl_medium_data_user_radio_button:
                    lvlActivity = PARAMETER_LVL_ACTIVITY_MEDIUM;
                    break;
                case R.id.lvl_height_data_user_radio_button:
                    lvlActivity = PARAMETER_LVL_ACTIVITY_HEIGHT;
                    break;


                case R.id.carbo_diet_data_user_radio_button:
                    typeDiet = GOAL_TYPE_DIETS_CARBOHYDRATE;
                    break;
                case R.id.stabile_diet_data_user_radio_button:
                    typeDiet = GOAL_TYPE_DIETS_STABILE;
                    break;
                case R.id.protein_diet_data_user_radio_button:
                    typeDiet = GOAL_TYPE_DIETS_PROTEIN;
                    break;
                case R.id.fat_diet_data_user_radio_button:
                    typeDiet = GOAL_TYPE_DIETS_FAT;
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
        realm.close();
    }
}
