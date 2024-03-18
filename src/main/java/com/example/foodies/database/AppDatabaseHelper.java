package com.example.foodies.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.foodies.model.Buyer;
import com.example.foodies.model.Product;

import java.util.ArrayList;
import java.util.List;

public class AppDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Foodie";
    public static final int DATABASE_VERSION = 1;
    public static final String BUYER_TABLE_NAME = "Buyers";
    public static final String BUYER_ID_COLUMN = "_id";
    public static final String BUYER_NAME_COLUMN = "Name";
    public static final String BUYER_MAIL_COLUMN = "Mail";
    public static final String BUYER_MOBILE_COLUMN = "Mobile";
    public static final String BUYER_USERNAME_COLUMN = "Username";
    public static final String BUYER_PASSWORD_COLUMN = "Password";

    public static final String PRODUCT_TABLE_NAME = "Product";
    public static final String PRODUCT_ID_COLUMN = "_id";
    public static final String PRODUCT_NAME_COLUMN = "Name";
    public static final String PRODUCT_PRICE_COLUMN = "Price";
    public static final String PRODUCT_DESCRIPTION_COLUMN = "Description";
    public static final String PRODUCT_PHOTO_COLUMN = "Photopath";


    public AppDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_BUYER_TABLE = "CREATE TABLE " + BUYER_TABLE_NAME + "(" +
                BUYER_ID_COLUMN + " INTEGER PRIMARY KEY," +
                BUYER_NAME_COLUMN + " TEXT," +
                BUYER_MAIL_COLUMN + " TEXT," +
                BUYER_MOBILE_COLUMN + " TEXT," +
                BUYER_USERNAME_COLUMN + " TEXT," +
                BUYER_PASSWORD_COLUMN + " TEXT" +
                ")";
        sqLiteDatabase.execSQL(CREATE_BUYER_TABLE);

        String CREATE_PRODUCT_TABLE = "CREATE TABLE " + PRODUCT_TABLE_NAME + "(" +
                PRODUCT_ID_COLUMN + " INTEGER PRIMARY KEY," +
                PRODUCT_NAME_COLUMN + " TEXT," +
                PRODUCT_PRICE_COLUMN + " TEXT," +
                PRODUCT_DESCRIPTION_COLUMN + " TEXT," +
                PRODUCT_PHOTO_COLUMN + " TEXT" +
                ")";
        sqLiteDatabase.execSQL(CREATE_PRODUCT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public long addBuyer(Buyer buyer) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // Inserting Row to Buyer table
        values.put(BUYER_NAME_COLUMN, buyer.getName());
        values.put(BUYER_MAIL_COLUMN, buyer.getMail());
        values.put(BUYER_MOBILE_COLUMN, buyer.getNumber());
        values.put(BUYER_USERNAME_COLUMN, buyer.getUsername());
        values.put(BUYER_PASSWORD_COLUMN, buyer.getPassword());

        long buyerID = db.insert(BUYER_TABLE_NAME, null, values);

        db.close();
        return buyerID;
    }

    public Buyer getBuyerWithUsernameAndPassword(String username, String password) {

        SQLiteDatabase db = this.getReadableDatabase();

        String table = BUYER_TABLE_NAME;

        String[] columns = new String[]{
                BUYER_ID_COLUMN,
                BUYER_NAME_COLUMN,
                BUYER_MAIL_COLUMN,
                BUYER_MOBILE_COLUMN,
                BUYER_USERNAME_COLUMN,
                BUYER_PASSWORD_COLUMN};
        String where = BUYER_USERNAME_COLUMN + " =?" + " AND " + BUYER_PASSWORD_COLUMN + " =?";

        Cursor cursor = db.query(table, columns, where,
                new String[]{username, password}, null, null, null, null);
        Buyer buyer = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            @SuppressLint("Range") long buyerID = cursor.getLong(cursor.getColumnIndex(BUYER_ID_COLUMN));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(BUYER_NAME_COLUMN));
            @SuppressLint("Range") String mail = cursor.getString(cursor.getColumnIndex(BUYER_MAIL_COLUMN));
            @SuppressLint("Range") String contact = cursor.getString(cursor.getColumnIndex(BUYER_MOBILE_COLUMN));


            buyer = new Buyer();
            buyer.setId(buyerID);
            buyer.setName(name);
            buyer.setNumber(contact);
            buyer.setMail(mail);
            buyer.setUsername(username);
            buyer.setPassword(password);
        }

        db.close();
        return buyer;
    }

    public Buyer getBuyerWithID(long buyerID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String table = BUYER_TABLE_NAME;

        String[] columns = new String[]{
                BUYER_NAME_COLUMN,
                BUYER_MAIL_COLUMN,
                BUYER_MOBILE_COLUMN,

        };
        String where = BUYER_ID_COLUMN + " =?";

        Cursor cursor = db.query(table, columns, where,
                new String[]{String.valueOf(buyerID)}, null, null, null, null);
        Buyer buyer = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(BUYER_NAME_COLUMN));
            @SuppressLint("Range") String mail = cursor.getString(cursor.getColumnIndex(BUYER_MAIL_COLUMN));
            @SuppressLint("Range") String number = cursor.getString(cursor.getColumnIndex(BUYER_MOBILE_COLUMN));



            buyer = new Buyer();
            buyer.setId(buyerID);
            buyer.setName(name);
            buyer.setMail(mail);
            buyer.setNumber(number);

        }
        db.close();
        return buyer;
    }
    public List<Product> getProductList() {
        SQLiteDatabase db = this.getReadableDatabase();

        String table = PRODUCT_TABLE_NAME;

        String[] columns = new String[]{
                PRODUCT_ID_COLUMN,
                PRODUCT_DESCRIPTION_COLUMN,
                PRODUCT_NAME_COLUMN,
                PRODUCT_PRICE_COLUMN,
                PRODUCT_PHOTO_COLUMN};

        Cursor cursor = db.query(table, columns, null,
                null, null, null, null, null);
        ArrayList<Product> productList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME_COLUMN));
                @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE_COLUMN));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(PRODUCT_DESCRIPTION_COLUMN));
                @SuppressLint("Range") String photoPath = cursor.getString(cursor.getColumnIndex(PRODUCT_PHOTO_COLUMN));
                @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex(PRODUCT_ID_COLUMN));

                Product product = new Product();
                product.setName(name);
                product.setPrice(price);
                product.setDescription(description);
                product.setPhotopath(photoPath);
                product.setId(id);


                productList.add(product);
            } while (cursor.moveToNext());
        }
        db.close();
        return productList;
    }
    public long addProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        // Inserting Row to Buyer table
        values.put(PRODUCT_NAME_COLUMN, product.getName());
        values.put(PRODUCT_PRICE_COLUMN, product.getPrice());
        values.put(PRODUCT_DESCRIPTION_COLUMN, product.getDescription());
        values.put(PRODUCT_PHOTO_COLUMN, product.getPhotopath());


        long productID = db.insert(PRODUCT_TABLE_NAME, null, values);

        db.close();
        return productID;
    }

    public Product getProductWithID(long productID) {
        SQLiteDatabase db = this.getReadableDatabase();

        String table = PRODUCT_TABLE_NAME;

        String[] columns = new String[]{
                PRODUCT_NAME_COLUMN,
                PRODUCT_PRICE_COLUMN,
                PRODUCT_DESCRIPTION_COLUMN,
                PRODUCT_PHOTO_COLUMN,
        };
        String where = PRODUCT_ID_COLUMN + " =?";

        Cursor cursor = db.query(table, columns, where,
                new String[]{String.valueOf(productID)}, null, null, null, null);
        Product product = null;
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();

            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(PRODUCT_NAME_COLUMN));
            @SuppressLint("Range") String price = cursor.getString(cursor.getColumnIndex(PRODUCT_PRICE_COLUMN));
            @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex(PRODUCT_DESCRIPTION_COLUMN));
            @SuppressLint("Range") String photoPath = cursor.getString(cursor.getColumnIndex(PRODUCT_PHOTO_COLUMN));


            product = new Product();
            product.setId(productID);
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setPhotopath(photoPath);
        }
        db.close();
        return product;
    }

    public void deleteProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(PRODUCT_TABLE_NAME, PRODUCT_ID_COLUMN + "=?",
                new String[]{String.valueOf(product.getId())});
        db.close();
    }
    }


