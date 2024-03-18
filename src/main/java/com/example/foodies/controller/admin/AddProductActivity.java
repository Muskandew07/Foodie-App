package com.example.foodies.controller.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.foodies.R;
import com.example.foodies.controller.UserSelectionActivity;
import com.example.foodies.database.AppDatabaseHelper;
import com.example.foodies.model.Product;
import com.example.foodies.utility.ImageFilePath;
import com.example.foodies.utility.Utility;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class AddProductActivity extends AppCompatActivity {

    public static final int REQUEST_CAMERA =123 ;
    public static final int SELECT_FILE =234 ;
    ImageView iconIV;
    String realPath = null;
    EditText editText;
    EditText nameET;
    EditText priceET;
    EditText descriptionET;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproductscreen);
        iconIV=findViewById(R.id.image);
        nameET = findViewById(R.id.name);
        priceET = findViewById(R.id.price);
        descriptionET = findViewById(R.id.description);


    }
    public void add(View view) {



        String name = nameET.getText().toString();
        String price = priceET.getText().toString();
        String description = descriptionET.getText().toString();

        if (name.length() == 0) {
            nameET.setError("Please Enter Name");

        }
        else if (price.length() == 0)
        {
            priceET.setError("Please Enter Price");
        }
        else if (description.length() == 0)
        {
            descriptionET.setError("Please Enter Description");
        }
        else
        {
            Product product = new Product();
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setPhotopath(realPath);

            AppDatabaseHelper databaseHelper = new AppDatabaseHelper(this);
            databaseHelper.addProduct(product);

            Toast.makeText(getApplicationContext(),"You have successfully added the product",Toast.LENGTH_LONG).show();
            clearUI();

        }
    }

    public void clearUI() {

        nameET.setText("");
        priceET.setText("");
        descriptionET.setText("");

        iconIV.setVisibility(View.GONE);
        realPath = null;

    }


    public void selectPhoto(View view)
    {
        final CharSequence[] items = { "Take Photo", "Choose from Library",
                "Cancel" };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Photo");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                String userChoosenTask;

                boolean result= Utility.checkPermission(AddProductActivity.this);
                if (items[item].equals("Take Photo")) {
                    if(result)
                        cameraIntent();
                } else if (items[item].equals("Choose from Library")) {
                    if(result)
                        galleryIntent();
                } else if (items[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }


    private void cameraIntent()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent,REQUEST_CAMERA);
    }

    private void galleryIntent()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);//
        startActivityForResult(Intent.createChooser(intent, "Select File"),SELECT_FILE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == SELECT_FILE)
                onSelectFromGalleryResult(data);
            else if (requestCode == REQUEST_CAMERA)
                onCaptureImageResult(data);
        }
    }

    @SuppressWarnings("deprecation")
    private void onSelectFromGalleryResult(Intent data) {

        Uri uri = data.getData();


        realPath = ImageFilePath.getPath(this, data.getData());

        try
        {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

            iconIV.setImageBitmap(bitmap);
            iconIV.setVisibility(View.VISIBLE);

        } catch (IOException e)
        {
            e.printStackTrace();
        }

    }
    private void onCaptureImageResult(Intent data) {
        Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        thumbnail.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File destination = new File(Environment.getExternalStorageDirectory(),
                System.currentTimeMillis() + ".jpg");
        FileOutputStream fo;
        try {
            destination.createNewFile();
            Uri fileUri = Uri.fromFile(destination);
            realPath = ImageFilePath.getPath(this, fileUri);
            fo = new FileOutputStream(destination);
            fo.write(bytes.toByteArray());
            fo.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        iconIV.setImageBitmap(thumbnail);
        iconIV.setVisibility(View.VISIBLE);
    }


    public void onClick(View v) {
        editText.getText().clear(); //or you can use editText.setText("");
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
