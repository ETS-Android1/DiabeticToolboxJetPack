package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.R;

import java.util.ArrayList;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealViewHolder> {

    private ArrayList<ExampleMeal> mExampleMeal;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(int position);
        void onAddMealClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }

    public static class MealViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextView;
        public ImageView mAddMealImage;

        public MealViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mTextView = itemView.findViewById(R.id.mealName);
            mAddMealImage = itemView.findViewById(R.id.image_add_meal);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            mAddMealImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onAddMealClick(position);
                        }
                    }
                }
            });
        }
    }

    public MealAdapter(ArrayList<ExampleMeal> exampleMeal){
        mExampleMeal = exampleMeal;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_meal, parent, false);
        MealViewHolder mealViewHolder = new MealViewHolder(view, mOnItemClickListener);
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
