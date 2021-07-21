package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.util.ArrayList;
import java.util.List;

public class DiaryFragment extends Fragment {

    private DiaryEntryViewModel diaryEntryViewModel;
    private DiaryEntryAdapter mAdapter;
    private RecyclerView mDiaryRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private FloatingActionButton mAddProductButton;
    private FloatingActionButton mSubProductButton;
    Product product1 = new Product("TestProduct2.0", 11.0, 6.0, 3.0);


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        diaryEntryViewModel =
                new ViewModelProvider(this).get(DiaryEntryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_diary, container, false);

        buildRecyclerView(root);

        mAddProductButton = root.findViewById(R.id.diary_button_add_product);
        mSubProductButton = root.findViewById(R.id.diary_button_subtract_product);

        final Observer<List<Product>> diaryEntriesObserver = new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> diaryEntries) {
                mAdapter.setDiaryEntries(diaryEntries);
            }
        };

        mAddProductButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                diaryEntryViewModel.insert(product1);
            }
        });

        mSubProductButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                diaryEntryViewModel.deleteAllProducts();
            }
        });

        diaryEntryViewModel.getAllProducts().observe(getViewLifecycleOwner(), diaryEntriesObserver);
        return root;
    }

    private void buildRecyclerView(View rootView){
        mDiaryRecyclerView = rootView.findViewById(R.id.diary_recycler_view);
        mDiaryRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new DiaryEntryAdapter();

        mDiaryRecyclerView.setLayoutManager(mLayoutManager);
        mDiaryRecyclerView.setAdapter(mAdapter);
    }

}