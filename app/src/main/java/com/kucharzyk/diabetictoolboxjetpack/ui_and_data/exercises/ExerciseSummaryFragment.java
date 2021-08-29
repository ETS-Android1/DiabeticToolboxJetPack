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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.kucharzyk.diabetictoolboxjetpack.Globals;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Exercise;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;

import java.util.List;
import java.util.Objects;

public class ExerciseSummaryFragment extends Fragment {

    private ExercisesViewModel exercisesViewModel;
    private NavController navController;
    private Double ratio;

    private Double caloriesBurned;
    private Double carbohydrateExchangersUsed;
    private Double exerciseDuration;
    private Double userWeight;

    TextView mExerciseName;
    TextView mCaloriesBurned;
    TextView mCarbohydrateExchangersUsed;
    TextInputEditText mExerciseDuration;
    FloatingActionButton mSaveExerciseButton;
    Exercise currentExercise;
    User currentUser;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        exercisesViewModel = new ViewModelProvider(requireActivity()).get(ExercisesViewModel.class);
        View child = inflater.inflate(R.layout.fragment_exercise_summary, container, false);
        navController = NavHostFragment.findNavController(this);
        mExerciseName = child.findViewById(R.id.exercise_summary_text_name);
        mCaloriesBurned = child.findViewById(R.id.exercise_summary_textView_calories_burned_value);
        mCarbohydrateExchangersUsed = child.findViewById(R.id.exercise_summary_textView_carbs_exchanger_used_value);
        mExerciseDuration = child.findViewById(R.id.exercise_summary_edit_text_input_exercise_duration);
        mSaveExerciseButton = child.findViewById(R.id.exercise_summary_button_save);
        return child;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        currentExercise = ExerciseSummaryFragmentArgs.fromBundle(getArguments()).getParcelizedExercise();
        currentUser = ExerciseSummaryFragmentArgs.fromBundle(getArguments()).getParcelizedUser();

        String exerciseName = currentExercise.getExerciseName();
        exerciseDuration = currentExercise.getExerciseDuration();
        userWeight = currentUser.getWeight();
        ratio = calculateRatio(exerciseDuration);
        getExerciseAttributes(currentExercise, exerciseDuration);
        setExerciseAttributes(exerciseName, caloriesBurned, carbohydrateExchangersUsed);

        mExerciseDuration.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (count > 0) {
                    exerciseDuration = Double.parseDouble(Objects.requireNonNull(mExerciseDuration.getText()).toString());
                } else exerciseDuration = 30.0;
                getExerciseAttributes(currentExercise, exerciseDuration);
                setExerciseAttributes(exerciseName, caloriesBurned, carbohydrateExchangersUsed);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        mSaveExerciseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Exercise newExercise = new Exercise(exerciseName,currentExercise.getMetabolicEquivalentOfTask(), exerciseDuration);
                newExercise.setExerciseId(currentExercise.getExerciseId());
                List<Exercise> exerciseList = exercisesViewModel.getListOfExercises();
                if (Globals.containsExercise(exerciseList, currentExercise.getExerciseName())) {
                    boolean isFound = false;
                    for (int i = 0; i < exerciseList.size() && !isFound; i++){
                        String exerciseNameToReplace = exerciseList.get(i).getExerciseName();
                        if (exerciseNameToReplace.equals(newExercise.getExerciseName())){
                            exerciseList.set(i, newExercise);
                            isFound = true;
                        }
                    }
                    if (!isFound){
                        try {
                            throw new Exception("Unreachable");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } else {
                    exerciseList.add(newExercise);
                }
                exercisesViewModel.getTrainingSummary().setValue(exerciseList);
                navController.navigateUp();
            }
        });
    }

    private void setExerciseAttributes(String exerciseName, Double caloriesBurned,
                                      Double carbohydrateExchangersUsed) {
        mExerciseName.setText(exerciseName);
        mCaloriesBurned.setText(Globals.REAL_FORMATTER.format((caloriesBurned)));
        mCarbohydrateExchangersUsed.setText(Globals.REAL_FORMATTER.format(carbohydrateExchangersUsed));
    }

    private void getExerciseAttributes(Exercise currentExercise, Double exerciseDuration) {
        caloriesBurned = Globals.calculateCaloriesBurned(ratio * exerciseDuration,
                currentExercise.getMetabolicEquivalentOfTask(), userWeight);
        carbohydrateExchangersUsed = caloriesBurned / 4 / 12;
    }

    private Double calculateRatio(Double exerciseDuration) {
        return (30.0 / exerciseDuration);
    }
}