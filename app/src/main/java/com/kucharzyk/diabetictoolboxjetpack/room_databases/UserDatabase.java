package com.kucharzyk.diabetictoolboxjetpack.room_databases;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public static final String TAG = "UserDatabase";
    private static UserDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static UserDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (UserDatabase.class) {
                if (INSTANCE == null) {
                    Log.d(TAG, "getDatabaseStart: " + INSTANCE);
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            UserDatabase.class, "user_database")
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

    private static void clearDatabase(UserDatabase database){
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
                UserDao dao = INSTANCE.userDao();
                dao.deleteAll();

                User user = new User("Konrad");
                dao.insert(user);
                user = new User("Miron");
                dao.insert(user);
            });
        }
    };

}
