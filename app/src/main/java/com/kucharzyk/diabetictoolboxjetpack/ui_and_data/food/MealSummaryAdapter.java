package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.food;

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

        private final TextView mProductName;
/*        private final TextView mProductBrand;
        private final TextView mProductQuantity;*/
        private final TextView mProductCarbsValue;
        private final TextView mProductFatValue;
        private final TextView mProductProteinsValue;
        private final TextView mProductCaloriesValue;
        private final TextView mProductCarbsExchangerValue;
        private final TextView mProductProteinFatExchangerValue;

        public MealSummaryViewHolder(@NonNull View itemView, OnItemClickListener listener) {
            super(itemView);
            mProductName = itemView.findViewById(R.id.text_product_name);
/*            mProductBrand = itemView.findViewById(R.id.text_product_brand);
            mProductQuantity = itemView.findViewById(R.id.text_product_quantity);*/
            mProductCarbsValue = itemView.findViewById(R.id.text_product_carbs_value);
            mProductFatValue = itemView.findViewById(R.id.text_product_fat_value);
            mProductProteinsValue = itemView.findViewById(R.id.text_product_proteins_value);
            mProductCaloriesValue = itemView.findViewById(R.id.text_product_calories_value);
            mProductCarbsExchangerValue = itemView.findViewById(R.id.text_product_carbs_exchanger_value);
            mProductProteinFatExchangerValue = itemView.findViewById(R.id.text_product_fat_exchanger_value);
            ImageView mEditProductImage = itemView.findViewById(R.id.image_edit_product);
            ImageView mDeleteProductImage = itemView.findViewById(R.id.exercise_example_image_delete);

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

        String mProductCarbs = Globals.REAL_FORMATTER.format(currentProduct.getCarbohydrates()) + " g";
        String mProductFat = Globals.REAL_FORMATTER.format(currentProduct.getFat()) + " g";
        String mProductProteins = Globals.REAL_FORMATTER.format(currentProduct.getProteins()) + " g";
        String mProductCalories = Globals.REAL_FORMATTER.format(currentProduct.getCalories()) + " kcal";
        String mProductCarbsExchanger = Globals.REAL_FORMATTER.
                format((currentProduct.getCarbohydrates()) / 12) + " units";
        String mProductProteinFatExchanger = Globals.REAL_FORMATTER.
                format((9 * currentProduct.getFat() + 4 * currentProduct.getProteins()) / 100) + " units";

        holder.mProductName.setText(currentProduct.getProductName());
        holder.mProductCarbsValue.setText(mProductCarbs);
        holder.mProductFatValue.setText(mProductFat);
        holder.mProductProteinsValue.setText(mProductProteins);
        holder.mProductCaloriesValue.setText(mProductCalories);
        holder.mProductCarbsExchangerValue.setText(mProductCarbsExchanger);
        holder.mProductProteinFatExchangerValue.setText(mProductProteinFatExchanger);
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
