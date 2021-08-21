package com.kucharzyk.diabetictoolboxjetpack.room_database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Product.class, Meal.class, MealProductCrossRef.class, DiaryEntry.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ProductDao productDao();
    public abstract MealDao mealDao();
    public abstract MealProductCrossRefDao mealProductCrossRefDao();
    public abstract MealWithProductsDao mealWithProductsDao();
    public abstract DiaryEntryDao diaryEntryDao();
    public abstract DiaryEntryWithMealsAndProductsDao diaryEntryWithMealsAndProductsDao();

    public static final String TAG = "AppDatabase";
    private static AppDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    Log.d(TAG, "getDatabaseStart: " + INSTANCE);
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                    Log.d(TAG, "getDatabaseEnd: " + INSTANCE);
                    //clearDatabase(INSTANCE);
                } else {
                    Log.d(TAG, "getDatabase: " + INSTANCE);
                    INSTANCE.clearAllTables();
                }
            }
        }
        return INSTANCE;
    }

    private static void clearDatabase(AppDatabase database){
        databaseWriteExecutor.execute(() -> {
            database.clearAllTables();
        });
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            Log.d(TAG, "onCreateCallbackStart: ");
            super.onCreate(db);
            Log.d(TAG, "onCreate: Creating databases");
            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                UserDao userDao = INSTANCE.userDao();
                ProductDao productDao = INSTANCE.productDao();

                //userDao.deleteAll();

                User user = new User("Konrad");
                userDao.insert(user);
                Log.d(TAG, "onCreate: before new product");
                productDao.insert(new Product("Agrest", 11.8, 0.9, 1.2, 100.0));
                productDao.insert(new Product("Banan", 17.0, 0.3, 0.9, 100.0));
                productDao.insert(new Product("Chleb orkiszowy", 50.0, 1.2, 8.0, 100.0));
                productDao.insert(new Product("Delicje", 71.5, 8.4, 3.5, 100.0));
                productDao.insert(new Product("Fasola biała gotowana", 25.7, 0.7, 8.9, 100.0));
                productDao.insert(new Product("Herbatniki", 76.8, 11.0, 8.2, 100.0));
                productDao.insert(new Product("Jabłko", 12.1, 0.7, 0.6, 100.0));
                productDao.insert(new Product("Kiełbasa biała", 3.0, 28.0, 14.3, 100.0));
                productDao.insert(new Product("Leczo", 5.5, 4.4, 1.2, 100.0));
                productDao.insert(new Product("Marchew surowa", 8.7, 0.2, 1.0, 100.0));

            });
        }
    };

}
