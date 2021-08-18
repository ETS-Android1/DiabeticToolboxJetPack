package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.Globals;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.util.ArrayList;
import java.util.List;

public class DiaryEntryAdapter extends RecyclerView.Adapter<DiaryEntryAdapter.DiaryEntryViewHolder> {
    public static final String TAG = "DiaryEntryAdapter";

    private List<Product> diaryEntries = new ArrayList<>();
    private OnItemClickListener mOnItemClickListener;
    private int diarySize;

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onAddProductClick(int position);

        void onDeleteProductClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mOnItemClickListener = listener;
    }

    public static class DiaryEntryViewHolder extends RecyclerView.ViewHolder {

/*        private final TextView mProductName;
        public TextView mProductBrand;
        public TextView mProductQuantity;*/

        private final TextView diaryEntryCarbohydrateValue;
        private final TextView diaryEntryFatValue;
        private final TextView diaryEntryProteinsValue;
        //private final TextView diaryEntryCaloriesValue;
        private final TextView diaryEntryCarbsExchangerValue;
        private final TextView diaryEntryFatExchangerValue;

        public DiaryEntryViewHolder(@NonNull View itemView, DiaryEntryAdapter.OnItemClickListener listener) {
            super(itemView);
/*            mProductName = itemView.findViewById(R.id.diary_text_meal_summary);
            mProductBrand = itemView.findViewById(R.id.text_product_brand);
            mProductQuantity = itemView.findViewById(R.id.text_product_quantity);*/

            diaryEntryCarbohydrateValue = itemView.findViewById(R.id.diaryEntryTextViewCarbsValue);
            diaryEntryFatValue = itemView.findViewById(R.id.diaryEntryTextViewFatValue);
            diaryEntryProteinsValue = itemView.findViewById(R.id.diaryEntryTextViewProteinsValue);
            //diaryEntryCaloriesValue = itemView.findViewById(R.id.diaryEntryTextViewCaloriesValue);
            diaryEntryCarbsExchangerValue = itemView.findViewById(R.id.diaryEntryTextViewCarbsExchangerValue);
            diaryEntryFatExchangerValue = itemView.findViewById(R.id.diaryEntryTextViewFatExchangerValue);


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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_diary_entry_overview, parent, false);
        DiaryEntryViewHolder diaryEntryViewHolder = new DiaryEntryViewHolder(view, mOnItemClickListener);
        return diaryEntryViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull DiaryEntryViewHolder holder, int position) {
        //Product currentDiaryEntry = diaryEntries.get(position);
        double carbohydrates = 0, fat = 0, proteins = 0;
        for (Product product : diaryEntries
        ) {
            carbohydrates = carbohydrates + product.getCarbohydrates();
            fat = fat + product.getFat();
            proteins = proteins + product.getProteins();
        }
        holder.diaryEntryCarbohydrateValue.setText(Globals.REAL_FORMATTER.format(carbohydrates));
        holder.diaryEntryFatValue.setText(Globals.REAL_FORMATTER.format(fat));
        holder.diaryEntryProteinsValue.setText(Globals.REAL_FORMATTER.format(proteins));
        holder.diaryEntryCarbsExchangerValue.setText(Globals.REAL_FORMATTER.
                format((carbohydrates) / 12));
        holder.diaryEntryFatExchangerValue.setText(Globals.REAL_FORMATTER.
                format((9 * fat + 4 * proteins) / 100));
    }


    @Override
    public int getItemCount() {
        if (diaryEntries == null) {
            Log.d(TAG, "getItemCount: No elements to display");
            return 0;
        }

        return diaryEntries.size();
        //return diarySize;
    }

    public void setDiaryEntries(List<Product> diaryEntries) {
        this.diaryEntries = diaryEntries;
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<Product> filteredList) {
        diaryEntries = filteredList;
        notifyDataSetChanged();
    }

    public void setDiarySize(int size) {
        diarySize = size;
    }
}
