package com.kucharzyk.diabetictoolboxjetpack.ui_and_data.diary;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.ProductRepository;

import java.util.ArrayList;
import java.util.List;

public class DiaryEntryViewModel extends AndroidViewModel {
    public static final String TAG = "DiaryEntryViewModel";

    private final ProductRepository mProductRepository;
    private final LiveData<List<Product>> mAllProducts;

    public DiaryEntryViewModel(@NonNull Application application) {
        super(application);

        mProductRepository = new ProductRepository(application);
        mAllProducts = mProductRepository.getAllProducts();
    }

    public void insert(Product product){
        mProductRepository.insert(product);
    }
    public void delete(Product product) { mProductRepository.delete(product); }
    public void deleteAllProducts() { mProductRepository.deleteAllProducts(); }
    public LiveData<List<Product>> getAllProducts() {
        return mAllProducts;
    }

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
