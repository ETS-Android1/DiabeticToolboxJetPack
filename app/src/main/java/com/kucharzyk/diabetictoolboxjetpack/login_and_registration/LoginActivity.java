package com.kucharzyk.diabetictoolboxjetpack.login_and_registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;
import com.kucharzyk.diabetictoolboxjetpack.Globals;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.MainActivity;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.user.UserViewModel;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";

    private TextInputLayout userName;
    private TextInputLayout userPassword;

    private UserViewModel userViewModel;
    private User currentUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userName = findViewById(R.id.logging_username_textInputLayout);
        userPassword = findViewById(R.id.logging_password_textInputLayout);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        final Observer<List<User>> userObserver = new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                if (!users.isEmpty()){
                    currentUser = users.get(0);
                }
            }
        };

        userViewModel.getAppUsers().observe(this,userObserver);

    }



    /** Called when the user taps the Login button */
    public void login(View view) throws NoSuchAlgorithmException {
        if(currentUser != null) {
            String currentUserName = Objects.requireNonNull(userName.getEditText()).getText().toString();
            String currentUserPassword = Objects.requireNonNull(userPassword.getEditText()).getText().toString();
            String currentUserPasswordHash = Globals.getMessageDigest("SHA-256", currentUserPassword);

            if (currentUserName.equals(currentUser.getUsername()) &&
                    currentUserPasswordHash.equals(currentUser.getPasswordHash())) {
                userPassword.setError(null);
                userPassword.getEditText().getText().clear();
                Intent myIntent = new Intent(this, MainActivity.class);
                startActivity(myIntent);
            } else {
                userPassword.setError("Wrong username or password");
            }
        }

    }

    /** Called when the user taps the Register button */
    public void register(View view){
        Intent myIntent = new Intent(this, RegistrationActivity.class);
        startActivity(myIntent);
    }


}