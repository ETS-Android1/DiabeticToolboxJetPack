package com.kucharzyk.diabetictoolboxjetpack.login_and_registration;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.textfield.TextInputLayout;
import com.kucharzyk.diabetictoolboxjetpack.Globals;
import com.kucharzyk.diabetictoolboxjetpack.R;
import com.kucharzyk.diabetictoolboxjetpack.room_database.User;
import com.kucharzyk.diabetictoolboxjetpack.ui_and_data.user.UserViewModel;

import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class RegistrationActivity extends AppCompatActivity {
    private static final String TAG = "RegistrationActivity";

    public static final String EXTRA_REPLY = "com.example.android.userlistsql.REPLY";

    private TextInputLayout userName;
    private TextInputLayout userPassword;
    private TextInputLayout userWeight;

    private UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        userName = findViewById(R.id.registration_username_textInputLayout);
        userPassword = findViewById(R.id.registration_password_textInputLayout);
        userWeight = findViewById(R.id.registration_weight_textInputLayout);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);



/*        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            if (TextUtils.isEmpty(editUserView.getText())){
                setResult(RESULT_CANCELED, replyIntent);
            }else {
                String user = editUserView.getText().toString();
                replyIntent.putExtra(EXTRA_REPLY, user);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });*/
    }

    public void register(View view) throws NoSuchAlgorithmException {
        String currentUserName = Objects.requireNonNull(userName.getEditText()).getText().toString();
        String currentUserPassword = Objects.requireNonNull(userPassword.getEditText()).getText().toString();
        String currentUserPasswordHash = Globals.getMessageDigest("SHA-256", currentUserPassword);
        Double currentUserWeight = Double.parseDouble(Objects.requireNonNull(userWeight.getEditText()).getText().toString());

        User newUser = new User(currentUserName, currentUserWeight, currentUserPasswordHash);
        userViewModel.insertUser(newUser);
        finish();
    }

    public void goBack(View view) {
        Log.d(TAG, "goBack: ");
        finish();
    }
}