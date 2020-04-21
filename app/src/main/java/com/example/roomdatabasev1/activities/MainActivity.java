package com.example.roomdatabasev1.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.roomdatabasev1.fragments.CreateProfileFragment;
import com.example.roomdatabasev1.fragments.HomeFragment;
import com.example.roomdatabasev1.fragments.ProfileFragment;
import com.example.roomdatabasev1.R;
import com.example.roomdatabasev1.fragments.SettingsFragment;
import com.example.roomdatabasev1.storage.SharedPrefManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        displayFragment(new HomeFragment());

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!SharedPrefManager.getInstance(this).isLoggedIn()){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.menuHome:
                fragment = new HomeFragment();
                break;
            case R.id.menuProfile:
                fragment = new ProfileFragment();
                break;
            case R.id.menuSettings:
                fragment = new SettingsFragment();
                break;
            case R.id.menuAddProfile:
                fragment = new CreateProfileFragment();
                break;
        }
        if(fragment!=null){
            displayFragment(fragment);
        }

        return super.onOptionsItemSelected(item);
    }

    private void displayFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.relativeLayout, fragment)
                .commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.menuHome:
                fragment = new HomeFragment();
                break;
            case R.id.menuProfile:
                fragment = new ProfileFragment();
                break;
            case R.id.menuSettings:
                fragment = new SettingsFragment();
                break;
            case R.id.menuAddProfile:
                fragment = new CreateProfileFragment();
                break;
        }
        if(fragment!=null){
            displayFragment(fragment);
        }
        return false;
    }

}
