package com.example.foodies.controller.buyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.foodies.R;


public class BuyerHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buyerhomescreen);
    }
    public void viewproduct(View view) {

        Intent intent = new Intent(this, ViewProductActivity.class);
        startActivity(intent);


    }

    public void viewprofile(View view) {

        Intent intent = new Intent(this, ViewProfileActivity.class);
        long buyerID = getIntent().getLongExtra("id",0);
        intent.putExtra("id",buyerID);
        startActivity(intent);

        }

    }

