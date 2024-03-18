package com.example.foodies.controller.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.foodies.R;
import com.example.foodies.controller.UserSelectionActivity;


public class AdminHomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhomescreen);
    }

    public void add_product(View view){
        Intent intent=new Intent(this,AddProductActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu logout) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.logout, logout );
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            Toast.makeText(getApplicationContext(),"Logged out",Toast.LENGTH_LONG).show();
            Intent intent=new Intent(this, UserSelectionActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);
    }

    public void view_product(View view) {

        Intent intent = new Intent(this, ViewProductActivity.class);
        startActivity(intent);


    }

    public void delete_product(View view) {

        Intent intent = new Intent(this, DeleteProductActivity.class);
        startActivity(intent);


    }






}