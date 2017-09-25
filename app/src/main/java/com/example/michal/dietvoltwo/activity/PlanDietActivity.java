package com.example.michal.dietvoltwo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;


import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.adapter.ProductAdapter;
import com.example.michal.dietvoltwo.adapter.ProductInMealsAdapter;
import com.example.michal.dietvoltwo.dto.ProductDto;
import com.example.michal.dietvoltwo.dto.ProductInMeal;
import com.example.michal.dietvoltwo.repository.ProductServiceImpl;
import com.example.michal.dietvoltwo.service.planDietService.createPlanDietWithProduct;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class PlanDietActivity extends AppCompatActivity {

    private Realm realm;
    private RecyclerView recyclerView;
    private ProductInMealsAdapter adapter;
    List<ProductInMeal> productInMeals;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_diet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_diet_plan_activity);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        this.realm = ProductServiceImpl.with(this).getRealm();
        RealmResults<ProductDto> products = ProductServiceImpl.getInstance().findAll();
        productInMeals = new ArrayList<>();
        for (int i = 0; i <= 4; i++) {
            productInMeals.add(createPlanDietWithProduct.create(products, i));
        }

        adapter = new ProductInMealsAdapter(productInMeals, this);
        recyclerView.setAdapter(adapter);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
