package com.kucharzyk.diabetictoolboxjetpack.ui_and_data;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;
import com.kucharzyk.diabetictoolboxjetpack.room_database.UserDatabase;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_USER_ACTIVITY_REQUEST_CODE = 1;
    private UserViewModel userViewModel;
    public static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final UserListAdapter adapter = new UserListAdapter(new UserListAdapter.UserDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, users -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(users);
        });

        FloatingActionButton fab = findViewById(R.id.floatingActionButton);
        fab.setOnClickListener( view -> {
            Intent intent = new Intent(MainActivity.this, NewUserActivity.class);
            startActivityForResult(intent, NEW_USER_ACTIVITY_REQUEST_CODE);
        });

        UserDatabase userDatabase = Room.databaseBuilder(getApplicationContext(),
                UserDatabase.class, "user_database")
                .build();

        //userDatabase.clearDatabase(userDatabase);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == NEW_USER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            User user = new User(data.getStringExtra(NewUserActivity.EXTRA_REPLY));
            userViewModel.insert(user);
        } else {
            Toast.makeText(getApplicationContext()
            , R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }
}