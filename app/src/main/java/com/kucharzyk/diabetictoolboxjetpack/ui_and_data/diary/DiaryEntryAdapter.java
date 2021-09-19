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

    private List<DiaryMealEntrySummary> diaryMealEntries = new ArrayList<>();
    private List<DiaryMealEntrySummary> diaryMealEntriesFull;
    private List<DiaryTrainingEntrySummary> diaryTrainingEntries = new ArrayList<>();
    private List<DiaryTrainingEntrySummary> diaryTrainingEntriesFull;
    private List<DiaryMeasurementEntrySummary> diaryMeasurementEntries = new ArrayList<>();
    private List<DiaryMeasurementEntrySummary> diaryMeasurementEntriesFull;

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

        private final TextView diarEntryGlycemiaMeasurementsTakenValue;
        private final TextView diarEntryHyperglycemiaMeasurementsTakenValue;
        private final TextView diarEntryStandardMeasurementsTakenValue;
        private final TextView diarEntryHypoglycemiaMeasurementsTakenValue;

        private final TextView diaryEntryDate;

        public DiaryEntryViewHolder(@NonNull View itemView) {
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

            diarEntryGlycemiaMeasurementsTakenValue = itemView.findViewById(R.id.diaryEntryTextMeasurementsTakenValue);
            diarEntryHyperglycemiaMeasurementsTakenValue = itemView.findViewById(R.id.diaryEntryTextViewHyperglycemiaValue);
            diarEntryStandardMeasurementsTakenValue = itemView.findViewById(R.id.diaryEntryTextViewMeasurementStandardValue);
            diarEntryHypoglycemiaMeasurementsTakenValue = itemView.findViewById(R.id.diaryEntryTextViewHypoglycemiaValue);

            diaryEntryDate = itemView.findViewById(R.id.diaryEntryTextViewDate);
        }
    }


    @NonNull
    @Override
    public DiaryEntryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_diary_entry_overview, parent, false);
        DiaryEntryViewHolder diaryEntryViewHolder = new DiaryEntryViewHolder(view);
        return diaryEntryViewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull DiaryEntryViewHolder holder, int position) {

        Log.d(TAG, "onBindViewHolder: position " + position);
        try {
            DiaryTrainingEntrySummary currentDiaryTrainingEntrySummary = diaryTrainingEntries.get(position);

            String diaryEntryCaloriesBurned = Globals.REAL_FORMATTER.
                    format(currentDiaryTrainingEntrySummary.getCaloriesBurned()) + " kcal";
            String diaryEntryCarbsExchangerUsed = Globals.REAL_FORMATTER.
                    format(currentDiaryTrainingEntrySummary.getCarbsExchangerUsed())  + " units";
            String diaryEntryProteinFatExchangerUsed = Globals.REAL_FORMATTER.
                    format(currentDiaryTrainingEntrySummary.getProteinFatExchangerUsed()) + " units";

            holder.diaryEntryDate.setText(currentDiaryTrainingEntrySummary.getDiaryEntryDate().
                    format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            holder.diaryEntryCaloriesBurnedValue.setText(diaryEntryCaloriesBurned);
            holder.diaryEntryCarbsExchangerUsedValue.setText(diaryEntryCarbsExchangerUsed);
            holder.diaryEntryProteinFatExchangerUsedValue.setText(diaryEntryProteinFatExchangerUsed);

        } catch (IndexOutOfBoundsException e) {
            Log.i(TAG, "onBindViewHolder: diaryTrainingEntries not ready yet");
        }

        try {
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

            holder.diaryEntryCarbohydrateValue.setText(diaryEntryCarbohydrate);
            holder.diaryEntryFatValue.setText(diaryEntryFat);
            holder.diaryEntryProteinsValue.setText(diaryEntryProteins);
            holder.diaryEntryCaloriesValue.setText(diaryEntryCalories);
            holder.diaryEntryCarbsExchangerValue.setText(diaryEntryCarbsExchanger);
            holder.diaryEntryFatExchangerValue.setText(diaryEntryFatExchanger);

        } catch (IndexOutOfBoundsException e) {
            Log.i(TAG, "onBindViewHolder: diaryMealEntries not ready yet");
        }

        try {
            DiaryMeasurementEntrySummary currentDiaryMeasurementEntrySummary = diaryMeasurementEntries.get(position);

            String diarEntryGlycemiaMeasurementsTaken = Globals.REAL_FORMATTER.
                    format(currentDiaryMeasurementEntrySummary.getMeasurementsCount());
            String diarEntryHyperglycemiaMeasurementsTaken = Globals.REAL_FORMATTER.
                    format(currentDiaryMeasurementEntrySummary.getHyperglycemiaMeasurementsCount());
            String diarEntryStandardMeasurementsTaken = Globals.REAL_FORMATTER.
                    format(currentDiaryMeasurementEntrySummary.getStandardMeasurementsCount());
            String diarEntryHypoglycemiaMeasurementsTaken = Globals.REAL_FORMATTER.
                    format(currentDiaryMeasurementEntrySummary.getHypoglycemiaMeasurementsCount());

            holder.diarEntryGlycemiaMeasurementsTakenValue.setText(diarEntryGlycemiaMeasurementsTaken);
            holder.diarEntryHyperglycemiaMeasurementsTakenValue.setText(diarEntryHyperglycemiaMeasurementsTaken);
            holder.diarEntryStandardMeasurementsTakenValue.setText(diarEntryStandardMeasurementsTaken);
            holder.diarEntryHypoglycemiaMeasurementsTakenValue.setText(diarEntryHypoglycemiaMeasurementsTaken);

        } catch (IndexOutOfBoundsException e) {
            Log.i(TAG, "onBindViewHolder: diaryMesurementEntries not ready yet");
        }

    }


    @Override
    public int getItemCount() {
        int biggerList;
        biggerList = Math.max(diaryMealEntries.size(),
                Math.max(diaryTrainingEntries.size(), diaryMeasurementEntries.size()));
        Log.d(TAG, "getItemCount: biggerList= " + biggerList);
        return biggerList;
    }

    public void setDiaryMealEntries(List<DiaryMealEntrySummary> diaryMealEntries) {
        this.diaryMealEntries = diaryMealEntries;
        diaryMealEntriesFull = new ArrayList<>(diaryMealEntries);
        notifyDataSetChanged();
    }

    public void setDiaryTrainingEntries(List<DiaryTrainingEntrySummary> diaryTrainingEntries) {
        this.diaryTrainingEntries = diaryTrainingEntries;
        diaryTrainingEntriesFull = new ArrayList<>(diaryTrainingEntries);
        notifyDataSetChanged();
    }

    public void setDiaryMeasurementEntries(List<DiaryMeasurementEntrySummary> diaryMeasurementEntries) {
        this.diaryMeasurementEntries = diaryMeasurementEntries;
        diaryMeasurementEntriesFull = new ArrayList<>(diaryMeasurementEntries);
        notifyDataSetChanged();
    }

    public void filterList(ArrayList<DiaryMealEntrySummary> filteredMealList,
                           ArrayList<DiaryTrainingEntrySummary> filteredTrainingList,
                           ArrayList<DiaryMeasurementEntrySummary> filteredMeasurementList) {
        diaryMealEntries = filteredMealList;
        diaryTrainingEntries = filteredTrainingList;
        diaryMeasurementEntries = filteredMeasurementList;
        notifyDataSetChanged();
    }

    public List<DiaryMealEntrySummary> getDiaryMealEntriesList() {
        if (diaryMealEntriesFull != null){
            return new ArrayList<>(diaryMealEntriesFull);
        }
        else {
            return new ArrayList<>(diaryMealEntries);
        }
    }

    public List<DiaryTrainingEntrySummary> getDiaryTrainingEntriesList() {
        if (diaryTrainingEntriesFull != null){
            return new ArrayList<>(diaryTrainingEntriesFull);
        }
        else {
            return new ArrayList<>(diaryTrainingEntries);
        }
    }

    public List<DiaryMeasurementEntrySummary> getDiaryMeasurementEntriesList() {
        if (diaryMeasurementEntriesFull != null){
            return new ArrayList<>(diaryMeasurementEntriesFull);
        }
        else {
            return new ArrayList<>(diaryMeasurementEntries);
        }
    }
}
