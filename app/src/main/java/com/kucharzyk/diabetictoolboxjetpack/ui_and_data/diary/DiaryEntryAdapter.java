package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.util.ArrayList;
import java.util.List;

public class DiaryEntryAdapter extends RecyclerView.Adapter<DiaryEntryAdapter.DiaryEntryViewHolder> {
    public static final String TAG = "DiaryEntryAdapter";

    private List<Product> diaryEntries = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onAddProductClick(int position);

        void onDeleteProductClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public static class DiaryEntryViewHolder extends RecyclerView.ViewHolder{

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
    }


    @NonNull
    @Override
    public DiaryEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_diary_entry, parent, false);
        DiaryEntryViewHolder diaryEntryViewHolder = new DiaryEntryViewHolder(view, mOnItemClickListener);
        return diaryEntryViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull DiaryEntryViewHolder holder, int position) {
        Product currentDiaryEntry = diaryEntries.get(position);
        holder.mProductName.setText(currentDiaryEntry.getProductName());
        holder.mProductCarbsValue.setText(currentDiaryEntry.getCarbohydrates().toString());
        holder.mProductFatValue.setText(currentDiaryEntry.getFat().toString());
        holder.mProductProteinsValue.setText(currentDiaryEntry.getProteins().toString());
    }


    @Override
    public int getItemCount() {
        if (diaryEntries == null){
            Log.d(TAG, "getItemCount: No elements to display");
            return 0;
        }

        return diaryEntries.size();
    }

    public void setDiaryEntries(List<Product> diaryEntries){
        this.diaryEntries = diaryEntries;
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<Product> filteredList) {
        diaryEntries = filteredList;
        notifyDataSetChanged();
    }
}
