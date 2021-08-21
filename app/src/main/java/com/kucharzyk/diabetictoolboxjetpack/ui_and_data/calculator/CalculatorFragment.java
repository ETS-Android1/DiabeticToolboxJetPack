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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;


import com.kucharzyk.diabetictoolboxjetpack.Globals;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.util.ArrayList;
import java.util.List;

public class CalculatorFragment extends Fragment {
    public static final String TAG = "CalculatorFragment";

    private TextView mMealCarbsValue;
    private TextView mMealFatValue;
    private TextView mMealProteinValue;
    private TextView mealCarbsExchangerValue;
    private TextView mealFatExchangerValue;

    private CalculatorViewModel calculatorViewModel;
    private FoodProductAdapter mFoodProductAdapter;

    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        calculatorViewModel = new ViewModelProvider(requireActivity()).get(CalculatorViewModel.class);
        View root = inflater.inflate(R.layout.fragment_calculator, container, false);
        navController = NavHostFragment.findNavController(this);

        buildRecyclerView(root);

        mMealCarbsValue = root.findViewById(R.id.text_carbs_summary_value);
        mMealFatValue = root.findViewById(R.id.text_fat_summary_value);
        mMealProteinValue = root.findViewById(R.id.text_proteins_summary_value);
        mealCarbsExchangerValue = root.findViewById(R.id.text_carbs_exchanger_summary_value);
        mealFatExchangerValue = root.findViewById(R.id.text_protein_fat_exchanger_summary_value);
        AutoCompleteTextView mMealTextView = root.findViewById(R.id.MealAutoCompleteTextView);
        CardView mMealSummaryCardView = root.findViewById(R.id.view_meal_summary_card_view);
        ConstraintLayout mMealSummaryConstraintLayout = root.findViewById(R.id.layout_meal_summary_constraint_layout);

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

                if (foodProducts != null) {
                    for (Product product : foodProducts) {
                        sumMealCarbsValue = sumMealCarbsValue + product.getCarbohydrates();
                        sumMealFatValue = sumMealFatValue + product.getFat();
                        sumMealProteinsValue = sumMealProteinsValue + product.getProteins();
                    }
                }
                mMealCarbsValue.setText(Globals.REAL_FORMATTER.format(sumMealCarbsValue));
                mMealFatValue.setText(Globals.REAL_FORMATTER.format(sumMealFatValue));
                mMealProteinValue.setText(Globals.REAL_FORMATTER.format(sumMealProteinsValue));
                mealCarbsExchangerValue.
                        setText(Globals.REAL_FORMATTER.format
                                (sumMealCarbsValue / 12));
                mealFatExchangerValue.
                        setText(Globals.REAL_FORMATTER.format
                                ((9 * sumMealFatValue + 4 * sumMealProteinsValue) / 100));
            }
        };

        final Observer<List<Product>> allProductsObserver = new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                mFoodProductAdapter.setProducts(products);
            }
        };

        mMealSummaryConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = CalculatorFragmentDirections.actionNavigationCalculatorToMealSummary();
                navController.navigate(action);
            }
        });

        calculatorViewModel.getMealSummary().observe(getViewLifecycleOwner(), mealSummaryObserver);
        calculatorViewModel.getAllProducts().observe(getViewLifecycleOwner(), allProductsObserver);
        return root;
    }


    private void filter(String productText) {
        List<Product> filteredList = new ArrayList<>();

        for (Product product : mFoodProductAdapter.getProductsList()) {
            if (product.getProductName().toLowerCase().contains(productText.toLowerCase())) {
                filteredList.add(product);
            }
        }
        mFoodProductAdapter.filterList(filteredList);
    }

/*    public void changeItem(int position, String mealName) {
        mAdapter.mFoodProducts.get(position).addMeal(mealName);
        mAdapter.notifyItemChanged(position);
    }*/


    private void buildRecyclerView(View rootView) {
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recyclerViewCalculatorFragment);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        mFoodProductAdapter = new FoodProductAdapter();

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mFoodProductAdapter);

        mFoodProductAdapter.setOnItemClickListener(new FoodProductAdapter.OnItemClickListener() {
            @Override
            public void onAddProductClick(int position) {
                if (!calculatorViewModel.getMeal().contains(mFoodProductAdapter.getProduct(position))) {
                    calculatorViewModel.getMeal().add(mFoodProductAdapter.getProduct(position));
                    calculatorViewModel.getMealSummary().setValue(calculatorViewModel.getMeal());
                }
            }

            @Override
            public void onDeleteProductClick(int position) {
                calculatorViewModel.getMeal().remove(mFoodProductAdapter.getProduct(position));
                calculatorViewModel.getMealSummary().setValue(calculatorViewModel.getMeal());
            }

            @Override
            public void onItemClick(int position) {
                Product product = mFoodProductAdapter.getProduct(position);

                @NonNull NavDirections action = CalculatorFragmentDirections.
                        actionNavigationCalculatorToProductSummaryFragment(product, position);
                navController.navigate(action);
            }
        });
    }

}