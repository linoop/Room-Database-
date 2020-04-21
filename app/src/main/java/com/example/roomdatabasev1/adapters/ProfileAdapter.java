package com.example.roomdatabasev1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdatabasev1.models.Profile;
import com.example.roomdatabasev1.storage.ProfileDataBase;
import com.example.roomdatabasev1.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>{

    private Context context;
    private List<Profile> profileList = new ArrayList<Profile>();

    public ProfileAdapter(Context context) {
        this.context = context;
        query();


    }

    private void query(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ProfileDataBase profileDataBase = ProfileDataBase.getInstance(context);
                //profileList = profileDataBase.profileDao().getProfileWithName("linoop");
                profileList = profileDataBase.profileDao().getProfileList();
            }
        }).start();
    }

    @NonNull
    @Override
    public ProfileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.profiles_recyclerview, parent, false);
        return new ProfileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileViewHolder holder, int position) {

        Profile profile = profileList.get(position);
        holder.textViewName.setText(profile.getName());
        holder.textViewEmail.setText(profile.getEmail());
        holder.textViewAddress.setText(profile.getAddress());


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        return false;
                    }
                });
                popupMenu.show();
                return false;
            }
        });

    }

    @Override
    public int getItemCount() {
        return profileList.size();
    }

    class ProfileViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName, textViewEmail, textViewAddress;

        public ProfileViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewNameR);
            textViewEmail = itemView.findViewById(R.id.textViewEmailR);
            textViewAddress = itemView.findViewById(R.id.textViewAddressR);

        }
    }
}
