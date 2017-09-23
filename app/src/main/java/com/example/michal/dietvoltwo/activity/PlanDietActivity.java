package com.example.michal.dietvoltwo.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.dto.ProductDto;
import com.example.michal.dietvoltwo.dto.ProductInMeal;
import com.example.michal.dietvoltwo.repository.ProductServiceImpl;
import com.example.michal.dietvoltwo.service.planDietService.CreatePlanDietWithProduct;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class PlanDietActivity extends AppCompatActivity {

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_diet);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //TODO
        //wrzucenie danych do adaptera
        //adapter dynamiczny ???

        this.realm = ProductServiceImpl.with(this).getRealm();
        RealmResults<ProductDto> products = ProductServiceImpl.getInstance().findAll();
        CreatePlanDietWithProduct createPlanDietWithProduct = new CreatePlanDietWithProduct();
        List<ProductInMeal> productInMeals = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            productInMeals.add(createPlanDietWithProduct.create(products, i));
        }


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

    @Override
    public void onDestroy() {
        super.onDestroy();
        realm.close();
    }

}
