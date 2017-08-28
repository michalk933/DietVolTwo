package com.example.michal.dietvoltwo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.View;


import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.adapter.ProductAdapter;
import com.example.michal.dietvoltwo.adapter.RealmProductAdapter;
import com.example.michal.dietvoltwo.dto.ProductDto;
import com.example.michal.dietvoltwo.service.reamlService.ProductServiceImpl;


import java.util.ArrayList;
import java.util.Date;


import io.realm.Realm;
import io.realm.RealmResults;

public class ProductActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Realm realm;
    private ProductAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_product_activity);

        this.realm = ProductServiceImpl.with(this).getRealm();

        setRealmData();

        setupRecycler();
        ProductServiceImpl.with(this).refresh();

        setRealmAdapter(ProductServiceImpl.with(this).findAll());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), NewProductActivity.class);
                startActivity(intent);
            }
        });
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

    private void setRealmData() {
        ArrayList<ProductDto> products = new ArrayList<>();

        ProductDto product = new ProductDto();
        product.setName("test1");
        product.setProducent("test1");
        product.setProductTyp("test1");
        product.setTimeOfDay("test1");
        product.setKcal(123);
        product.setB(1);
        product.setT(1);
        product.setW(1);
        product.setwW(1);
        product.setIg(1);
        product.setForDiabets(1);
        product.setCreate(new Date());
        product.setImage(String.valueOf(R.drawable.ziemniaki));
        products.add(product);

        ProductDto product1 = new ProductDto();
        product1.setName("test2");
        product1.setProducent("test2");
        product1.setProductTyp("test2");
        product1.setTimeOfDay("test2");
        product1.setKcal(1234);
        product1.setB(12);
        product1.setT(12);
        product1.setW(12);
        product1.setwW(12);
        product1.setIg(12);
        product1.setForDiabets(12);
        product1.setCreate(new Date());
        product1.setImage(String.valueOf(R.drawable.losos));
        products.add(product1);

        ProductDto product2 = new ProductDto();
        product2.setName("test3");
        product2.setProducent("test3");
        product2.setProductTyp("test3");
        product2.setTimeOfDay("test3");
        product2.setKcal(12345);
        product2.setB(123);
        product2.setT(123);
        product2.setW(123);
        product2.setwW(123);
        product2.setIg(123);
        product2.setForDiabets(123);
        product2.setCreate(new Date());
        product2.setImage(String.valueOf(R.drawable.jaglak));
        products.add(product2);

        ProductDto product3 = new ProductDto();
        product3.setName("test4");
        product3.setProducent("test4");
        product3.setProductTyp("test4");
        product3.setTimeOfDay("test4");
        product3.setKcal(123456);
        product3.setB(1234);
        product3.setT(1234);
        product3.setW(1234);
        product3.setwW(1234);
        product3.setIg(1234);
        product3.setForDiabets(1234);
        product3.setCreate(new Date());
        product3.setImage(String.valueOf(R.drawable.dzem));
        products.add(product3);

        for (ProductDto productDto : products) {
            ProductServiceImpl.getInstance().save(productDto);
        }
    }

}
