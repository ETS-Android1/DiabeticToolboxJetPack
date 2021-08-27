package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.exercises;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Exercise;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.util.List;

public class ExercisesFragment extends Fragment {

    private ExercisesViewModel exercisesViewModel;
    private ExerciseAdapter exerciseAdapter;
    private NavController navController;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        exercisesViewModel = new ViewModelProvider(requireActivity()).get(ExercisesViewModel.class);
        View root = inflater.inflate(R.layout.exercises_fragment, container, false);
        navController = NavHostFragment.findNavController(this);

        buildRecyclerView(root);


        final Observer<List<Exercise>> allExercisesObserver = new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                exerciseAdapter.setExercises(exercises);
            }
        };

        exercisesViewModel.getAllExercises().observe(getViewLifecycleOwner(),allExercisesObserver);
        return root;
    }

    private void buildRecyclerView(View rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.exercises_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        exerciseAdapter = new ExerciseAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(exerciseAdapter);

    }


}