package com.example.michal.dietvoltwo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.adapter.ProductAdapter;
import com.example.michal.dietvoltwo.adapter.RealmProductAdapter;
import com.example.michal.dietvoltwo.dto.BtwDto;
import com.example.michal.dietvoltwo.dto.ProductDto;
import com.example.michal.dietvoltwo.repository.BtwServiceImpl;
import com.example.michal.dietvoltwo.repository.MealServideImpl;
import com.example.michal.dietvoltwo.repository.ProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

import static com.example.michal.dietvoltwo.util.Constant.CARBOHYDRATE_TYPE;
import static com.example.michal.dietvoltwo.util.Constant.FAT_TYPE;
import static com.example.michal.dietvoltwo.util.Constant.PROTEIN_TYPE;

public class DescriptionMealActivity extends AppCompatActivity {

    private String proteinProduct;
    private String carbohydrateProduct;
    private String fatProduct;
    private Intent intent;

    private RecyclerView recyclerView;
    private Realm realm;
    private Realm mealRealm;
    private ProductAdapter adapter;
    private RealmResults<ProductDto> produts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description_meal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        intent = getIntent();
        proteinProduct = intent.getStringExtra(PROTEIN_TYPE);
        carbohydrateProduct = intent.getStringExtra(CARBOHYDRATE_TYPE);
        fatProduct = intent.getStringExtra(FAT_TYPE);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_desription_meal_activity);
        this.realm = ProductServiceImpl.with(this).getRealm();

        setupRecycler();
        ProductServiceImpl.with(this).refresh();
        setRealmAdapter(ProductServiceImpl.getInstance().findForName(proteinProduct,carbohydrateProduct,fatProduct));





        this.mealRealm = MealServideImpl.with(this).getRealm();
        MealServideImpl.with(this).refresh();
        MealServideImpl.with(this).findAll();







        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setRealmAdapter(RealmResults<ProductDto> produts) {
        RealmProductAdapter realmProductAdapter = new RealmProductAdapter(this.getApplicationContext(), produts, true);
        adapter.setRealmBaseAdapter(realmProductAdapter);
        adapter.notifyDataSetChanged();
    }

    private void setupRecycler() {
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new ProductAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
