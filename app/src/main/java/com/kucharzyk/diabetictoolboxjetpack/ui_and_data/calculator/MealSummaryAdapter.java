package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.Globals;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.util.ArrayList;
import java.util.List;

public class MealSummaryAdapter extends RecyclerView.Adapter<MealSummaryAdapter.MealSummaryViewHolder> {

    private List<Product> mMealSummary = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {

        void onEditProductClick(int position);

        void onDeleteProductClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public static class MealSummaryViewHolder extends RecyclerView.ViewHolder {

        private TextView mProductName;
        private TextView mProductBrand;
        private TextView mProductQuantity;
        private TextView mProductCarbsValue;
        private TextView mProductFatValue;
        private TextView mProductProteinsValue;
        private TextView mProductCarbsExchangerValue;
        private TextView mProductFatExchangerValue;
        private ImageView mEditProductImage;
        private ImageView mDeleteProductImage;

        public MealSummaryViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mProductName = itemView.findViewById(R.id.text_product_name);
            mProductBrand = itemView.findViewById(R.id.text_product_brand);
            mProductQuantity = itemView.findViewById(R.id.text_product_quantity);
            mProductCarbsValue = itemView.findViewById(R.id.text_product_carbs_value);
            mProductFatValue = itemView.findViewById(R.id.text_product_fat_value);
            mProductProteinsValue = itemView.findViewById(R.id.text_product_proteins_value);
            mProductCarbsExchangerValue = itemView.findViewById(R.id.text_product_carbs_exchanger_value);
            mProductFatExchangerValue = itemView.findViewById(R.id.text_product_fat_exchanger_value);
            mEditProductImage = itemView.findViewById(R.id.image_edit_product);
            mDeleteProductImage = itemView.findViewById(R.id.image_delete_product);

            mEditProductImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onEditProductClick(position);
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

    @NonNull
    @Override
    public MealSummaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_product_summary, parent, false);
        MealSummaryViewHolder mMealSummaryViewHolder = new MealSummaryViewHolder(view, mOnItemClickListener);
        return mMealSummaryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MealSummaryViewHolder holder, int position) {
        Product currentProduct = mMealSummary.get(position);
        holder.mProductName.setText(currentProduct.getProductName());
        holder.mProductCarbsValue.setText(Globals.REAL_FORMATTER.format(currentProduct.getCarbohydrates()));
        holder.mProductFatValue.setText(Globals.REAL_FORMATTER.format(currentProduct.getFat()));
        holder.mProductProteinsValue.setText(Globals.REAL_FORMATTER.format(currentProduct.getProteins()));
    }

    @Override
    public int getItemCount() {
        return mMealSummary.size();
    }

    public void setProducts(List<Product> mealSummary) {
        mMealSummary = mealSummary;
        notifyDataSetChanged();
    }

    public Product getProduct(int position) {return mMealSummary.get(position); }


}
