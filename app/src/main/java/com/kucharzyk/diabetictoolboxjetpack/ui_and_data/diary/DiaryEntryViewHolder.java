package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.R;

class DiaryEntryViewHolder extends RecyclerView.ViewHolder {
    private final TextView mProductName;
/*        public TextView mProductBrand;
        public TextView mProductQuantity;*/

    private final TextView mProductCarbsValue;
    private final TextView mProductFatValue;
    private final TextView mProductProteinsValue;
    private final TextView mProductCarbsExchangerValue;
    private final TextView mProductFatExchangerValue;


    public DiaryEntryViewHolder(@NonNull View itemView, DiaryEntryAdapter.OnItemClickListener listener) {
        super(itemView);
        mProductName = itemView.findViewById(R.id.diary_text_meal_summary);
/*            mProductBrand = itemView.findViewById(R.id.text_product_brand);
            mProductQuantity = itemView.findViewById(R.id.text_product_quantity);*/

        mProductCarbsValue = itemView.findViewById(R.id.diary_text_carbs_summary_value);
        mProductFatValue = itemView.findViewById(R.id.diary_text_fat_summary_value);
        mProductProteinsValue = itemView.findViewById(R.id.diary_text_proteins_summary_value);
        mProductCarbsExchangerValue = itemView.findViewById(R.id.diary_text_carbs_exchanger_summary_value);
        mProductFatExchangerValue = itemView.findViewById(R.id.diary_text_protein_fat_exchanger_summary_value);


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

/*            mAddProductImage.setOnClickListener(new View.OnClickListener() {
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
            });*/
    }

    public void bind(String text){
        
    }
}
