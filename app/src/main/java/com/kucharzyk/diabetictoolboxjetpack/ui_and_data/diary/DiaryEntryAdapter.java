package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.R;

import java.util.ArrayList;

public class DiaryEntryAdapter extends RecyclerView.Adapter<DiaryEntryAdapter.DiaryEntryViewHolder> {

    private ArrayList<DiaryEntry> mDiaryEntry;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onAddProductClick(int position);

        void onDeleteProductClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public static class DiaryEntryViewHolder extends RecyclerView.ViewHolder {

        public TextView mProductName;
/*        public TextView mProductBrand;
        public TextView mProductQuantity;*/

        public TextView mProductCarbsValue;
        public TextView mProductFatValue;
        public TextView mProductProteinsValue;
        public TextView mProductCarbsExchangerValue;
        public TextView mProductFatExchangerValue;


        public DiaryEntryViewHolder(@NonNull View itemView, OnItemClickListener listener) {
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

    public DiaryEntryAdapter(ArrayList<DiaryEntry> diaryEntry) {
        mDiaryEntry = diaryEntry;
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
        DiaryEntry currentDiaryEntry = mDiaryEntry.get(position);
        holder.mProductName.setText(currentDiaryEntry.getProductName());
        holder.mProductCarbsValue.setText(currentDiaryEntry.getCarbohydrates().toString());
        holder.mProductFatValue.setText(currentDiaryEntry.getFat().toString());
        holder.mProductProteinsValue.setText(currentDiaryEntry.getProteins().toString());
    }


    @Override
    public int getItemCount() {
        return mDiaryEntry.size();
    }

    public void filterList(ArrayList<DiaryEntry> filteredList) {
        mDiaryEntry = filteredList;
        notifyDataSetChanged();
    }
}
