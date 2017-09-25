package com.example.michal.dietvoltwo.util;


import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import static com.example.michal.dietvoltwo.util.Constant.KEY_SHAREPREFERENCES_EMAIL;
import static com.example.michal.dietvoltwo.util.Constant.KEY_SHAREPREFERENCES_NAME;
import static com.example.michal.dietvoltwo.util.Constant.KEY_SHAREPREFERENCES_PASSWORD;

public class SetSharedPreferences {

    public static void setNameSharedPreferences(String name, Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_SHAREPREFERENCES_NAME, name);
        editor.commit();
    }

    public static String getNameSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(KEY_SHAREPREFERENCES_NAME,null);
    }

    public static void setEmailSharedPreferences(String email, Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_SHAREPREFERENCES_EMAIL,email);
        editor.commit();
    }

    public static String getEmailSharedPreferences(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(KEY_SHAREPREFERENCES_EMAIL,null);
    }

    public static final void setPasswordSharedPreferences(String password, Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_SHAREPREFERENCES_PASSWORD,password);
        editor.commit();
    }

    public static String getPasswordSharedPreferences(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        return sharedPreferences.getString(KEY_SHAREPREFERENCES_PASSWORD,null);
    }

}
