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
        realm = Realm.getDefaultInstance();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_product_activity);
        setupRecycler();
        setRealmAdapter(realm.where(ProductDto.class).findAll());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), NewProductActivity.class));
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

}
