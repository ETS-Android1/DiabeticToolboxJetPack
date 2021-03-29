package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.R;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {

    private ArrayList<ExampleMeal> mExampleMeal;

    public static class MealViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.mealName);
        }
    }

    public MealAdapter(ArrayList<ExampleMeal> exampleMeal){
        mExampleMeal = exampleMeal;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_meal, parent, false);
        MealViewHolder mealViewHolder = new MealViewHolder(view);
        return mealViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        ExampleMeal currentMeal = mExampleMeal.get(position);
        holder.mTextView.setText(currentMeal.getMealName());
    }


    @Override
    public int getItemCount() {
        return mExampleMeal.size();
    }

    public void filterList(ArrayList<ExampleMeal> filteredList) {
        mExampleMeal = filteredList;
        notifyDataSetChanged();
    }
}
