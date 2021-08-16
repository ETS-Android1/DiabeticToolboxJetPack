package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.Meal;
import com.kucharzyk.diabetictoolboxjetpack.room_database.MealWithProducts;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.MealRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.MealWithProductsRepository;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class DiaryEntryViewModel extends AndroidViewModel {
    public static final String TAG = "DiaryEntryViewModel";

    private final ProductRepository productRepository;
    private final MealRepository mealRepository;
    private final MealWithProductsRepository mealWithProductsRepository;
    private final LiveData<List<Product>> allProducts;
    private final LiveData<List<Meal>> allMeals;
    private final LiveData<MealWithProducts> mealWithProducts;
    private final LiveData<List<MealWithProducts>> mealWithProductsFromDate;
    private final LiveData<List<String>> allMealDates;


    public DiaryEntryViewModel(@NonNull Application application) {
        super(application);

        productRepository = new ProductRepository(application);
        mealRepository = new MealRepository(application);
        mealWithProductsRepository = new MealWithProductsRepository(application);

        allProducts = productRepository.getAllProducts();
        allMeals = mealRepository.getAllMeals();
        allMealDates = mealRepository.getAllMealDates();
        mealWithProducts = mealWithProductsRepository.getMealWithProducts(2);
        mealWithProductsFromDate = mealWithProductsRepository.getMealWithProductsFromDate("16-08-2021");
    }

    public void insert(Product product){
        productRepository.insert(product);
    }
    public void delete(Product product) { productRepository.delete(product); }
    public void deleteAllProducts() { productRepository.deleteAllProducts(); }
    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }
    public LiveData<List<Meal>> getAllMeals() {
        return allMeals;
    }
    public LiveData<List<String>> getAllMealDates() {return allMealDates; }
    public LiveData<MealWithProducts> getMealWithProducts() {
        return mealWithProducts;
    }
    public LiveData<List<MealWithProducts>> getMealWithProductsFromDate() { return mealWithProductsFromDate; }


    public void deleteLastProduct(Product lastProduct) {
        List<Product> currentProductList = getAllProducts().getValue();
        if (currentProductList == null){
            currentProductList = new ArrayList<>();
        }
        int id = 0;
        for (Product product : currentProductList){
            if (product.getPid() > id) { id = product.getPid(); }
            Log.d(TAG, "deleteLastProduct: " + product);
        }
        Log.d(TAG, "deleteLastProduct: " + id);
        lastProduct.setPid(id);
        delete(lastProduct);
    }


}
