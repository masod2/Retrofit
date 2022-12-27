package com.example.Retrofit.services;

import android.content.Context;
import android.content.SharedPreferences;


public class TokenSaver {

    public static boolean IsDelevery(Context c) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        return prefs.getBoolean("isDelevery", false);
    }

    public static void setIsDelevery(Context c, boolean isDelevery) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("isDelevery", isDelevery);
        editor.apply();
    }


    public static boolean IsFirst(Context c) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);

        return prefs.getBoolean("IsFirst", true);
    }

    public static void setIsFirst(Context c, boolean IsFirst) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("IsFirst", IsFirst);
        editor.apply();
    }

    public static String getToken(Context c) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        return prefs.getString("TOKEN", "");
    }

    public static void setToken(Context c, String token) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("TOKEN", token);
        editor.apply();
    }

    public static String getPositionLat(Context c) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        return prefs.getString("PositionLat", "0");
    }

    public static void setPositionLat(Context c, String PositionLat) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("PositionLat", PositionLat);
        editor.apply();
    }
    public static String getPositionLong(Context c) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        return prefs.getString("PositionLong","0");
    }

    public static void setPositionLong(Context c, String PositionLong) {
        SharedPreferences prefs = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("PositionLong", PositionLong);
        editor.apply();
    }
    public static void logout(Context c) {
        SharedPreferences sharedPreferences = c.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
     }

}
