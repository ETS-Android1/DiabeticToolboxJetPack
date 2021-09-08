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
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kucharzyk.diabetictoolboxjetpack.Globals;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntry;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Exercise;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Training;
import com.kucharzyk.diabetictoolboxjetpack.room_database.TrainingExerciseCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;

import java.time.LocalDate;
import java.util.List;

public class TrainingSummaryFragment extends Fragment {

    public static final String TAG = "MealSummaryFragment";

    private ExercisesViewModel exercisesViewModel;
    private NavController navController;
    private TrainingSummaryAdapter mTrainingSummaryAdapter;
    private User currentUser;
    //private TextView mealName;

    FloatingActionButton mSaveTrainingButton, mDeleteTrainingButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        exercisesViewModel = new ViewModelProvider(requireActivity()).get(ExercisesViewModel.class);
        View child = inflater.inflate(R.layout.fragment_training_summary, container, false);
        navController = NavHostFragment.findNavController(this);
        buildRecyclerView(child);

        mSaveTrainingButton = child.findViewById(R.id.button_save_training);
        mDeleteTrainingButton = child.findViewById(R.id.button_delete_training);
        //mealName = child.findViewById(R.id.text_meal_summary);


        final Observer<List<Exercise>> trainingSummaryObserver = new Observer<List<Exercise>>() {
            @Override
            public void onChanged(List<Exercise> exerciseList) {
                mTrainingSummaryAdapter.setExercises(exerciseList);
            }
        };

        exercisesViewModel.getTrainingSummary().observe(getViewLifecycleOwner(), trainingSummaryObserver);
        return child;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        currentUser = TrainingSummaryFragmentArgs.fromBundle(getArguments()).getParcelizedUser();

        mDeleteTrainingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                exercisesViewModel.getListOfExercises().clear();
                NavDirections action = TrainingSummaryFragmentDirections.actionTrainingSummaryFragmentToNavigationExercises();
                navController.navigate(action);
            }
        });

        mSaveTrainingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Training currentTraining = new Training("Training " + Globals.SIMPLE_DATE_TIME_FORMAT.format(System.currentTimeMillis()),
                        LocalDate.now());
                long trainingId = exercisesViewModel.insertTraining(currentTraining);

                for (Exercise exercise: exercisesViewModel.getListOfExercises()
                ) {
                    TrainingExerciseCrossRef trainingExerciseCrossRef =  new TrainingExerciseCrossRef();
                    trainingExerciseCrossRef.setTrainingId((int) trainingId);
                    trainingExerciseCrossRef.setExerciseId(exercise.getExerciseId());
                    trainingExerciseCrossRef.setDuration(exercise.getExerciseDuration());
                    exercisesViewModel.insertTrainingExerciseCrossRef(trainingExerciseCrossRef);
                }

/*                if (calculatorViewModel.getDiaryEntryFromDate(currentMeal.getMealDate()).getValue() == null) {
                    calculatorViewModel.insertDiaryEntry(new DiaryEntry(currentMeal.getMealDate()));
                }*/
                exercisesViewModel.insertDiaryEntry(new DiaryEntry(currentTraining.getTrainingDate()));
                exercisesViewModel.getListOfExercises().clear();
                NavDirections action = TrainingSummaryFragmentDirections.actionTrainingSummaryFragmentToNavigationExercises();
                navController.navigate(action);
            }
        });
    }

    private void buildRecyclerView(View rootView) {
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recyclerViewTrainingSummary);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        mTrainingSummaryAdapter = new TrainingSummaryAdapter();

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mTrainingSummaryAdapter);

        mTrainingSummaryAdapter.setOnItemClickListener(new TrainingSummaryAdapter.OnItemClickListener() {
            @Override
            public void onEditExerciseClick(int position) {
                Exercise exercise = mTrainingSummaryAdapter.getExercise(position);

                NavDirections action = TrainingSummaryFragmentDirections.
                        actionTrainingSummaryFragmentToExerciseSummaryFragment(exercise, position, currentUser);
                navController.navigate(action);
            }

            @Override
            public void onDeleteExerciseClick(int position) {
                exercisesViewModel.getListOfExercises().remove(mTrainingSummaryAdapter.getExercise(position));
                exercisesViewModel.getTrainingSummary().setValue(exercisesViewModel.getListOfExercises());
                //TODO If numof(exercises) == 0 then go back to the ExercisesFragment
            }
        });
    }
}