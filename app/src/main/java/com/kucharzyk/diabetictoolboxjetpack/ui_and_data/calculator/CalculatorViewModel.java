package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.calculator;

import android.app.Application;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.ProductRepository;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CalculatorViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Product>> mMealSummary;
    private final LiveData<List<Product>> mAllProducts;
    private final ProductRepository mProductRepository;

    public CalculatorViewModel(@NonNull @NotNull Application application) {
        super(application);

        mProductRepository = new ProductRepository(application);
        mAllProducts = mProductRepository.getAllProducts();
    }

    public void insert(Product product){
        mProductRepository.insert(product);
    }
    public void update(Product product) { mProductRepository.update(product); }
    public LiveData<List<Product>> getAllProducts() {
        return mAllProducts;
    }

    public MutableLiveData<ArrayList<Product>> getMealSummary(){
        if (mMealSummary == null) {
            mMealSummary = new MutableLiveData<>();
        }
        return mMealSummary;
    }



}
