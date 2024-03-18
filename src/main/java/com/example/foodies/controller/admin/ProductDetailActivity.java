package com.example.foodies.controller.admin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodies.R;
import com.example.foodies.controller.UserSelectionActivity;
import com.example.foodies.controller.buyer.BuyerHomeActivity;
import com.example.foodies.database.AppDatabaseHelper;
import com.example.foodies.model.Product;

public class ProductDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetailscreen);
        displayDetails();

    }

    public void displayDetails(){
        AppDatabaseHelper databaseHelper = new AppDatabaseHelper(this);
        long productID = getIntent().getLongExtra("id",0);
        Product product = databaseHelper.getProductWithID(productID);

        TextView nameTV = findViewById(R.id.name);
        TextView priceTV = findViewById(R.id.price);
        TextView descriptionTV = findViewById(R.id.description);
        ImageView imageIV = findViewById(R.id.image);

        nameTV.setText(product.getName());
        priceTV.setText(product.getPrice());
        descriptionTV.setText(product.getDescription());
        String imagePath = product.getPhotopath();
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        if (null != bitmap) {
            imageIV.setImageBitmap(bitmap);
        }
    }

    public void buy( View view){

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Payment in progress..."); // Setting Message
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); // Progress Dialog Style Spinner
        progressDialog.show(); // Display Progress Dialog
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
                loadHomeActivity();
            }
        }).start();
    }

    public void loadHomeActivity() {
        Intent intent = new Intent(this, BuyerHomeActivity.class);
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
}
