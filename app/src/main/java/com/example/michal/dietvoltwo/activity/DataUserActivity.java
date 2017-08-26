package com.example.michal.dietvoltwo.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.michal.dietvoltwo.R;

import lombok.extern.log4j.Log4j;

@Log4j
public class DataUserActivity extends AppCompatActivity {

    private SeekBar ageSeekBar, weightSeekbar, heightSeekBar;
    private RadioGroup lvlActivityFragmentRadioGrup, golRadioGroup, dietTypeFragmentRadioGrup;
    private TextView ageTextView, weightTextView, heightTextView;
    private int age;
    private int weight;
    private int height;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        createView();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void createView(){
        weightSeekbar = (SeekBar) findViewById(R.id.weight_data_user_seek_bar);
        ageSeekBar = (SeekBar) findViewById(R.id.age_data_user_seek_bar);
        heightSeekBar = (SeekBar) findViewById(R.id.height_data_user_seek_bar);

        lvlActivityFragmentRadioGrup = (RadioGroup)findViewById(R.id.lvl_activity_data_user_radio_grup);
        golRadioGroup = (RadioGroup)findViewById(R.id.gol_fragment_data_user_radio_grup);
        dietTypeFragmentRadioGrup = (RadioGroup)findViewById(R.id.diet_type_data_user_radio_grup);

        ageTextView = (TextView)findViewById(R.id.age_data_user_text_view);
        weightTextView = (TextView)findViewById(R.id.weight_data_user_text_view);
        heightTextView = (TextView)findViewById(R.id.height_data_user_text_view);
    }

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
            switch (i){
                case R.id.reduction_data_user_goal_radio_button:

                    break;
                case R.id.state_data_user_goal_radio_button:

                    break;
                case R.id.mass_data_user_goal_radio_button:

                    break;



                case R.id.lvl_low_activity_data_user_radio_button:

                    break;
                case R.id.lvl_medium_data_user_radio_button:

                    break;
                case R.id.lvl_height_data_user_radio_button:

                    break;


                case R.id.carbo_diet_data_user_radio_button:

                    break;
                case R.id.stabile_diet_data_user_radio_button:

                    break;
                case R.id.protein_diet_data_user_radio_button:

                    break;
                case R.id.fat_diet_data_user_radio_button:

                    break;
            }
        }
    };

    private SeekBar.OnSeekBarChangeListener onSeekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            switch (i){
                case R.id.age_data_user_seek_bar:
                    if(b){
                        age = ageSeekBar.getProgress();
                        ageTextView.setText(getResources().getString(R.string.age_text_view) + " " + age + " lat");
                        //
                    }
                    break;
                case R.id.weight_data_user_seek_bar:
                    if(b){
                        weight = weightSeekbar.getProgress();
                        weightTextView.setText(getResources().getString(R.string.weight_text_view) + " " + weight + " kg");
                        //
                    }
                    break;
                case R.id.height_data_user_seek_bar:
                    if(b){
                        height = heightSeekBar.getProgress();
                        heightTextView.setText(getResources().getString(R.string.height_text_view) + " " + height + " cm");
                        //
                    }
                    break;
            }
        }
        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {}
        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {}
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        //realm delete

    }

}
