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
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealWithProducts;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DiaryFragment extends Fragment {
    public static final String TAG = "DiaryFragment";

    private DiaryEntryViewModel diaryEntryViewModel;
    private DiaryEntryAdapter mAdapter;
    private final List<Product> unpackedProducts = new ArrayList<>();
    private List<String> distinctDates = new ArrayList<>();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        diaryEntryViewModel =
                new ViewModelProvider(this).get(DiaryEntryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_diary, container, false);

        buildRecyclerView(root);

/*        final Observer<List<Product>> diaryEntriesObserver = new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> diaryEntries) {
                mAdapter.setDiaryEntries(diaryEntries);
            }
        };

        diaryEntryViewModel.getAllProducts().observe(getViewLifecycleOwner(), diaryEntriesObserver);*/
        //mealWithProducts = diaryEntryViewModel.getMealWithProducts();
/*        final Observer<List<String>> distinctDatesObserver = new Observer<List<String>>() {
            @Override
            public void onChanged(List<String> strings) {
                distinctDates = diaryEntryViewModel.getAllMealDates().getValue();
                assert distinctDates != null;
                mAdapter.setDiarySize(distinctDates.size());
            }
        };*/

        final Observer<List<MealWithProducts>> diaryEntriesObserverTest = new Observer<List<MealWithProducts>>() {
            @Override
            public void onChanged(List<MealWithProducts> mealWithProducts) {
                mealWithProducts = diaryEntryViewModel.getMealWithProductsFromDate().getValue();
                if(mealWithProducts != null) {
                    for (MealWithProducts meal: mealWithProducts
                         ) {
                        unpackedProducts.addAll(meal.getProducts());
                    }
                    mAdapter.setDiaryEntries(Objects.requireNonNull(unpackedProducts));
                }
            }
        };

        diaryEntryViewModel.getMealWithProductsFromDate().observe(getViewLifecycleOwner(), diaryEntriesObserverTest);
        //diaryEntryViewModel.getAllMealDates().observe(getViewLifecycleOwner(), distinctDatesObserver);

        return root;
    }

    private void buildRecyclerView(View rootView){
        RecyclerView mDiaryRecyclerView = rootView.findViewById(R.id.diary_recycler_view);
        mDiaryRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        mAdapter = new DiaryEntryAdapter();

        mDiaryRecyclerView.setLayoutManager(mLayoutManager);
        mDiaryRecyclerView.setAdapter(mAdapter);
    }

}