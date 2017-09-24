package com.example.michal.dietvoltwo.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.dto.ProductInMeal;

import java.util.List;

public class ProductInMealsAdapter extends RecyclerView.Adapter<ProductInMealsAdapter.ViewHolder> {

    private List<ProductInMeal> productInMeals;
    private Context context;

    public ProductInMealsAdapter(List<ProductInMeal> productInMeals, Context context) {
        this.productInMeals = productInMeals;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_plan_diet, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ProductInMeal productInMeal = productInMeals.get(position);

        holder.numberTextView.setText(String.valueOf(productInMeals.get(position).getNumber() + 1));
        holder.proteinProductTextView.setText(productInMeals.get(position).getNameProduct().get(0));
        holder.carbohydrateProductTextView.setText(productInMeals.get(position).getNameProduct().get(1));
        holder.fatProductTextView.setText(productInMeals.get(position).getNameProduct().get(2));

    }

    @Override
    public int getItemCount() {
        return productInMeals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView numberTextView;
        public TextView proteinProductTextView;
        public TextView carbohydrateProductTextView;
        public TextView fatProductTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            numberTextView = (TextView) itemView.findViewById(R.id.number_meal_text_view);
            proteinProductTextView = (TextView) itemView.findViewById(R.id.prodein_product_plan_diet_text_view);
            carbohydrateProductTextView = (TextView) itemView.findViewById(R.id.carbohydrate_product_plan_diet_text_view);
            fatProductTextView = (TextView) itemView.findViewById(R.id.fat_product_plan_diet_text_view);
        }
    }
}
