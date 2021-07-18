package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.ProductRepository;

import java.util.List;

public class DiaryEntryViewModel extends ViewModel {
    private ProductRepository mProductRepository;
    private LiveData<List<Product>> mAllProducts;

    public DiaryEntryViewModel() {

/*        mProductRepository = new ProductRepository(application);
        mAllProducts = mProductRepository.getAllProducts();*/
    }

/*    public LiveData<List<Product>> getAllProducts() {
        return mAllProducts;
    }*/

}
