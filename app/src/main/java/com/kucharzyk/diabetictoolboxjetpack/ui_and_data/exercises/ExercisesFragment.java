package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.exercises;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.kucharzyk.diabetictoolboxjetpack.Globals;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Exercise;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ExercisesFragment extends Fragment {

    private ExercisesViewModel exercisesViewModel;
    private User currentUser;
    private ExerciseAdapter exerciseAdapter;
    private NavController navController;

    private TextView mTrainingCaloriesBurnedValue;
    private TextView mTrainingCarbohydrateExchangersUsed;
    private TextView mTrainingDuration;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        exercisesViewModel = new ViewModelProvider(requireActivity()).get(ExercisesViewModel.class);
        View root = inflater.inflate(R.layout.exercises_fragment, container, false);
        navController = NavHostFragment.findNavController(this);

        buildRecyclerView(root);

        mTrainingCaloriesBurnedValue = root.findViewById(R.id.exercises_text_calories_burned_value);
        mTrainingCarbohydrateExchangersUsed = root.findViewById(R.id.exercises_text_carb_exchangers_used_value);
        mTrainingDuration = root.findViewById(R.id.exercises_text_time_spent_value);
        TextInputEditText exerciseSearchBar = root.findViewById(R.id.exercises_fragment_TextInputEditText_searched_exercise);
        ConstraintLayout mTrainingSummaryConstraintLayout = root.findViewById(R.id.exercises_constraint_layout_summary);

        exerciseSearchBar.addTextChangedListener(new TextWatcher() {
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

        final Observer<List<Exercise>> trainingObserver = new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exerciseList) {
                Double sumTrainingCaloriesBurned = 0.0;
                Double sumTrainingCarbohydrateExchangersUsed = 0.0;
                Double sumTrainingDuration = 0.0;

                if (exerciseList != null) {
                    for (Exercise exercise : exerciseList) {
                        sumTrainingCaloriesBurned += Globals.calculateCaloriesBurned(exercise.getExerciseDuration()
                                , exercise.getMetabolicEquivalentOfTask(),
                                Objects.requireNonNull(currentUser.getWeight()));
                        sumTrainingCarbohydrateExchangersUsed += sumTrainingCaloriesBurned / 4 / 12;
                        sumTrainingDuration += exercise.getExerciseDuration();
                    }
                }
                String caloriesBurned = Globals.REAL_FORMATTER.format(sumTrainingCaloriesBurned) + " kcal";
                String carbExchangerUsed = Globals.REAL_FORMATTER.format(sumTrainingCarbohydrateExchangersUsed) + " units";
                String trainingDuration = Globals.REAL_FORMATTER.format(sumTrainingDuration) + " min";

                mTrainingCaloriesBurnedValue.setText(caloriesBurned);
                mTrainingCarbohydrateExchangersUsed.setText(carbExchangerUsed);
                mTrainingDuration.setText(trainingDuration);
            }
        };


        final Observer<List<Exercise>> allExercisesObserver = new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exercises) {
                exerciseAdapter.setExercises(exercises);
            }
        };

        final Observer<User> currentUserObserver = new Observer<User>() {
            @Override
            public void onChanged(User user) {
                exerciseAdapter.setCurrentUser(user);
                currentUser = exerciseAdapter.getCurrentUser();
            }
        };

        mTrainingSummaryConstraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections action = ExercisesFragmentDirections.actionNavigationExercisesToTrainingSummaryFragment(currentUser);
                navController.navigate(action);
            }
        });

        exercisesViewModel.getAllExercises().observe(getViewLifecycleOwner(),allExercisesObserver);
        exercisesViewModel.getCurrentUser().observe(getViewLifecycleOwner(), currentUserObserver);
        exercisesViewModel.getTrainingSummary().observe(getViewLifecycleOwner(), trainingObserver);
        return root;
    }

    private void buildRecyclerView(View rootView) {
        RecyclerView recyclerView = rootView.findViewById(R.id.exercises_recycler_view);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        exerciseAdapter = new ExerciseAdapter();
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(exerciseAdapter);

        exerciseAdapter.setOnItemClickListener(new ExerciseAdapter.OnItemClickListener() {
/*            @Override
            public void onAddProductClick(int position) {
                if (!foodViewModel.getMeal().contains(exerciseAdapter.getProduct(position))) {
                    foodViewModel.getMeal().add(exerciseAdapter.getProduct(position));
                    foodViewModel.getMealSummary().setValue(exerciseAdapter.getMeal());
                }
            }

            @Override
            public void onDeleteProductClick(int position) {
                foodViewModel.getMeal().remove(mFoodProductAdapter.getProduct(position));
                foodViewModel.getMealSummary().setValue(foodViewModel.getMeal());
            }*/

            @Override
            public void onItemClick(int position) {
                Exercise exercise = exerciseAdapter.getExercise(position);

                @NonNull NavDirections action = ExercisesFragmentDirections.
                        actionNavigationExercisesToExerciseSummaryFragment(exercise, position, currentUser);
                navController.navigate(action);
            }
        });

    }

    private void filter(String exerciseText) {
        List<Exercise> filteredList = new ArrayList<>();

        for (Exercise exercise : exerciseAdapter.getExercisesList()) {
            if (exercise.getExerciseName().toLowerCase().contains(exerciseText.toLowerCase())) {
                filteredList.add(exercise);
            }
        }
        exerciseAdapter.filterList(filteredList);
    }


}