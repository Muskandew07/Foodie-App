package com.example.foodies.controller.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.foodies.R;


public class AdminLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminloginscreen);
    }

    public void admin_login(View view) {
        EditText usernameET = findViewById(R.id.admin_username);
        EditText passwordET = findViewById(R.id.admin_password);

        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();

        if (username.length() == 0) {
            usernameET.setError("Please Enter admin Name");

        }
        else if (password.length() == 0)
        {
            passwordET.setError("Please Enter Password");
        }
        else if (username.equals("admin") && password.equals("admin"))
        {
            Intent intent = new Intent(this, AdminHomeActivity.class);
            startActivity(intent);

        }
    }
}