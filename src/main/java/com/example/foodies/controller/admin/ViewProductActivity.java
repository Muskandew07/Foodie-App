package com.example.foodies.controller.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.foodies.R;
import com.example.foodies.adapter.ProductListItemAdapter;
import com.example.foodies.controller.UserSelectionActivity;
import com.example.foodies.database.AppDatabaseHelper;
import com.example.foodies.model.Product;

import java.util.ArrayList;


public class ViewProductActivity extends AppCompatActivity {

    ArrayList<Product> productList = null;
    ProductListItemAdapter customAdapter = null;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewproductscreen);
        populateListView();
    }
    public void populateListView() {

        AppDatabaseHelper databaseHelper = new AppDatabaseHelper(this);

        productList = (ArrayList<Product>) databaseHelper.getProductList();
        if (productList.size() > 0) {
            customAdapter = new ProductListItemAdapter(this, R.layout.listviewitems);
            customAdapter.setProductList(productList);
            listView = findViewById(R.id.listview);
            listView.setAdapter(customAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Product product = productList.get(i);
                    Intent intent = new Intent(getApplicationContext(), ProductDetailActivity.class);
                    intent.putExtra("id", product.getId());
                    startActivity(intent);
                }
            });


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
