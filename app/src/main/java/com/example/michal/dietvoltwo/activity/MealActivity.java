package com.example.michal.dietvoltwo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.adapter.MealAdapter;
import com.example.michal.dietvoltwo.adapter.RealmMealAdapter;
import com.example.michal.dietvoltwo.dto.BtwDto;
import com.example.michal.dietvoltwo.dto.MealDto;
import com.example.michal.dietvoltwo.dto.MealsDto;
import com.example.michal.dietvoltwo.service.Impl.BtwServiceImpl;
import com.example.michal.dietvoltwo.service.Impl.MealServideImpl;
import com.example.michal.dietvoltwo.service.diet.DietGenerateService;
import com.example.michal.dietvoltwo.service.totalBtw.GenerateBTW;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class MealActivity extends AppCompatActivity {

    private RecyclerView recycleListView;
    private Realm realm;
    private MealAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recycleListView = (RecyclerView) findViewById(R.id.recycler_view_meal_activity);
        this.realm = MealServideImpl.with(this).getRealm();
        setupRecycler();
        MealServideImpl.with(this).refresh();



        RealmResults<MealDto> all = MealServideImpl.with(this).findAll();
        for (MealDto mealDto : all) {
            Log.d("ACTIVITY MEAL ==== ", String.valueOf(mealDto.getNumberMeal()));
        }
        setRealmAdapter(MealServideImpl.with(this).findAll());




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void setRealmAdapter(RealmResults<MealDto> meals) {
        RealmMealAdapter realmMealAdapter = new RealmMealAdapter(this.getApplicationContext(), meals, true);
        adapter.setRealmBaseAdapter(realmMealAdapter);
        adapter.notifyDataSetChanged();
    }


    private void setupRecycler() {
        recycleListView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycleListView.setLayoutManager(linearLayoutManager);

        adapter = new MealAdapter(this);
        recycleListView.setAdapter(adapter);
    }


}
