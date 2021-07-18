package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.ProductRepository;

import java.util.List;

public class DiaryEntryViewModel extends AndroidViewModel {
    private ProductRepository mProductRepository;
    private LiveData<List<Product>> mAllProducts;
//    private MutableLiveData<String> mText;

    public DiaryEntryViewModel(@NonNull Application application) {
        super(application);
//        mText = new MutableLiveData<>();
//        mText.setValue("This is calculator fragment");

        mProductRepository = new ProductRepository(application);
        mAllProducts = mProductRepository.getAllProducts();
    }

    public void insert(Product product){
        mProductRepository.insert(product);
    }

    public LiveData<List<Product>> getAllProducts() {
        return mAllProducts;
    }

//    public LiveData<String> getText() {
//        return mText;
//    }

}
