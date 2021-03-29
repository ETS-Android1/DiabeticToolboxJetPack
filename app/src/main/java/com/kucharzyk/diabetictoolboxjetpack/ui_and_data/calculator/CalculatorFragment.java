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
    private ArrayList<ExampleMeal> exampleMealList;

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

/*        calculatorViewModel =
                new ViewModelProvider(this).get(CalculatorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calculator, container, false);
        final TextView textView = root.findViewById(R.id.autoCompleteTextViewMeal);
        calculatorViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });*/
        return root;
    }

    private void filter(String mealText){
        ArrayList<ExampleMeal> filteredList = new ArrayList<>();

        for (ExampleMeal meal : exampleMealList){
            if(meal.getMealName().toLowerCase().contains(mealText.toLowerCase())) {
                filteredList.add(meal);
            }
        }
        mAdapter.filterList(filteredList);
    }

    private void createExampleMealList(){
        exampleMealList = new ArrayList<>();
        exampleMealList.add(new ExampleMeal("banana"));
        exampleMealList.add(new ExampleMeal("apple"));
        exampleMealList.add(new ExampleMeal("pear"));
        exampleMealList.add(new ExampleMeal("cornflakes"));
        exampleMealList.add(new ExampleMeal("bear"));
        exampleMealList.add(new ExampleMeal("orange juice"));
        exampleMealList.add(new ExampleMeal("sandwich"));
        exampleMealList.add(new ExampleMeal("fish"));
        exampleMealList.add(new ExampleMeal("chicken wings"));
    }

    private void buildRecyclerView(View rootView){
        mRecyclerView = rootView.findViewById(R.id.recyclerView2);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new MealAdapter(exampleMealList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}