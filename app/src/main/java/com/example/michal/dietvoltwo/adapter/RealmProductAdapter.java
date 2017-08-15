package com.example.michal.dietvoltwo.adapter;


import android.content.Context;

import com.example.michal.dietvoltwo.dto.ProductDto;


import io.realm.RealmResults;

public class RealmProductAdapter extends RealmModelAdapter<ProductDto> {
    public RealmProductAdapter(Context context, RealmResults<ProductDto> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }
}
