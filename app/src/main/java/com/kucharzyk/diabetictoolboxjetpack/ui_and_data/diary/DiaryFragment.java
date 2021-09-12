package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithMealsAndProducts;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithTrainingsAndExercises;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealProductCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.room_database.TrainingExerciseCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;

import java.util.ArrayList;
import java.util.List;

public class DiaryFragment extends Fragment {
    public static final String TAG = "DiaryFragment";

    private DiaryEntryViewModel diaryEntryViewModel;
    private DiaryEntryAdapter mAdapter;
    private List<DiaryMealEntrySummary> diaryMealEntrySummaries = new ArrayList<>();
    private List<DiaryTrainingEntrySummary> diaryTrainingEntrySummaries = new ArrayList<>();
    private List<MealProductCrossRef> mealProductCrossRefList = new ArrayList<>();
    private List<TrainingExerciseCrossRef> trainingExerciseCrossRefList = new ArrayList<>();
    private List<User> appUsers = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        diaryEntryViewModel =
                new ViewModelProvider(this).get(DiaryEntryViewModel.class);
        View root = inflater.inflate(R.layout.diary_fragment, container, false);

        buildRecyclerView(root);

        final Observer<List<DiaryEntryWithMealsAndProducts>> diaryMealEntriesObserver = new Observer<List<DiaryEntryWithMealsAndProducts>>() {
            @Override
            public void onChanged(List<DiaryEntryWithMealsAndProducts> diaryEntryWithMealsAndProducts) {
                diaryEntryWithMealsAndProducts = diaryEntryViewModel.getAllDiaryMealEntries().getValue();

                assert diaryEntryWithMealsAndProducts != null;
                for (DiaryEntryWithMealsAndProducts diaryEntry:diaryEntryWithMealsAndProducts
                     ) {
                        diaryMealEntrySummaries.add(new DiaryMealEntrySummary(diaryEntry.meals, mealProductCrossRefList, diaryEntry.diaryEntry.getDiaryEntryDate()));
                }
                mAdapter.setDiaryMealEntries(diaryMealEntrySummaries);
            }
        };

        final Observer<List<DiaryEntryWithTrainingsAndExercises>> diaryTrainingEntriesObserver = new Observer<List<DiaryEntryWithTrainingsAndExercises>>() {
            @Override
            public void onChanged(List<DiaryEntryWithTrainingsAndExercises> diaryEntryWithTrainingsAndExercises) {
                diaryEntryWithTrainingsAndExercises = diaryEntryViewModel.getAllDiaryTrainingEntries().getValue();

                assert diaryEntryWithTrainingsAndExercises != null;
                for (DiaryEntryWithTrainingsAndExercises diaryEntry:diaryEntryWithTrainingsAndExercises
                ) {
                    diaryTrainingEntrySummaries.add(new DiaryTrainingEntrySummary(diaryEntry.trainings, trainingExerciseCrossRefList,
                            diaryEntry.diaryEntry.getDiaryEntryDate(), appUsers.get(0)));
                }
                mAdapter.setDiaryTrainingEntries(diaryTrainingEntrySummaries);
            }
        };

        final Observer<List<MealProductCrossRef>> mealsRefObserver = new Observer<List<MealProductCrossRef>>() {
            @Override
            public void onChanged(List<MealProductCrossRef> refs) {
                mealProductCrossRefList = refs;
            }
        };

        final Observer<List<TrainingExerciseCrossRef>> trainingsRefObserver = new Observer<List<TrainingExerciseCrossRef>>() {
            @Override
            public void onChanged(List<TrainingExerciseCrossRef> refs) {
                trainingExerciseCrossRefList = refs;
            }
        };

        final Observer<List<User>> appUsersObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                appUsers = users;
            }
        };

        diaryEntryViewModel.getAllMealCrossRefs().observe(getViewLifecycleOwner(), mealsRefObserver);
        diaryEntryViewModel.getAllTrainingCrossRefs().observe(getViewLifecycleOwner(), trainingsRefObserver);
        diaryEntryViewModel.getAllApplicationUsers().observe(getViewLifecycleOwner(), appUsersObserver);
        diaryEntryViewModel.getAllDiaryMealEntries().observe(getViewLifecycleOwner(), diaryMealEntriesObserver);
        diaryEntryViewModel.getAllDiaryTrainingEntries().observe(getViewLifecycleOwner(), diaryTrainingEntriesObserver);
        return root;
    }

    private void buildRecyclerView(View rootView){
        RecyclerView mDiaryRecyclerView = rootView.findViewById(R.id.diary_recycler_view);
        //mDiaryRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new DiaryEntryAdapter();

        mDiaryRecyclerView.setLayoutManager(mLayoutManager);
        mDiaryRecyclerView.setAdapter(mAdapter);
    }

}