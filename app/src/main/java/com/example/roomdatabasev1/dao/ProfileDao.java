package com.example.roomdatabasev1.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roomdatabasev1.models.Profile;

import java.util.List;

@Dao
public interface ProfileDao {

    @Query("select * from profile")
    List<Profile> getProfileList();

    @Query("select * from profile where name = :name")
    List<Profile>getProfileWithName(String name);

    @Query("delete from profile where id = :id")
    void deleteById(int id);
    @Query("update profile set name = :name, email = :email, password = :password, address = :address")
    void updateProfile(String name, String email, String password, String address);

    @Insert
    void insertProfile(Profile profile);

    @Update
    void updateProfile(Profile profile);

    @Delete
    void deleteProfile(Profile profile);

}
