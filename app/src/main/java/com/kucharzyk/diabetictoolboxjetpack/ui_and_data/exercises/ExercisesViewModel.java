package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.exercises;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntry;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Exercise;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Training;
import com.kucharzyk.diabetictoolboxjetpack.room_database.TrainingExerciseCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.DiaryEntryRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.ExerciseRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.TrainingExerciseCrossRefRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.TrainingRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.TrainingWithExercisesRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.UserRepository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ExercisesViewModel extends AndroidViewModel {
    private final UserRepository userRepository;
    private final ExerciseRepository exerciseRepository;
    private final TrainingRepository trainingRepository;
    private final TrainingWithExercisesRepository trainingWithExercisesRepository;
    private final TrainingExerciseCrossRefRepository trainingExerciseCrossRefRepository;
    private final DiaryEntryRepository diaryEntryRepository;

    private List<Exercise> listOfExercises;
    private MutableLiveData<List<Exercise>> trainingSummary;
    private final LiveData<List<Exercise>> allExercises;
    public final LiveData<List<User>> appUsers;


    public ExercisesViewModel(@NonNull @NotNull Application application) {
        super(application);

        userRepository = new UserRepository(application);
        exerciseRepository = new ExerciseRepository(application);
        trainingRepository = new TrainingRepository(application);
        trainingWithExercisesRepository = new TrainingWithExercisesRepository(application);
        trainingExerciseCrossRefRepository = new TrainingExerciseCrossRefRepository(application);
        diaryEntryRepository = new DiaryEntryRepository(application);

        appUsers = userRepository.getAppUsers();
        allExercises = exerciseRepository.getAllExercises();
    }

    public LiveData<List<User>> getAppUsers() {return appUsers; }

    public long insertTraining(Training training) {return trainingRepository.insert(training); }

    public void insertTrainingExerciseCrossRef(TrainingExerciseCrossRef trainingExerciseCrossRef) {
        trainingExerciseCrossRefRepository.insert(trainingExerciseCrossRef);
    }

    public void insertDiaryEntry(DiaryEntry diaryEntry) { diaryEntryRepository.insert(diaryEntry); }

    public LiveData<List<Exercise>> getAllExercises() {
        return allExercises;
    }

    public MutableLiveData<List<Exercise>> getTrainingSummary(){
        if (trainingSummary == null) {
            trainingSummary = new MutableLiveData<>();
        }
        return trainingSummary;
    }

    public List<Exercise> getListOfExercises() {
        if (listOfExercises == null) {
            listOfExercises = new ArrayList<>();
        }
        return listOfExercises;
    }
}