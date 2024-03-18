package com.example.foodies.controller.buyer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.foodies.R;
import com.example.foodies.adapter.ProductListItemAdapter;
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
}
