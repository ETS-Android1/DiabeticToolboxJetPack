package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.food;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntry;
import com.kucharzyk.diabetictoolboxjetpack.room_database.DiaryEntryWithMealsAndProducts;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Meal;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealProductCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.DiaryEntryRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.DiaryEntryWithMealsAndProductsRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.MealProductCrossRefRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.MealRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.ProductRepository;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FoodViewModel extends AndroidViewModel {
    private List<Product> meal;
    private MutableLiveData<List<Product>> mealSummary;
    private final LiveData<List<Product>> allProducts;
    private final LiveData<List<Meal>> allMeals;
    private final ProductRepository productRepository;
    private final MealRepository mealRepository;
    private final MealProductCrossRefRepository mealProductCrossRefRepository;
    private final DiaryEntryRepository diaryEntryRepository;
    private final DiaryEntryWithMealsAndProductsRepository diaryEntryWithMealsAndProductsRepository;

    public FoodViewModel(@NonNull @NotNull Application application) {
        super(application);

        productRepository = new ProductRepository(application);
        mealRepository = new MealRepository(application);
        mealProductCrossRefRepository = new MealProductCrossRefRepository(application);
        diaryEntryRepository = new DiaryEntryRepository(application);
        diaryEntryWithMealsAndProductsRepository = new DiaryEntryWithMealsAndProductsRepository(application);
        allProducts = productRepository.getAllProducts();
        allMeals = mealRepository.getAllMeals();
    }

    public void insertProduct(Product product){
        productRepository.insert(product);
    }
    public void updateProduct(Product product) { productRepository.update(product); }
    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }

    public long insertMeal(Meal meal) {return mealRepository.insert(meal); }
    public LiveData<List<Meal>> getAllMeals() {return allMeals; }

    public void insertMealProductCrossRef(MealProductCrossRef mealProductCrossRef) {
        mealProductCrossRefRepository.insert(mealProductCrossRef);
    }

    public LiveData<DiaryEntryWithMealsAndProducts> getDiaryEntryFromDate(LocalDate date) {
        return diaryEntryWithMealsAndProductsRepository.getDiaryEntryFromDate(date);
    }

    public void insertDiaryEntry(DiaryEntry diaryEntry) { diaryEntryRepository.insert(diaryEntry); }
    public void updateDiaryEntry(DiaryEntry diaryEntry) { diaryEntryRepository.update(diaryEntry); }

    public MutableLiveData<List<Product>> getMealSummary(){
        if (mealSummary == null) {
            mealSummary = new MutableLiveData<>();
        }
        return mealSummary;
    }

    public List<Product> getMeal() {
        if (meal == null) {
            meal = new ArrayList<>();
        }
        return meal;
    }



}
