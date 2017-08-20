package com.example.michal.dietvoltwo.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

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

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //View to list meal
        recycleListView = (RecyclerView) findViewById(R.id.recycler_view_meal_activity);

        //Realm data base
        this.realm = MealServideImpl.with(this).getRealm();
        setupRecycler();
        MealServideImpl.with(this).refresh();
        RealmResults<MealDto> all = MealServideImpl.with(this).findAll();
        setRealmAdapter(MealServideImpl.with(this).findAll());

        //Menu
        drawerLayout = (DrawerLayout) findViewById(R.id.drow_layout_meal_activity);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.navigation_open, R.string.navigation_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView = (NavigationView) findViewById(R.id.navigation_view_menu);


        setUpNavigationView();

        //Float button
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void setUpNavigationView() {
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_main_menu:
                        Toast.makeText(MealActivity.this, "Meanu główne", Toast.LENGTH_SHORT).show();
                        Log.d("MOJE LOGI", "Meanu główne");
                        break;
                    case R.id.navigation_list_product:
                        Toast.makeText(MealActivity.this, "Lista produktów", Toast.LENGTH_SHORT).show();
                        Log.d("MOJE LOGI", "Lista produktów");
                        break;
                    case R.id.navigation_me_data:
                        Toast.makeText(MealActivity.this, "Moje dane", Toast.LENGTH_SHORT).show();
                        Log.d("MOJE LOGI", "Moje dane");
                        break;
                    case R.id.navigation_about:
                        Toast.makeText(MealActivity.this, "O aplikacji", Toast.LENGTH_SHORT).show();
                        Log.d("MOJE LOGI", "O aplikacji");
                        break;
                    case R.id.navigation_nothing:
                        Toast.makeText(MealActivity.this, "NIC !", Toast.LENGTH_SHORT).show();
                        Log.d("MOJE LOGI", "NIC !");
                        break;
                }

                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);

                return true;
            }
        });


    }

}
