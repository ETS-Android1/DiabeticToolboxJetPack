package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.kucharzyk.diabetictoolboxjetpack.room_database.AppDatabase;
import com.kucharzyk.diabetictoolboxjetpack.room_database.Product;
import com.kucharzyk.diabetictoolboxjetpack.room_database.ProductDao;

import java.util.List;

public class ProductRepository {

    private static final String TAG = "ProductRepository";
    private final ProductDao productDao;
    private final LiveData<List<Product>> mAllProducts;

    public ProductRepository(Application application){
        AppDatabase db = AppDatabase.getDatabase(application);
        productDao = db.productDao();
        mAllProducts = productDao.getAllProducts();
    }

    public void insert(Product product) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            productDao.insert(product);
        });
    }

    public void update(Product product){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            productDao.update(product);
        });
    }

    public void delete(Product product){
        AppDatabase.databaseWriteExecutor.execute(() -> {
            productDao.delete(product);
        });
    }

    public void deleteAllProducts(){
        AppDatabase.databaseWriteExecutor.execute(productDao::deleteAllProducts);
    }


    public LiveData<List<Product>> getAllProducts() {
        return mAllProducts;
    }
}
