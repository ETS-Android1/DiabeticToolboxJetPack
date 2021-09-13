package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import android.os.Bundle;
import android.util.Log;
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
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithGlycemia;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithMealsAndProducts;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithTrainingsAndExercises;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealProductCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.room_database.TrainingExerciseCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.home.HomeFragment;

import java.util.ArrayList;
import java.util.List;

public class DiaryFragment extends Fragment {
    public static final String TAG = "DiaryFragment";

    private DiaryEntryViewModel diaryEntryViewModel;
    private DiaryEntryAdapter mAdapter;
    private final List<DiaryMealEntrySummary> diaryMealEntrySummaries = new ArrayList<>();
    private final List<DiaryTrainingEntrySummary> diaryTrainingEntrySummaries = new ArrayList<>();
    private final List<DiaryMeasurementEntrySummary> diaryMeasurementEntrySummaries = new ArrayList<>();
    private List<MealProductCrossRef> mealProductCrossRefList = new ArrayList<>();
    private List<TrainingExerciseCrossRef> trainingExerciseCrossRefList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        diaryEntryViewModel =
                new ViewModelProvider(this).get(DiaryEntryViewModel.class);
        View root = inflater.inflate(R.layout.diary_fragment, container, false);
        buildRecyclerView(root);

        final Observer<List<DiaryEntryWithTrainingsAndExercises>> diaryTrainingEntriesObserver = new Observer<List<DiaryEntryWithTrainingsAndExercises>>() {
            @Override
            public void onChanged(List<DiaryEntryWithTrainingsAndExercises> diaryEntryWithTrainingsAndExercises) {
                diaryEntryWithTrainingsAndExercises = diaryEntryViewModel.getAllDiaryTrainingEntries().getValue();

                assert diaryEntryWithTrainingsAndExercises != null;
                for (DiaryEntryWithTrainingsAndExercises diaryEntry:diaryEntryWithTrainingsAndExercises
                ) {
                    diaryTrainingEntrySummaries.
                            add(new DiaryTrainingEntrySummary(diaryEntry.trainings,
                                    trainingExerciseCrossRefList, diaryEntry.diaryEntry.getDiaryEntryDate(), HomeFragment.currentUser));
                }
                mAdapter.setDiaryTrainingEntries(diaryTrainingEntrySummaries);
            }
        };

        final Observer<List<DiaryEntryWithMealsAndProducts>> diaryMealEntriesObserver = new Observer<List<DiaryEntryWithMealsAndProducts>>() {
            @Override
            public void onChanged(List<DiaryEntryWithMealsAndProducts> diaryEntryWithMealsAndProducts) {
                diaryEntryWithMealsAndProducts = diaryEntryViewModel.getAllDiaryMealEntries().getValue();

                assert diaryEntryWithMealsAndProducts != null;
                for (DiaryEntryWithMealsAndProducts diaryEntry:diaryEntryWithMealsAndProducts
                     ) {
                        diaryMealEntrySummaries.
                                add(new DiaryMealEntrySummary(diaryEntry.meals,
                                        mealProductCrossRefList, diaryEntry.diaryEntry.getDiaryEntryDate()));
                    Log.d(TAG, "onChanged: diaryMealEntriesObserver carbsValue = " + diaryEntry.meals);
                }
                mAdapter.setDiaryMealEntries(diaryMealEntrySummaries);
            }
        };

        final Observer<List<DiaryEntryWithGlycemia>> diaryMeasurementEntriesObserver = new Observer<List<DiaryEntryWithGlycemia>>() {
            @Override
            public void onChanged(List<DiaryEntryWithGlycemia> diaryEntryWithMeasurements) {
                diaryEntryWithMeasurements = diaryEntryViewModel.getAllDiaryMeasurementEntries().getValue();

                assert diaryEntryWithMeasurements != null;
                for (DiaryEntryWithGlycemia diaryEntry:diaryEntryWithMeasurements
                ) {
                    diaryMeasurementEntrySummaries.
                            add(new DiaryMeasurementEntrySummary(diaryEntry.getGlycemiaMeasurements(),
                                    diaryEntry.getDiaryEntry().getDiaryEntryDate()));
                    Log.d(TAG, "onChanged: diaryMeasurementEntriesObserver measValue = " + diaryEntry.getGlycemiaMeasurements()
                    + " size: " + diaryEntry.getGlycemiaMeasurements().size() + " value: " + diaryEntry.getGlycemiaMeasurements().get(0).getMeasurementValue());
                }
                mAdapter.setDiaryMeasurementEntries(diaryMeasurementEntrySummaries);
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

        diaryEntryViewModel.getAllTrainingCrossRefs().observe(getViewLifecycleOwner(), trainingsRefObserver);
        diaryEntryViewModel.getAllDiaryTrainingEntries().observe(getViewLifecycleOwner(), diaryTrainingEntriesObserver);
        diaryEntryViewModel.getAllMealCrossRefs().observe(getViewLifecycleOwner(), mealsRefObserver);
        diaryEntryViewModel.getAllDiaryMealEntries().observe(getViewLifecycleOwner(), diaryMealEntriesObserver);
        diaryEntryViewModel.getAllDiaryMeasurementEntries().observe(getViewLifecycleOwner(), diaryMeasurementEntriesObserver);

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