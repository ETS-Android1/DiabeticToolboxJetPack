package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kucharzyk.diabetictoolboxjetpack.R;

import java.util.ArrayList;

public class DiaryFragment extends Fragment {

    private DiaryEntryViewModel diaryEntryViewModel;
    private DiaryEntryAdapter mAdapter;
    private RecyclerView mDiaryRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<DiaryEntry> mDiaryEntryList;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        diaryEntryViewModel =
                new ViewModelProvider(this).get(DiaryEntryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_diary, container, false);

        createExampleDiaryEntryList();
        buildRecyclerView(root);

        return root;
    }

    private void buildRecyclerView(View rootView){
        mDiaryRecyclerView = rootView.findViewById(R.id.diary_recycler_view);
        mDiaryRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new DiaryEntryAdapter(mDiaryEntryList);

        mDiaryRecyclerView.setLayoutManager(mLayoutManager);
        mDiaryRecyclerView.setAdapter(mAdapter);
    }

    private void createExampleDiaryEntryList(){
        mDiaryEntryList = new ArrayList<>();
        mDiaryEntryList.add(new DiaryEntry("banana", 20.24, 0.33, 1.09));;
    }

    public void addDiaryEntry(DiaryEntry newDiaryEntry){
        mDiaryEntryList.add(newDiaryEntry);
    }
}