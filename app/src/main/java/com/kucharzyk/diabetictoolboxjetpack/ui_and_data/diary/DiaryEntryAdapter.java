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

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DiaryEntryAdapter extends RecyclerView.Adapter<DiaryEntryAdapter.DiaryEntryViewHolder> {
    public static final String TAG = "DiaryEntryAdapter";

    private List<DiaryEntrySummary> diaryEntries = new ArrayList<>();
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

/*        private final TextView mProductName;
        public TextView mProductBrand;
        public TextView mProductQuantity;*/

        private final TextView diaryEntryCarbohydrateValue;
        private final TextView diaryEntryFatValue;
        private final TextView diaryEntryProteinsValue;
        //private final TextView diaryEntryCaloriesValue;
        private final TextView diaryEntryCarbsExchangerValue;
        private final TextView diaryEntryFatExchangerValue;
        private final TextView diaryEntryDate;

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
            diaryEntryDate = itemView.findViewById(R.id.diaryEntryTextViewDate);


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
        DiaryEntrySummary currentDiaryEntry = diaryEntries.get(position);
        holder.diaryEntryDate.setText(currentDiaryEntry.getDiaryEntryDate().
                format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        holder.diaryEntryCarbohydrateValue.setText(Globals.REAL_FORMATTER.
                format(currentDiaryEntry.getCarbohydrates()));
        holder.diaryEntryFatValue.setText(Globals.REAL_FORMATTER.
                format(currentDiaryEntry.getFat()));
        holder.diaryEntryProteinsValue.setText(Globals.REAL_FORMATTER.
                format(currentDiaryEntry.getProteins()));
        holder.diaryEntryCarbsExchangerValue.setText(Globals.REAL_FORMATTER.
                format((currentDiaryEntry.getCarbohydrates()) / 12));
        holder.diaryEntryFatExchangerValue.setText(Globals.REAL_FORMATTER.
                format((9 * currentDiaryEntry.getFat() + 4 * currentDiaryEntry.getProteins()) / 100));
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

    public void setDiaryEntries(List<DiaryEntrySummary> diaryEntries) {
        this.diaryEntries = diaryEntries;
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<DiaryEntrySummary> filteredList) {
        diaryEntries = filteredList;
        notifyDataSetChanged();
    }
}
