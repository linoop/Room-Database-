package com.example.roomdatabasev1.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.roomdatabasev1.models.Profile;
import com.example.roomdatabasev1.storage.ProfileDataBase;
import com.example.roomdatabasev1.R;

public class CreateProfileFragment extends Fragment {

    private EditText editTextName, editTextEmail, editTextPassword, editTextAddress;
    private Button buttonCreate;
    private TextView textViewLogin;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.create_profile_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        editTextName = view.findViewById(R.id.editTextNameC);
        editTextEmail = view.findViewById(R.id.editTexEmailC);
        editTextAddress = view.findViewById(R.id.editTextAddressC);
        editTextPassword = view.findViewById(R.id.editTextPasswordC);
        buttonCreate = view.findViewById(R.id.buttonCreate);

        buttonCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createProfile(
                        editTextName.getText().toString().trim(),
                        editTextEmail.getText().toString().trim(),
                        editTextPassword.getText().toString().trim(),
                        editTextAddress.getText().toString().trim()

                );
                Toast.makeText(getContext(),"Profile saved successfully", Toast.LENGTH_LONG).show();
            }
        });
    }



    private void createProfile(final String name, final String email, final String password, final String address){

        new Thread(new Runnable() {
            @Override
            public void run() {
                ProfileDataBase profileDataBase = ProfileDataBase.getInstance(getContext());
                Profile profile = new Profile(name, email,password,address);
                profileDataBase.profileDao().insertProfile(profile);
            }
        }).start();
    }
}
