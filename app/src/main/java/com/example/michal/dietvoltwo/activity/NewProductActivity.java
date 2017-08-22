package com.example.michal.dietvoltwo.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.michal.dietvoltwo.R;
import com.example.michal.dietvoltwo.dto.ProductDto;
import com.example.michal.dietvoltwo.service.Impl.ProductServiceImpl;

import java.io.File;
import java.util.Date;

import io.realm.Realm;

public class NewProductActivity extends AppCompatActivity {

    private EditText nameEditText, producentEditText, kcalEditText, carboEditText, proteinEditText,
            fatEditText, igEditText, forDiabedtEditText, typeMacroEditText, timeDayEditText;
    private Button photoButton;
    private Spinner spinner;
    private String[] elementy = {"Węglowodanowy", "Białkowy", "Tłuszczowy"};
    private String typProduct = "Węglowodanowy";

    private static final int TAKE_PHOTO_CODE = 0;
    private static final int CAMERA_REQUEST = 1888;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        createView();

        this.realm = ProductServiceImpl.with(this).getRealm();
        ProductServiceImpl.with(this).refresh();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProductDto newProduct = new ProductDto();
                newProduct.setName(nameEditText.getText().toString());
                newProduct.setProducent(producentEditText.getText().toString());
                newProduct.setProductTyp(typeMacroEditText.getText().toString());
                newProduct.setTimeOfDay(timeDayEditText.getText().toString());
                newProduct.setKcal(Integer.parseInt(kcalEditText.getText().toString()));
                newProduct.setB(Integer.parseInt(proteinEditText.getText().toString()));
                newProduct.setT(Integer.parseInt(fatEditText.getText().toString()));
                newProduct.setW(Integer.parseInt(carboEditText.getText().toString()));
                newProduct.setwW((Integer.parseInt(carboEditText.getText().toString()) / 10));
                newProduct.setIg(Integer.parseInt(igEditText.getText().toString()));
                newProduct.setForDiabets(forDiabedtEditText.getText().toString().equals("TAK") ? 1 : 0);
                newProduct.setCreate(new Date());
                newProduct.setImage(R.drawable.ziemniaki);

                ProductServiceImpl.getInstance().save(newProduct);


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void createView() {
        nameEditText = (EditText) findViewById(R.id.name_product_edit_text);
        producentEditText = (EditText) findViewById(R.id.producent_edit_text);
        kcalEditText = (EditText) findViewById(R.id.kcal_product_edit_text);
        carboEditText = (EditText) findViewById(R.id.w_product_edit_text);
        proteinEditText = (EditText) findViewById(R.id.b_product_edit_text);
        fatEditText = (EditText) findViewById(R.id.t_product_edit_text);
        igEditText = (EditText) findViewById(R.id.ig_edit_text);
        forDiabedtEditText = (EditText) findViewById(R.id.for_diabets_product_edit_text);
        typeMacroEditText = (EditText) findViewById(R.id.type_macro_edit_text);
        timeDayEditText = (EditText) findViewById(R.id.time_day_edit_text);
        photoButton = (Button) findViewById(R.id.take_photo);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (getApplicationContext().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                    if (shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());
                        builder.setMessage("Pozwolenie na użycie camry");
                        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                requestPermissions(new String[]{Manifest.permission.CAMERA}, TAKE_PHOTO_CODE);
                            }
                        });

                        builder.create();
                    } else {
                        requestPermissions(new String[]{Manifest.permission.CAMERA}, TAKE_PHOTO_CODE);
                    }
                } else {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }

            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, elementy);
        spinner.setAdapter(adapter1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        typProduct = "Węglowodanowy";
                        break;
                    case 1:
                        typProduct = "Białkowy";
                        break;

                    case 2:
                        typProduct = "Tłuszczowy";
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAMERA_REQUEST) {
            ContentValues values = new ContentValues();
            String path = String.valueOf(System.currentTimeMillis());
            values.put(MediaStore.Images.Media.TITLE, new File(this.getFilesDir(), path).toString());
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
            values.put(MediaStore.Images.Media.DATA, path);
            getContentResolver().insert( MediaStore.Images.Media.EXTERNAL_CONTENT_URI , values);
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
