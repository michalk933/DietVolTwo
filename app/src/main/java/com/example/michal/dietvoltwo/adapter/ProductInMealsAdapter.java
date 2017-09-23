package com.example.michal.dietvoltwo.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.dto.ProductInMeal;

import java.util.List;

public class ProductInMealsAdapter extends BaseAdapter {

    private Context context;
    private List<ProductInMeal>productInMeals;

    public ProductInMealsAdapter(Context context, List<ProductInMeal> productInMeals) {
        this.context = context;
        this.productInMeals = productInMeals;
    }


    @Override
    public int getCount() {
        return productInMeals.size();
    }

    @Override
    public Object getItem(int i) {
        return productInMeals.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewHolder = View.inflate(context, R.layout.item_plan_diet,null);
        TextView numberTextView = (TextView)viewHolder.findViewById(R.id.number_meal_text_view);
        TextView proteinProductTextView = (TextView)viewHolder.findViewById(R.id.number_meal_text_view);
        TextView carbohydrateProductTextView = (TextView)viewHolder.findViewById(R.id.number_meal_text_view);
        TextView fatProductTextView = (TextView)viewHolder.findViewById(R.id.number_meal_text_view);

        numberTextView.setText(String.valueOf(productInMeals.get(i).getNumber() + 1));
        proteinProductTextView.setText(productInMeals.get(i).getNameProduct().get(0));
        carbohydrateProductTextView.setText(productInMeals.get(i).getNameProduct().get(1));
        fatProductTextView.setText(productInMeals.get(i).getNameProduct().get(2));

        viewHolder.setTag(productInMeals.get(i));
        return viewHolder;
    }
}
