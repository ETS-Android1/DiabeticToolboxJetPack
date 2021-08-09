package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.util.List;

public class MealSummaryFragment extends Fragment {

    private CalculatorViewModel calculatorViewModel;
    private NavController navController;
    private MealSummaryAdapter mMealSummaryAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        calculatorViewModel = new ViewModelProvider(requireActivity()).get(CalculatorViewModel.class);
        View child = inflater.inflate(R.layout.fragment_meal_summary, container, false);
        navController = NavHostFragment.findNavController(this);
        buildRecyclerView(child);

        final Observer<List<Product>> mealSummaryObserver = new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                mMealSummaryAdapter.setProducts(products);
            }
        };

        calculatorViewModel.getMealSummary().observe(getViewLifecycleOwner(), mealSummaryObserver);
        return child;
    }

    private void buildRecyclerView(View rootView) {
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recyclerViewMealSummary);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        mMealSummaryAdapter = new MealSummaryAdapter();

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mMealSummaryAdapter);
    }
}