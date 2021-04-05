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

public class FoodProductAdapter extends RecyclerView.Adapter<FoodProductAdapter.ProductViewHolder> {

    private ArrayList<FoodProduct> mFoodProduct;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onAddMealClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView mMealName;
        public TextView mCarbsValue;
        public TextView mFatValue;
        public TextView mProteinsValue;
        public ImageView mAddMealImage;


        public ProductViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mMealName = itemView.findViewById(R.id.text_meal_name);
            mAddMealImage = itemView.findViewById(R.id.image_add_meal);
            mCarbsValue = itemView.findViewById(R.id.text_carbs_value);
            mFatValue = itemView.findViewById(R.id.text_fat_value);
            mProteinsValue = itemView.findViewById(R.id.text_proteins_value);

/*            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });*/

            mAddMealImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onAddMealClick(position);
                        }
                    }
                }
            });
        }
    }

    public FoodProductAdapter(ArrayList<FoodProduct> foodProduct) {
        mFoodProduct = foodProduct;
    }


    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_meal, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view, mOnItemClickListener);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        FoodProduct currentMeal = mFoodProduct.get(position);
        holder.mMealName.setText(currentMeal.getMealName());
        holder.mCarbsValue.setText(currentMeal.getCarbohydrates().toString());
        holder.mFatValue.setText(currentMeal.getFat().toString());
        holder.mProteinsValue.setText(currentMeal.getProteins().toString());
    }


    @Override
    public int getItemCount() {
        return mFoodProduct.size();
    }

    public void filterList(ArrayList<FoodProduct> filteredList) {
        mFoodProduct = filteredList;
        notifyDataSetChanged();
    }
}
