package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;
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
import java.util.Objects;

public class CalculatorFragment extends Fragment {

    private static final DecimalFormat REAL_FORMATTER = new DecimalFormat("0.##");

    private CalculatorViewModel calculatorViewModel;
    private FoodProductAdapter mAdapter;
    private ArrayList<Product> mMeal;

    private TextView mMealCarbsValue;
    private TextView mMealFatValue;
    private TextView mMealProteinValue;

    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calculatorViewModel = new ViewModelProvider(this).get(CalculatorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calculator, container, false);
        navController = NavHostFragment.findNavController(this);

        buildRecyclerView(root);

        AutoCompleteTextView mMealTextView = root.findViewById(R.id.MealAutoCompleteTextView);
        mMealCarbsValue = root.findViewById(R.id.text_carbs_summary_value);
        mMealFatValue = root.findViewById(R.id.text_fat_summary_value);
        mMealProteinValue = root.findViewById(R.id.text_proteins_summary_value);
        CardView mMealSummaryCardView = root.findViewById(R.id.view_meal_summary_card_view);
        ConstraintLayout mMealSummaryConstraintLayout = root.findViewById(R.id.layout_meal_summary_constraint_layout);
        mMeal = new ArrayList<>();

        mMealTextView.addTextChangedListener(new TextWatcher() {
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

        final Observer<List<Product>> mealSummaryObserver = new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> foodProducts) {

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
                requireActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.view_products_selection_card_view, MealSummaryFragment, "findThisFragment")
                        .addToBackStack(null)
                        .commit();
            }
        });



        calculatorViewModel.getMealSummary().observe(getViewLifecycleOwner(), mealSummaryObserver);
        calculatorViewModel.getAllProducts().observe(getViewLifecycleOwner(), allProductsObserver);
        return root;
    }


    private void filter(String productText) {
        List<Product> filteredList = new ArrayList<>();

        for (Product product : mAdapter.mFoodProductsFull) {
            if (product.getProductName().toLowerCase().contains(productText.toLowerCase())) {
                filteredList.add(product);
            }
        }
        mAdapter.filterList(filteredList);
    }

/*    public void changeItem(int position, String mealName) {
        mAdapter.mFoodProducts.get(position).addMeal(mealName);
        mAdapter.notifyItemChanged(position);
    }*/


    private void buildRecyclerView(View rootView) {
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recycler_view_product);
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
                String productName = mAdapter.mFoodProducts.get(position).getProductName();

                @NonNull NavDirections action = CalculatorFragmentDirections.
                        actionNavigationCalculatorToProductSummaryFragment(productName);
                navController.navigate(action);
            }
        });
    }

}