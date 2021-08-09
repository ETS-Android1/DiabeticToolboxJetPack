package com.kucharzyk.diabetictoolboxjetpack.room_database;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class, Product.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ProductDao productDao();

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
                //UserDao userDao = INSTANCE.userDao();
                ProductDao productDao = INSTANCE.productDao();

                //userDao.deleteAll();

                //User user = new User("Konrad");
                //userDao.insert(user);
                Log.d(TAG, "onCreate: before new product");
                Product product = new Product("TestProduct", 10.0, 5.0, 2.0, 100.0);
                productDao.insert(product);
                Log.d(TAG, "onCreate: product inserted: " + product);

            });
        }
    };

}
