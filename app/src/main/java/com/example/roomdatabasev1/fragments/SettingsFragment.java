package com.example.roomdatabasev1.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.roomdatabasev1.R;
import com.example.roomdatabasev1.models.Profile;
import com.example.roomdatabasev1.storage.ProfileDataBase;

public class SettingsFragment extends Fragment {

    private Button buttonLogout, buttonUpdate, buttonDelete;
    private EditText editTextName, editTextEmail, editTextPassword, editTextAddress;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.settings_fragment, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextName = view.findViewById(R.id.editTextNameU);
        editTextEmail = view.findViewById(R.id.editTextEmailU);
        editTextPassword = view.findViewById(R.id.editTextPasswordU);
        editTextAddress = view.findViewById(R.id.editTextAddressU);

        buttonLogout = view.findViewById(R.id.buttonLogout);
        registerForContextMenu(buttonLogout);


        buttonUpdate = view.findViewById(R.id.buttonUpdateProfile);
        buttonDelete = view.findViewById(R.id.buttonDelete);

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                update(editTextName.getText().toString().trim(),
                        editTextEmail.getText().toString().trim(),
                        editTextPassword.getText().toString().trim(),
                        editTextAddress.getText().toString().trim());
            }
        });


    }

    private void delete() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                  ProfileDataBase profileDataBase = ProfileDataBase.getInstance(getContext());
                //profileDataBase.profileDao().deleteWithEmail("linoop");
            }
        }).start();
    }

    private void update(final String name, final String email, final String password, final String address){

        new Thread(new Runnable() {
            @Override
            public void run() {
                ProfileDataBase p = ProfileDataBase.getInstance(getContext());
                p.profileDao().updateProfile(name, email, password, address);
            }
        }).start();

    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        menu.setHeaderTitle("Exit from App");
        menu.setHeaderIcon(R.drawable.ic_power_settings_new_black_24dp);
        menu.add(0, v.getId(), 1, "Login");
        menu.add(0, v.getId(), 0, "Logout");
        menu.add(0, v.getId(), 2, "Delete");


    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        Toast.makeText(getContext(), "Selected item " + item.getTitle(), Toast.LENGTH_LONG).show();

        return true;

    }

}
