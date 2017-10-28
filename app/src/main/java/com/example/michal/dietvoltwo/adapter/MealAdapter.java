package com.example.michal.dietvoltwo.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.activity.PlanDietActivity;
import com.example.michal.dietvoltwo.dto.MealDto;
import com.example.michal.dietvoltwo.util.ConvertCarbohydratoToCarboChange;



public class MealAdapter extends RealmRecyclerViewAdapter<MealDto> {

    final Context context;
    private LayoutInflater layoutInflater;

    public MealAdapter(Context context) {
        this.context = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        final MealDto mealDto = getItem(position);
        final CardViewHolder holder = (CardViewHolder) viewHolder;

        holder.numberMeal.setText(String.valueOf(mealDto.getNumberMeal() + 1));
        holder.proteinMeal.setText("Białka: " + mealDto.getB());
        holder.fatMeal.setText("Tłuszczu: " + mealDto.getT());
        holder.carbohydrateMeal.setText("Węglowodanów: " + mealDto.getW());
        holder.kcalMeal.setText("Kcal: " + mealDto.getKcalForMeal());
        holder.changeCarboMeal.setText("Wymienników węglowodanowych: " + ConvertCarbohydratoToCarboChange.convert(mealDto.getW()));


        holder.card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, PlanDietActivity.class));
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
        public TextView numberMeal, kcalMeal, proteinMeal, fatMeal, carbohydrateMeal, changeCarboMeal;

        public CardViewHolder(View itemView) {
            super(itemView);
            card = (CardView) itemView.findViewById(R.id.card_meal);
            numberMeal = (TextView) itemView.findViewById(R.id.number_meal_tex);
            kcalMeal = (TextView) itemView.findViewById(R.id.kcal_meal_text_view);
            proteinMeal = (TextView) itemView.findViewById(R.id.protein_meal_text_view);
            fatMeal = (TextView) itemView.findViewById(R.id.fat_meal_text_view);
            carbohydrateMeal = (TextView) itemView.findViewById(R.id.carbohydrate_meal_text_view);
            changeCarboMeal = (TextView) itemView.findViewById(R.id.change_carbo_meal_text_view);
        }
    }
}
