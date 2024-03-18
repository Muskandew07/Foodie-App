package com.example.foodies.controller.buyer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.foodies.R;
import com.example.foodies.database.AppDatabaseHelper;
import com.example.foodies.model.Buyer;
import com.example.foodies.model.Product;

public class ViewProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewprofilescreen);
        displayProfileDetails();

    }

    public void displayProfileDetails() {
        AppDatabaseHelper databaseHelper = new AppDatabaseHelper(this);
        long buyerID = getIntent().getLongExtra("id",0);
        Buyer buyer = databaseHelper.getBuyerWithID(buyerID);

        TextView nameTV = findViewById(R.id.name);
        TextView mailTV = findViewById(R.id.mail);
        TextView mobilenoTV = findViewById(R.id.mobno);


        nameTV.setText(buyer.getName());
        mailTV.setText(buyer.getMail());
        mobilenoTV.setText(buyer.getNumber());

    }
    }
