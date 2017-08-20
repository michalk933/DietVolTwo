package com.example.michal.dietvoltwo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.dto.BtwDto;
import com.example.michal.dietvoltwo.service.Impl.BtwServiceImpl;
import com.example.michal.dietvoltwo.service.diet.DietGenerateService;
import com.example.michal.dietvoltwo.service.totalBtw.GenerateBTW;


public class MealActivity extends AppCompatActivity {

    private RecyclerView recycleListView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recycleListView = (RecyclerView)findViewById(R.id.recycler_view_meal_activity);

//        recycleListView.setAdapter();


        new Handler().post(new Runnable() {
            @Override
            public void run() {
                try {


                    // DOWNLOAD REALM DATA !!!

                } finally {
                    if (realm != null) {
                        realm.close();
                    }
                }
            }
        });







        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
