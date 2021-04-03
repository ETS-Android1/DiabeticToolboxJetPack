package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;


import com.kucharzyk.diabetictoolboxjetpack.R;

import java.util.ArrayList;

public class CalculatorFragment extends Fragment {

    private CalculatorViewModel calculatorViewModel;
    private RecyclerView mRecyclerView;
    private MealAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ExampleMeal> mExampleMealList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_calculator, container, false);

        createExampleMealList();
        buildRecyclerView(root);

        AutoCompleteTextView mealTextView = root.findViewById(R.id.MealAutoCompleteTextView);
        mealTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
        return root;
    }

    private void filter(String mealText){
        ArrayList<ExampleMeal> filteredList = new ArrayList<>();

        for (ExampleMeal meal : mExampleMealList){
            if(meal.getMealName().toLowerCase().contains(mealText.toLowerCase())) {
                filteredList.add(meal);
            }
        }
        mAdapter.filterList(filteredList);
    }

    public void changeItem(int position, String mealName){
        mExampleMealList.get(position).addMeal(mealName);
        mAdapter.notifyItemChanged(position);
    }

    private void createExampleMealList(){
        mExampleMealList = new ArrayList<>();
        mExampleMealList.add(new ExampleMeal("banana"));
        mExampleMealList.add(new ExampleMeal("apple"));
        mExampleMealList.add(new ExampleMeal("pear"));
        mExampleMealList.add(new ExampleMeal("cornflakes"));
        mExampleMealList.add(new ExampleMeal("bear"));
        mExampleMealList.add(new ExampleMeal("orange juice"));
        mExampleMealList.add(new ExampleMeal("sandwich"));
        mExampleMealList.add(new ExampleMeal("fish"));
        mExampleMealList.add(new ExampleMeal("chicken wings"));
    }

    private void buildRecyclerView(View rootView){
        mRecyclerView = rootView.findViewById(R.id.recyclerView2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new MealAdapter(mExampleMealList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new MealAdapter.OnItemClickListener() {
            @Override
            public void onAddMealClick(int position) {

            }

            @Override
            public void onItemClick(int position) {
                changeItem(position, "Selected");
            }
        });
    }
}