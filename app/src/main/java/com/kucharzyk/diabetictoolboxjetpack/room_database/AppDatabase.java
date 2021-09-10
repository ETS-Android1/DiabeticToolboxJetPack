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

@Database(entities = {User.class, Product.class, Exercise.class, Glycemia.class,
        Meal.class, Training.class,
        MealProductCrossRef.class, TrainingExerciseCrossRef.class, DiaryEntry.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract ProductDao productDao();
    public abstract ExerciseDao exerciseDao();
    public abstract GlycemiaDao glycemiaDao();
    public abstract MealDao mealDao();
    public abstract TrainingDao trainingDao();
    public abstract MealProductCrossRefDao mealProductCrossRefDao();
    public abstract MealWithProductsDao mealWithProductsDao();
    public abstract TrainingExerciseCrossRefDao trainingExerciseCrossRefDao();
    public abstract TrainingWithExercisesDao trainingWithExercisesDao();
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
                ExerciseDao exerciseDao = INSTANCE.exerciseDao();

                //userDao.deleteAll();

                User user = new User("Konrad", 68.7);
                userDao.insert(user);
                productDao.insert(new Product("Agrest", 11.8, 0.9, 1.2, 60.0, 100.0));
                productDao.insert(new Product("Ananas", 7.8, 0.0, 0.2, 33.0, 100.0));
                productDao.insert(new Product("Banan", 17.0, 0.3, 0.9, 74.0, 100.0));
                productDao.insert(new Product("Bakłażan", 6.3, 0.1, 1.1, 26.0, 100.0));
                productDao.insert(new Product("Chleb orkiszowy", 50.0, 1.2, 8.0, 250.0, 100.0));
                productDao.insert(new Product("Czekolada mleczna", 54.2, 30.7, 8.0, 526.0, 100.0));
                productDao.insert(new Product("Delicje", 71.5, 8.4, 3.5, 376.0, 100.0));
                productDao.insert(new Product("Dynia", 7.7, 0.3, 1.3, 39.0, 100.0));
                productDao.insert(new Product("Fasola biała gotowana", 25.7, 0.7, 8.9, 141.0, 100.0));
                productDao.insert(new Product("Figi suszone", 68.4, 1.2, 4.0, 300.0, 100.0));
                productDao.insert(new Product("Galaretka wieprzowa", 4.3, 10.6, 14.7, 166.0, 100.0));
                productDao.insert(new Product("Groszek zielony", 17.0, 0.4, 6.7, 98.0, 100.0));
                productDao.insert(new Product("Herbatniki", 76.8, 11.0, 8.2, 439.0, 100.0));
                productDao.insert(new Product("Jabłko", 12.1, 0.7, 0.6, 57.0,100.0));
                productDao.insert(new Product("Jajecznica", 0.8, 9.7, 10.0, 131.0, 100.0));
                productDao.insert(new Product("Kiełbasa biała", 3.0, 28.0, 14.3, 323.0,  100.0));
                productDao.insert(new Product("Kotlet mielony", 21.1, 11.8, 13.0, 284.0, 100.0));
                productDao.insert(new Product("Leczo", 5.5, 4.4, 1.2, 60.0,  100.0));
                productDao.insert(new Product("Lody waniliowe", 13.6, 6.5, 1.8, 120.0, 100.0));
                productDao.insert(new Product("Marchew surowa", 8.7, 0.2, 1.0, 40.0,  100.0));
                productDao.insert(new Product("Morele", 11.9, 0.2, 0.7, 52.0, 100.0));

                exerciseDao.insert(new Exercise("Chodzenie 5 km/h", 2.0, 30.0));
                exerciseDao.insert(new Exercise("Bieganie 8 km/h", 6.0, 30.0));
                exerciseDao.insert(new Exercise("Bieganie 10 km/h", 10.0, 30.0));
                exerciseDao.insert(new Exercise("Bieganie 12 km/h", 11.5, 30.0));
                exerciseDao.insert(new Exercise("Jazda na rowerze (16-19 km/h)", 6.8, 30.0));
                exerciseDao.insert(new Exercise("Jazda na rowerze (19-22 km/h)", 8.0, 30.0));

            });
        }
    };

}
