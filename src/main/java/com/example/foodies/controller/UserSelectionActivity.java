package com.example.foodies.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.foodies.R;
import com.example.foodies.controller.admin.AdminLoginActivity;
import com.example.foodies.controller.buyer.BuyerLoginActivity;


public class UserSelectionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userselectionscreen);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.about) {
            //Toast.makeText(getApplicationContext(),"Foodie",Toast.LENGTH_LONG).show();

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

            // Setting Dialog Title
            alertDialog.setTitle("About");

            // Setting Dialog Message
            alertDialog.setMessage("Foodie brings you Amazing Italian Flavour Food For Your Amazing Mood");

            // Setting Icon to Dialog
            alertDialog.setIcon(R.drawable.logo);
            alertDialog.setCancelable(false);
            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("Okay",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            // Write your code here to execute after dialog
                            dialog.dismiss();

                        }
                    });

            // Showing Alert Message
            alertDialog.show();


        }
        return super.onOptionsItemSelected(item);

    }
            public void Select_user(View view)
            {
                RadioGroup useTypeRG=findViewById(R.id.user_type);
                int SelectedRadioButton=useTypeRG.getCheckedRadioButtonId();

                if(SelectedRadioButton==R.id.admin)
                {
                    Intent intent=new Intent(this, AdminLoginActivity.class);
                    startActivity(intent);
                }
                else if (SelectedRadioButton == R.id.buyer)
                {
                    Intent intent=new Intent(this, BuyerLoginActivity.class);
                    startActivity(intent);
                }

            }
            }


