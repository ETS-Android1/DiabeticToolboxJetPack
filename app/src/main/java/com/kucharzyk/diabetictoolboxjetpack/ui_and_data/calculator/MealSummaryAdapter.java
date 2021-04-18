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

public class MealSummaryAdapter extends RecyclerView.Adapter<MealSummaryAdapter.MealSummaryViewHolder> {

    private ArrayList<MealSummary> mMealSummary;

    public static class MealSummaryViewHolder extends RecyclerView.ViewHolder {

        public TextView mProductName;
        public TextView mProductBrand;
        public TextView mProductQuantity;
        public TextView mProductCarbsValue;
        public TextView mProductFatValue;
        public TextView mProductProteinsValue;
        public TextView mProductCarbsExchangerValue;
        public TextView mProductFatExchangerValue;
        public ImageView mEditProductImage;
        public ImageView mDeleteProductImage;

        public MealSummaryViewHolder(@NonNull View itemView) {
            super(itemView);
            mProductName = itemView.findViewById(R.id.text_product_name);
            mProductBrand = itemView.findViewById(R.id.text_product_brand);
            mProductQuantity = itemView.findViewById(R.id.text_product_quantity);
            mProductCarbsValue = itemView.findViewById(R.id.text_product_carbs_value);
            mProductFatValue = itemView.findViewById(R.id.text_product_fat_value);
            mProductProteinsValue = itemView.findViewById(R.id.text_product_proteins_value);
            mProductCarbsExchangerValue = itemView.findViewById(R.id.text_product_carbs_exchanger_value);
            mProductFatExchangerValue = itemView.findViewById(R.id.text_product_fat_exchanger_value);
            mEditProductImage = itemView.findViewById(R.id.image_add_product);
            mDeleteProductImage = itemView.findViewById(R.id.image_delete_product);
        }
    }

    public MealSummaryAdapter(ArrayList<MealSummary> mealSummary){
        mMealSummary = mealSummary;
    }

    @NonNull
    @Override
    public MealSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_product_summary, parent, false);
        MealSummaryViewHolder mMealSummaryViewHolder = new MealSummaryViewHolder(view);
        return mMealSummaryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealSummaryViewHolder holder, int position) {
   /*     MealSummary currentProduct = mMealSummary.get(position);
        holder.mMealName.setText(currentProduct.getProductName());
        holder.mCarbsValue.setText(currentProduct.getCarbohydrates().toString());
        holder.mFatValue.setText(currentProduct.getFat().toString());
        holder.mProteinsValue.setText(currentProduct.getProteins().toString());*/
    }

    @Override
    public int getItemCount() {
        return mMealSummary.size();
    }


}
