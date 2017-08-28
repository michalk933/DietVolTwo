package com.example.michal.dietvoltwo.adapter;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.dto.ProductDto;
import com.example.michal.dietvoltwo.service.reamlService.ProductServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import io.realm.Realm;
import io.realm.RealmResults;

public class ProductAdapter extends RealmRecyclerViewAdapter<ProductDto> {

    private Context context;
    private Realm realm;
    private LayoutInflater layoutInflater;

    public ProductAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        realm = ProductServiceImpl.getInstance().getRealm();

        final ProductDto productDto = getItem(position);
        final CardViewHolder holder = (CardViewHolder) viewHolder;


        Log.d("LOGI ADAPTER == ",productDto.getImage() );
        File f = new File(productDto.getImage(), "profile.jpg");
        try {
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            holder.image.setImageBitmap(b);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

//        holder.image.setImageURI(u);
        holder.bProduct.setText("Białko: " + productDto.getB());
        holder.igProduct.setText("Index glikemiczny: " + productDto.getIg());
        holder.kcalProduct.setText("Kcal: " + productDto.getKcal());
        holder.nameProduct.setText("Nazwa: " + productDto.getName());
        holder.tProduct.setText("Tłuszcze: " + productDto.getT());
        holder.wProduct.setText("Węglowodanów: " + productDto.getW());


        holder.card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                RealmResults<ProductDto> results = realm.where(ProductDto.class).findAll();
                ProductDto product = results.get(position);
                String name = product.getName();

                realm.beginTransaction();
                results.remove(position);
                realm.commitTransaction();

                notifyDataSetChanged();

                Toast.makeText(context, name + " został usuniętny z bazy", Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        if (getRealmBaseAdapter() != null) {
            return getRealmBaseAdapter().getCount();
        }
        return 0;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        public CardView card;
        private ImageView image;
        private TextView nameProduct, kcalProduct, bProduct, tProduct, wProduct, igProduct;


        public CardViewHolder(View itemView) {
            super(itemView);
            card = (CardView) itemView.findViewById(R.id.card_product);
            image = (ImageView) itemView.findViewById(R.id.image_view_product);
            nameProduct = (TextView) itemView.findViewById(R.id.name_product_text_view);
            kcalProduct = (TextView) itemView.findViewById(R.id.kcal_oroduct_text_view);
            bProduct = (TextView) itemView.findViewById(R.id.protein_product_text_view);
            tProduct = (TextView) itemView.findViewById(R.id.fat_product_text_view);
            wProduct = (TextView) itemView.findViewById(R.id.carbohydrate_product_text_view);
            igProduct = (TextView) itemView.findViewById(R.id.ig_product_text_view);
        }
    }
}
