package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.food;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.util.ArrayList;
import java.util.List;

public class FoodProductAdapter extends RecyclerView.Adapter<FoodProductAdapter.ProductViewHolder> {

    private List<Product> mFoodProducts = new ArrayList<>();
    private List<Product> mFoodProductsFull;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onAddProductClick(int position);

        void onDeleteProductClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {

        public TextView mProductName;
        public TextView mProductProducer;
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
            mProductName = itemView.findViewById(R.id.text_product_name);
            mProductProducer = itemView.findViewById(R.id.text_producer_name);
            mProductQuantity = itemView.findViewById(R.id.text_product_quantity);

            mProductCarbsValue = itemView.findViewById(R.id.text_product_carbs_value);
            mProductFatValue = itemView.findViewById(R.id.text_product_fat_value);
            mProductProteinsValue = itemView.findViewById(R.id.text_product_proteins_value);
            mProductCarbsExchangerValue = itemView.findViewById(R.id.text_product_carbs_exchanger_value);
            mProductFatExchangerValue = itemView.findViewById(R.id.text_product_fat_exchanger_value);

            mAddProductImage = itemView.findViewById(R.id.execrise_example_image_add);
            mDeleteProductImage = itemView.findViewById(R.id.exercise_example_image_delete);
            mEditProductImage = itemView.findViewById(R.id.image_edit_product);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });

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

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_product, parent, false);
        ProductViewHolder productViewHolder = new ProductViewHolder(view, mOnItemClickListener);
        return productViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product currentMeal = mFoodProducts.get(position);
        holder.mProductName.setText(currentMeal.getProductName());
    }

    @Override
    public int getItemCount() {
        return mFoodProducts.size();
    }

    public void setProducts(List<Product> mFoodProducts){
        this.mFoodProducts = mFoodProducts;
        mFoodProductsFull = new ArrayList<>(mFoodProducts);
        notifyDataSetChanged();
    }

    public void filterList(List<Product> filteredList) {
        mFoodProducts = filteredList;
        notifyDataSetChanged();
    }

    public Product getProduct(int position) {return mFoodProducts.get(position); }

    public List<Product> getProductsList() {
        if (mFoodProductsFull != null){
            return new ArrayList<>(mFoodProductsFull);
        }
        else {
            return new ArrayList<>(mFoodProducts);
        }
    }
}
