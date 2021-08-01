package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class CalculatorFragment extends Fragment {

    private static final DecimalFormat REAL_FORMATTER = new DecimalFormat("0.##");

    private CalculatorViewModel calculatorViewModel;
    private FoodProductAdapter mAdapter;
    //private ArrayList<FoodProduct> mFoodProductList;
    private ArrayList<Product> mMeal;

    private TextView mMealCarbsValue;
    private TextView mMealFatValue;
    private TextView mMealProteinValue;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calculatorViewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calculator, container, false);

        buildRecyclerView(root);

        AutoCompleteTextView mMealTextView = root.findViewById(R.id.MealAutoCompleteTextView);
        mMealCarbsValue = root.findViewById(R.id.text_carbs_summary_value);
        mMealFatValue = root.findViewById(R.id.text_fat_summary_value);
        mMealProteinValue = root.findViewById(R.id.text_proteins_summary_value);
        FloatingActionButton mButtonConsumeMeal = root.findViewById(R.id.calculator_button_consume_meal);
        CardView mMealSummaryCardView = root.findViewById(R.id.view_meal_summary_card_view);
        ConstraintLayout mMealSummaryConstraintLayout = root.findViewById(R.id.layout_meal_summary_constraint_layout);
        mMeal = new ArrayList<>();
        Product product2 = new Product("TestProduct3.0", 12.0, 77.0, 4.0);

        mMealTextView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
//                filter(s.toString());
            }
        });

        final Observer<ArrayList<Product>> mealSummaryObserver = new Observer<ArrayList<Product>>() {
            @Override
            public void onChanged(ArrayList<Product> foodProducts) {

                Double sumMealCarbsValue = 0.0;
                Double sumMealFatValue = 0.0;
                Double sumMealProteinsValue = 0.0;

                if (foodProducts == null) {
                    mMealCarbsValue.setText(sumMealCarbsValue.toString());
                    mMealFatValue.setText(sumMealFatValue.toString());
                    mMealProteinValue.setText(sumMealProteinsValue.toString());
                } else {
                    for (Product product : foodProducts) {
                        sumMealCarbsValue = sumMealCarbsValue + product.getCarbohydrates();
                        sumMealFatValue = sumMealFatValue + product.getFat();
                        sumMealProteinsValue = sumMealProteinsValue + product.getProteins();
                    }
                    mMealCarbsValue.setText(REAL_FORMATTER.format(sumMealCarbsValue));
                    mMealFatValue.setText(REAL_FORMATTER.format(sumMealFatValue));
                    mMealProteinValue.setText(REAL_FORMATTER.format(sumMealProteinsValue));
                }

            }
        };

        final Observer<List<Product>> allProductsObserver = new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                mAdapter.setProducts(products);
            }
        };

        mMealSummaryConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MealSummary MealSummaryFragment = new MealSummary();
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.view_products_selection_card_view, MealSummaryFragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });

        mButtonConsumeMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorViewModel.insert(product2);
            }
        });

        calculatorViewModel.getMealSummary().observe(getViewLifecycleOwner(), mealSummaryObserver);
        calculatorViewModel.getAllProducts().observe(getViewLifecycleOwner(), allProductsObserver);
        return root;
    }


/*    private void filter(String mealText) {
        ArrayList<Product> filteredList = new ArrayList<>();

        for (Product meal : mFoodProductList) {
            if (meal.getProductName().toLowerCase().contains(mealText.toLowerCase())) {
                filteredList.add(meal);
            }
        }
        mAdapter.filterList(filteredList);
    }*/

    public void changeItem(int position, String mealName) {
        mAdapter.mFoodProducts.get(position).addMeal(mealName);
        mAdapter.notifyItemChanged(position);
    }


    private void buildRecyclerView(View rootView) {
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recyclerView2);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new FoodProductAdapter();

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new FoodProductAdapter.OnItemClickListener() {
            @Override
            public void onAddProductClick(int position) {
                mMeal.add(mAdapter.mFoodProducts.get(position));
                calculatorViewModel.getMealSummary().setValue(mMeal);
            }

            @Override
            public void onDeleteProductClick(int position) {
                mMeal.remove(mAdapter.mFoodProducts.get(position));
                calculatorViewModel.getMealSummary().setValue(mMeal);
            }

            @Override
            public void onItemClick(int position) {
                changeItem(position, "Selected");
            }
        });
    }

}