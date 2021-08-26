package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.exercises;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.R;

public class ExercisesFragment extends Fragment {

    private ExercisesViewModel exercisesViewModel;
    private NavController navController;

    public static ExercisesFragment newInstance() {
        return new ExercisesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        exercisesViewModel = new ViewModelProvider(requireActivity()).get(ExercisesViewModel.class);
        View root = inflater.inflate(R.layout.exercises_fragment, container, false);
        navController = NavHostFragment.findNavController(this);

        buildRecyclerView(root);
        return root;
    }

    private void buildRecyclerView(View rootView) {
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recyclerViewCalculatorFragment);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

    }


}