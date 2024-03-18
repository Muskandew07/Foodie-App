package com.example.foodies.controller.buyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodies.R;
import com.example.foodies.database.AppDatabaseHelper;
import com.example.foodies.model.Buyer;




public class BuyerLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyerloginscreen);
    }

    public void registration(View view) {

        Intent intent = new Intent(this, BuyerRegistrationActivity.class);
        startActivity(intent);


    }

    public void Login(View view) {
        EditText usernameET = findViewById(R.id.buyer_name);
        EditText passwordET = findViewById(R.id.buyer_password);

        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();

        if (username.length() == 0) {
            usernameET.setError("Please Enter admin Name");

        } else if (password.length() == 0) {
            passwordET.setError("Please Enter Password");
        } else {
            AppDatabaseHelper databasehelper = new AppDatabaseHelper(this);
            Buyer buyer = databasehelper.getBuyerWithUsernameAndPassword(username, password);
            if (buyer == null) {
                Toast.makeText(getApplicationContext(), "Please Register Before Login", Toast.LENGTH_LONG).show();

            } else {
                Intent intent = new Intent(this, BuyerHomeActivity.class);
                intent.putExtra("id",buyer.getId());

                startActivity(intent);
            }
        }
    }
}