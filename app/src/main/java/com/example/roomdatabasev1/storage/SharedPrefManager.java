package com.example.roomdatabasev1.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.roomdatabasev1.models.Profile;

public class SharedPrefManager {

    private static final String SHARED_PREF_NAME = "profile_shared_pref";
    private static SharedPrefManager sharedPrefManagerInstance;
    private Context context;

    SharedPrefManager(Context context){
        this.context = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context){
        if(sharedPrefManagerInstance == null){
            sharedPrefManagerInstance = new SharedPrefManager(context);
        }
        return sharedPrefManagerInstance;
    }

    public void saveProfile(Profile profile){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt("id", profile.getId());
        editor.putString("name", profile.getName());
        editor.putString("email", profile.getEmail());
        editor.putString("address", profile.getAddress());
        editor.commit();
    }

    public boolean isLoggedIn(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt("id", -1)!= -1;
    }

    public Profile getProfile(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new Profile(
                sharedPreferences.getInt("id",-1),
                sharedPreferences.getString("name", null),
                sharedPreferences.getString("email",null),
                sharedPreferences.getString("password",null),
                sharedPreferences.getString("address",null)
        );
    }

    public void clear(){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
