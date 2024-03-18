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


public class BuyerRegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyerregistrationscreen);
    }


    public void registration(View view)
    {
        EditText nameET = findViewById(R.id.name);
        EditText mailET = findViewById(R.id.mail);
        EditText mobnoET = findViewById(R.id.mobno);
        EditText usernameET = findViewById(R.id.username);
        EditText passwordET = findViewById(R.id.password);

        String name = nameET.getText().toString();
        String mail = mailET.getText().toString();
        String mobno = mobnoET.getText().toString();
        String username = usernameET.getText().toString();
        String password = passwordET.getText().toString();

        if (name.length() == 0) {
            nameET.setError("Please Enter Name");

        }
        else if (mail.length() == 0)
        {
            mailET.setError("Please Enter Mail");
        }
        else if (mobno.length() == 0)
        {
            mobnoET.setError("Please Enter Number");
        }
        else if (mobno.length() != 10)
        {
            mobnoET.setError("Please Enter 10 Digits ");
        }
        else if (username.length() == 0)
        {
            usernameET.setError("Please Enter Username");
        }
        else if (password.length() == 0)
        {
            passwordET.setError("Please Enter Password");
        }
        else
        {
            Buyer buyer=new Buyer();
            buyer.setName(name);
            buyer.setMail(mail);
            buyer.setNumber(mobno);
            buyer.setUsername(username);
            buyer.setPassword(password);

            AppDatabaseHelper databaseHelper=new AppDatabaseHelper(this);
            databaseHelper.addBuyer(buyer);

           Toast.makeText(getApplicationContext(),"Registration is Successful.Please login to continue",Toast.LENGTH_LONG).show();

           Intent intent=new Intent(this,BuyerLoginActivity.class);
           startActivity(intent);

        }
    }
}