package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

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

    private List<DiaryMealEntrySummary> diaryMealEntries = new ArrayList<>();
    private List<DiaryTrainingEntrySummary> diaryTrainingEntries = new ArrayList<>();
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
        private final TextView diaryEntryCaloriesValue;
        private final TextView diaryEntryCarbsExchangerValue;
        private final TextView diaryEntryFatExchangerValue;

        private final TextView diaryEntryCaloriesBurnedValue;
        private final TextView diaryEntryCarbsExchangerUsedValue;
        private final TextView diaryEntryProteinFatExchangerUsedValue;

        private final TextView diaryEntryDate;

        public DiaryEntryViewHolder(@NonNull View itemView, DiaryEntryAdapter.OnItemClickListener listener) {
            super(itemView);
/*            mProductName = itemView.findViewById(R.id.diary_text_meal_summary);
            mProductBrand = itemView.findViewById(R.id.text_product_brand);
            mProductQuantity = itemView.findViewById(R.id.text_product_quantity);*/

            diaryEntryCarbohydrateValue = itemView.findViewById(R.id.diaryEntryTextViewCarbsValue);
            diaryEntryFatValue = itemView.findViewById(R.id.diaryEntryTextViewFatValue);
            diaryEntryProteinsValue = itemView.findViewById(R.id.diaryEntryTextViewProteinsValue);
            diaryEntryCaloriesValue = itemView.findViewById(R.id.diaryEntryTextViewCaloriesValue);
            diaryEntryCarbsExchangerValue = itemView.findViewById(R.id.diaryEntryTextViewCarbsExchangerValue);
            diaryEntryFatExchangerValue = itemView.findViewById(R.id.diaryEntryTextViewFatExchangerValue);

            diaryEntryCaloriesBurnedValue = itemView.findViewById(R.id.diaryEntryTextCaloriesBurnedValue);
            diaryEntryCarbsExchangerUsedValue = itemView.findViewById(R.id.diaryEntryTextViewCarbsExchangerUsedValue);
            diaryEntryProteinFatExchangerUsedValue = itemView.findViewById(R.id.diaryEntryTextViewFatExchangerUsedValue);

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

        DiaryTrainingEntrySummary currentDiaryTrainingEntrySummary = diaryTrainingEntries.get(position);
        DiaryMealEntrySummary currentDiaryMealEntrySummary = diaryMealEntries.get(position);

        String diaryEntryCarbohydrate = Globals.REAL_FORMATTER.
                format(currentDiaryMealEntrySummary.getCarbohydrates()) + " g";
        String diaryEntryFat = Globals.REAL_FORMATTER.
                format(currentDiaryMealEntrySummary.getFat()) + " g";
        String diaryEntryProteins = Globals.REAL_FORMATTER.
                format(currentDiaryMealEntrySummary.getProteins()) + " g";
        String diaryEntryCalories = Globals.REAL_FORMATTER.
                format(currentDiaryMealEntrySummary.getCalories()) + " kcal";
        String diaryEntryCarbsExchanger = Globals.REAL_FORMATTER.
                format((currentDiaryMealEntrySummary.getCarbohydrates()) / 12) + " units";
        String diaryEntryFatExchanger = Globals.REAL_FORMATTER.
                format((9 * currentDiaryMealEntrySummary.getFat() + 4 * currentDiaryMealEntrySummary.getProteins()) / 100) + " units";

        String diaryEntryCaloriesBurned = Globals.REAL_FORMATTER.
                format(currentDiaryTrainingEntrySummary.getCaloriesBurned()) + " kcal";
        String diaryEntryCarbsExchangerUsed = Globals.REAL_FORMATTER.
                format(currentDiaryTrainingEntrySummary.getCarbsExchangerUsed())  + " units";
        String diaryEntryProteinFatExchangerUsed = Globals.REAL_FORMATTER.
                format(currentDiaryTrainingEntrySummary.getProteinFatExchangerUsed()) + " units";

        holder.diaryEntryDate.setText(currentDiaryMealEntrySummary.getDiaryEntryDate().
                format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        holder.diaryEntryCarbohydrateValue.setText(diaryEntryCarbohydrate);
        holder.diaryEntryFatValue.setText(diaryEntryFat);
        holder.diaryEntryProteinsValue.setText(diaryEntryProteins);
        holder.diaryEntryCaloriesValue.setText(diaryEntryCalories);
        holder.diaryEntryCarbsExchangerValue.setText(diaryEntryCarbsExchanger);
        holder.diaryEntryFatExchangerValue.setText(diaryEntryFatExchanger);

        holder.diaryEntryCaloriesBurnedValue.setText(diaryEntryCaloriesBurned);
        holder.diaryEntryCarbsExchangerUsedValue.setText(diaryEntryCarbsExchangerUsed);
        holder.diaryEntryProteinFatExchangerUsedValue.setText(diaryEntryProteinFatExchangerUsed);
    }


    @Override
    public int getItemCount() {
        if (diaryMealEntries == null) {
            return 0;
        }

        return diaryMealEntries.size();
    }

    public void setDiaryMealEntries(List<DiaryMealEntrySummary> diaryMealEntries) {
        this.diaryMealEntries = diaryMealEntries;
        notifyDataSetChanged();
    }

    public void setDiaryTrainingEntries(List<DiaryTrainingEntrySummary> diaryTrainingEntries) {
        this.diaryTrainingEntries = diaryTrainingEntries;
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<DiaryMealEntrySummary> filteredList) {
        diaryMealEntries = filteredList;
        notifyDataSetChanged();
    }
}
