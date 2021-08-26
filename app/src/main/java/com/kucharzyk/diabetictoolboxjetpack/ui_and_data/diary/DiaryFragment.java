package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithMealsAndProducts;

import java.util.ArrayList;
import java.util.List;

public class DiaryFragment extends Fragment {
    public static final String TAG = "DiaryFragment";

    private DiaryEntryViewModel diaryEntryViewModel;
    private DiaryEntryAdapter mAdapter;
    private List<DiaryEntrySummary> diaryEntrySummaries = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        diaryEntryViewModel =
                new ViewModelProvider(this).get(DiaryEntryViewModel.class);
        View root = inflater.inflate(R.layout.diary_fragment, container, false);

        buildRecyclerView(root);

        final Observer<List<DiaryEntryWithMealsAndProducts>> diaryEntriesObserver = new Observer<List<DiaryEntryWithMealsAndProducts>>() {
            @Override
            public void onChanged(List<DiaryEntryWithMealsAndProducts> diaryEntryWithMealsAndProducts) {
                diaryEntryWithMealsAndProducts = diaryEntryViewModel.getAllDiaryEntries().getValue();
                assert diaryEntryWithMealsAndProducts != null;
                for (DiaryEntryWithMealsAndProducts diaryEntry:diaryEntryWithMealsAndProducts
                     ) {
                        diaryEntrySummaries.add(new DiaryEntrySummary(diaryEntry.meals, diaryEntry.diaryEntry.getDiaryEntryDate()));
                }
                mAdapter.setDiaryEntries(diaryEntrySummaries);
            }
        };

        diaryEntryViewModel.getAllDiaryEntries().observe(getViewLifecycleOwner(), diaryEntriesObserver);

        return root;
    }

    private void buildRecyclerView(View rootView){
        RecyclerView mDiaryRecyclerView = rootView.findViewById(R.id.diary_recycler_view);
        mDiaryRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new DiaryEntryAdapter();

        mDiaryRecyclerView.setLayoutManager(mLayoutManager);
        mDiaryRecyclerView.setAdapter(mAdapter);
    }

}