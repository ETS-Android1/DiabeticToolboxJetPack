package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DiaryEntryAdapter extends ListAdapter<Product, DiaryEntryViewHolder> {

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
