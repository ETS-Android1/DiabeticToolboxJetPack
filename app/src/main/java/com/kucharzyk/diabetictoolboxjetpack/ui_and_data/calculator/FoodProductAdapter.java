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

        void onAddProductClick(int position);

        void onDeleteProductClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView mProductName;
        public TextView mProductBrand;
        public TextView mProductQuantity;

        public TextView mProductCarbsValue;
        public TextView mProductFatValue;
        public TextView mProductProteinsValue;
        public TextView mProductCarbsExchangerValue;
        public TextView mProductFatExchangerValue;

        public ImageView mAddProductImage;
        public ImageView mDeleteProductImage;
        public ImageView mEditProductImage;


        public ProductViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mProductName = itemView.findViewById(R.id.text_meal_name);
            mProductBrand = itemView.findViewById(R.id.text_product_brand);
            mProductQuantity = itemView.findViewById(R.id.text_product_quantity);

            mProductCarbsValue = itemView.findViewById(R.id.text_product_carbs_value);
            mProductFatValue = itemView.findViewById(R.id.text_product_fat_value);
            mProductProteinsValue = itemView.findViewById(R.id.text_product_proteins_value);
            mProductCarbsExchangerValue = itemView.findViewById(R.id.text_product_carbs_exchanger_value);
            mProductFatExchangerValue = itemView.findViewById(R.id.text_product_fat_exchanger_value);

            mAddProductImage = itemView.findViewById(R.id.image_add_product);
            mDeleteProductImage = itemView.findViewById(R.id.image_delete_product);
            mEditProductImage = itemView.findViewById(R.id.image_edit_product);

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

            mAddProductImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onAddProductClick(position);
                        }
                    }
                }
            });

            mDeleteProductImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteProductClick(position);
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_product, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view, mOnItemClickListener);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        FoodProduct currentMeal = mFoodProduct.get(position);
        holder.mProductName.setText(currentMeal.getProductName());
        holder.mProductCarbsValue.setText(currentMeal.getCarbohydrates().toString());
        holder.mProductFatValue.setText(currentMeal.getFat().toString());
        holder.mProductProteinsValue.setText(currentMeal.getProteins().toString());
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
