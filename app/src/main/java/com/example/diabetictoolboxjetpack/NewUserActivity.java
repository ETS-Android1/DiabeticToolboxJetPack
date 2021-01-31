package com.example.diabetictoolboxjetpack;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewUserActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.userlistsql.REPLY";

    private EditText editUserView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        editUserView =findViewById(R.id.edit_user);

        final Button button = findViewById(R.id.button_save);
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
        });
    }
}