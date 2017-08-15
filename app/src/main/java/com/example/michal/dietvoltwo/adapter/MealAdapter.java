package com.example.michal.dietvoltwo.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.dto.MealDto;
import com.example.michal.dietvoltwo.service.Impl.MealServideImpl;


import io.realm.Realm;

public class MealAdapter extends RealmRecyclerViewAdapter<MealDto> {

    private Context context;
    private Realm realm;
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
        realm = MealServideImpl.getInstance().getRealm();

        final MealDto mealDto = getItem(position);
        final CardViewHolder holder = (CardViewHolder) viewHolder;

        holder.numberMeal.setText(mealDto.getNumberMeal());
        holder.proteinMeal.setText(mealDto.getB());
        holder.fatMeal.setText(mealDto.getT());
        holder.carbohydrateMeal.setText(mealDto.getW());
        holder.kcalMeal.setText(mealDto.getKcalForMeal());
        holder.changeCarboMeal.setText(changeCarbohydratoToChangeCarbo(mealDto.getW()));
    }

    @Override
    public int getItemCount() {
        if (getRealmBaseAdapter() != null) {
            return getRealmBaseAdapter().getCount();
        }
        return 0;
    }


    private static int changeCarbohydratoToChangeCarbo(int carbohydrate) {
        return (int) (carbohydrate / 10);
    }


    public static class CardViewHolder extends RecyclerView.ViewHolder {

        private TextView numberMeal, kcalMeal, proteinMeal, fatMeal, carbohydrateMeal, changeCarboMeal;

        public CardViewHolder(View itemView) {
            super(itemView);
            numberMeal = (TextView) itemView.findViewById(R.id.number_meal_text_view);
            kcalMeal = (TextView) itemView.findViewById(R.id.kcal_meal_text_view);
            proteinMeal = (TextView) itemView.findViewById(R.id.protein_meal_text_view);
            fatMeal = (TextView) itemView.findViewById(R.id.fat_meal_text_view);
            carbohydrateMeal = (TextView) itemView.findViewById(R.id.carbohydrate_meal_text_view);
            changeCarboMeal = (TextView) itemView.findViewById(R.id.change_carbo_meal_text_view);
        }
    }
}
