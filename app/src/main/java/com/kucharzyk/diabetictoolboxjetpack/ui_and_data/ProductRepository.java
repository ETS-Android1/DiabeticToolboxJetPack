package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;
import com.kucharzyk.diabetictoolboxjetpack.room_database.ProductDao;

import java.util.List;

public class ProductRepository {

    private ProductDao mProductDao;
    private LiveData<List<Product>> mAllProducts;

    public ProductRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        mProductDao = db.productDao();
        mAllProducts = mProductDao.getAllProducts();
    }

    public LiveData<List<Product>> getAllProducts() {
        return mAllProducts;
    }

    void insert(Product product) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            mProductDao.insert(product);
        });
    }
}
