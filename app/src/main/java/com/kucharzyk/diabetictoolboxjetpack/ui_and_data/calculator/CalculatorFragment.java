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
    private FoodProductAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<ExampleFoodProduct> mExampleFoodProductList;

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
/*        mealTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment mealSelectionFragment = new MealSelection();
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_calculator, mealSelectionFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });*/
        return root;
    }

    private void filter(String mealText){
        ArrayList<ExampleFoodProduct> filteredList = new ArrayList<>();

        for (ExampleFoodProduct meal : mExampleFoodProductList){
            if(meal.getMealName().toLowerCase().contains(mealText.toLowerCase())) {
                filteredList.add(meal);
            }
        }
        mAdapter.filterList(filteredList);
    }

    public void changeItem(int position, String mealName){
        mExampleFoodProductList.get(position).addMeal(mealName);
        mAdapter.notifyItemChanged(position);
    }

    private void createExampleMealList(){
        mExampleFoodProductList = new ArrayList<>();
        mExampleFoodProductList.add(new ExampleFoodProduct("banana", 20.24, 0.33, 1.09));
        mExampleFoodProductList.add(new ExampleFoodProduct("apple", 10.1, 0.4, 0.4));
        mExampleFoodProductList.add(new ExampleFoodProduct("pear", 12.3, 0.2, 0.6));
        mExampleFoodProductList.add(new ExampleFoodProduct("cornflakes", 82.6, 7.4, 1.4));
        mExampleFoodProductList.add(new ExampleFoodProduct("beer", 3.8, 0.0, 0.5));
        mExampleFoodProductList.add(new ExampleFoodProduct("orange juice", 10.0, 0.2, 0.7));
        mExampleFoodProductList.add(new ExampleFoodProduct("sandwich", 23.0, 11.0, 14.0));
        mExampleFoodProductList.add(new ExampleFoodProduct("fish", 0.0, 12.0, 22.0));
        mExampleFoodProductList.add(new ExampleFoodProduct("chicken wings", 8.9, 21.8, 19.6));
    }

    private void buildRecyclerView(View rootView){
        mRecyclerView = rootView.findViewById(R.id.recyclerView2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new FoodProductAdapter(mExampleFoodProductList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new FoodProductAdapter.OnItemClickListener() {
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