package com.example.michal.dietvoltwo.util;


import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;



public class ConvertDrawableToStore {

    public static String convetrBitmapToStorage(int image, Context context){

        Log.d("CONWERSJA ====== ", String.valueOf(image));
        Bitmap photo = BitmapFactory.decodeResource(context.getResources(), image);

        ContextWrapper cw = new ContextWrapper(context);
        File directory = cw.getDir("image", Context.MODE_PRIVATE);
        File mypath=new File(directory,"2130837603.jpg");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            photo.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Log.d("KURWA === ", directory.getAbsolutePath());
        return directory.getAbsolutePath();
    }

    public static byte[] test(int image, Context context){
        Drawable p = ContextCompat.getDrawable(context,image);
        Bitmap bitmap = ((BitmapDrawable)p).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        byte[] bitmapdata = stream.toByteArray();
        return bitmapdata;
    }



}
//D/NEW IMAGE ==:
// /data/user/0/com.example.michal.dietvoltwo/app_image
///data/user/0/com.example.michal.dietvoltwo/app_2130837618imageDir
//D/LOGI ADAPTER ==: /data/user/0/com.example.michal.dietvoltwo/app_2130837618imageDir


/*
    private byte[] getByteImage(int imgaDrawable){

        Drawable p = ContextCompat.getDrawable(ProductListActivity.getAppContext(),imgaDrawable);
        Bitmap bitmap = ((BitmapDrawable)p).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        byte[] bitmapdata = stream.toByteArray();
        return bitmapdata;

    }
 */