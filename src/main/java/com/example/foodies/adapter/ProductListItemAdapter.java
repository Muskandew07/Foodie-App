package com.example.foodies.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodies.R;
import com.example.foodies.model.Product;

import java.util.ArrayList;
import java.util.List;


public class ProductListItemAdapter extends ArrayAdapter<Product> {
    private Activity context;
    List<Product> productList;
    LayoutInflater inflater;

    public ProductListItemAdapter(Activity context, int resourceId)
    {
        super(context, resourceId);
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    private class ViewHolder
    {
        TextView nameTV;
        ImageView iconIV;
    }

    public View getView(int position, View view, ViewGroup parent) {

        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listviewitems, null);

            holder.nameTV = view.findViewById(R.id.name);
            holder.iconIV = view.findViewById(R.id.image);
            view.setTag(holder);
        } else
        {
            holder = (ViewHolder) view.getTag();
        }

        holder.nameTV.setText(productList.get(position).getName());
        String imagePath = productList.get(position).getPhotopath();
        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        if (null != bitmap) {
            holder.iconIV.setImageBitmap(bitmap);
        }


        return view;
    }

    public List<Product> getProductList() {
        return this.productList;
    }

    public void setProductList(ArrayList<Product> productList)
    {
        this.productList = productList;
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Product getItem(int position) {
        return productList.get(position);
    }

}

