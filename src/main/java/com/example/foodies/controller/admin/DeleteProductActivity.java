package com.example.foodies.controller.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodies.R;
import com.example.foodies.controller.UserSelectionActivity;
import com.example.foodies.database.AppDatabaseHelper;
import com.example.foodies.model.Product;

import java.util.ArrayList;

public class DeleteProductActivity extends AppCompatActivity {


    private AppDatabaseHelper databaseHelper = new AppDatabaseHelper(this);
    private ArrayAdapter<String> adapter;
    private ArrayList<Product> productList;
    private Spinner productSP;
    private TextView productDetailsTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deleteproductscreen);


        productList = (ArrayList<Product>) databaseHelper.getProductList();

        productDetailsTV =  findViewById(R.id.productDetails); // TextView
        productSP = findViewById(R.id.spinner); // Spinner

        // Extracting product names from product list
        final String[] productNames = new String[productList.size()];
        for (int i = 0; i < productList.size(); i++)
        {
            productNames[i] = productList.get(i).getName();
        }

        productSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                Product product = productList.get(position);
                if (product !=null ) {
                    productDetailsTV.setText("Price: " + product.getPrice() + "\n" +
                            "Description: " + product.getDescription()
                    );
                }
                else
                {
                    productDetailsTV.setText("No products to delete");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });



        adapter = new ArrayAdapter<>(this,   android.R.layout.simple_spinner_dropdown_item, productNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        productSP.setAdapter(adapter);

        if ( productList == null || productList.size() == 0 )
        {
            Toast.makeText(getApplicationContext(), "No products to delete", Toast.LENGTH_LONG).show();
        }


    }

    public void delete_product(View view)
    {
        if ( productList == null || productList.size() == 0 )
        {
            Toast.makeText(getApplicationContext(), "No products to delete", Toast.LENGTH_LONG).show();
        }
        else {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);

            // Setting Dialog Title
            alertDialog.setTitle("Delete Product...");

            // Setting Dialog Message
            alertDialog.setMessage("Are you sure you want to delete this product?");

            // Setting Icon to Dialog
            alertDialog.setIcon(R.drawable.delete);
            alertDialog.setCancelable(false);
            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int which) {
                            // Write your code here to execute after dialog

                            databaseHelper.deleteProduct(productList.get(productSP.getSelectedItemPosition()));
                            Toast.makeText(getApplicationContext(), "Product deleted successfully", Toast.LENGTH_LONG).show();
                            clearUI();

                        }
                    });
            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,	int which) {
                            // Write your code here to execute after dialog
                            Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_LONG).show();
                            dialog.cancel();
                        }
                    });

            // Showing Alert Message
            alertDialog.show();

        }

    }

    public void  clearUI()
    {
        productList.clear();
        productList = (ArrayList<Product>) databaseHelper.getProductList();

        final String[] userNames = new String[productList.size()];
        for (int i = 0; i < productList.size(); i++)
        {
            userNames[i] = productList.get(i).getName();
        }

        adapter = new ArrayAdapter<>(this,   android.R.layout.simple_spinner_dropdown_item, userNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        productSP.setAdapter(adapter);

        if(productList.size()==0)
        {
            productDetailsTV.setText("No products to delete");
        }

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



