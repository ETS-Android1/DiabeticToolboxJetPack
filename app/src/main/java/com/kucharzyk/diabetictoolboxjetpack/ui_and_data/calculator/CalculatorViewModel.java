package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

import android.app.Application;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Meal;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealProductCrossRef;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.MealProductCrossRefRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.MealRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.ProductRepository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CalculatorViewModel extends AndroidViewModel {
    private List<Product> mMeal;
    private MutableLiveData<List<Product>> mMealSummary;
    private final LiveData<List<Product>> mAllProducts;
    private final LiveData<List<Meal>> allMeals;
    private final ProductRepository mProductRepository;
    private final MealRepository mealRepository;
    private final MealProductCrossRefRepository mealProductCrossRefRepository;

    public CalculatorViewModel(@NonNull @NotNull Application application) {
        super(application);

        mProductRepository = new ProductRepository(application);
        mealRepository = new MealRepository(application);
        mealProductCrossRefRepository = new MealProductCrossRefRepository(application);
        mAllProducts = mProductRepository.getAllProducts();
        allMeals = mealRepository.getAllMeals();


    }

    public void insertProduct(Product product){
        mProductRepository.insert(product);
    }
    public void updateProduct(Product product) { mProductRepository.update(product); }
    public LiveData<List<Product>> getAllProducts() {
        return mAllProducts;
    }

    public long insertMeal(Meal meal) {return mealRepository.insert(meal); }
    public LiveData<List<Meal>> getAllMeals() {return allMeals; }

    public void insertMealProductCrossRef(MealProductCrossRef mealProductCrossRef) {
        mealProductCrossRefRepository.insert(mealProductCrossRef); }

    public MutableLiveData<List<Product>> getMealSummary(){
        if (mMealSummary == null) {
            mMealSummary = new MutableLiveData<>();
        }
        return mMealSummary;
    }

    public List<Product> getMeal() {
        if (mMeal == null) {
            mMeal = new ArrayList<>();
        }
        return mMeal;
    }



}
