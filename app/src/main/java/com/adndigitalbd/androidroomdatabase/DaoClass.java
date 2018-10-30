package com.adndigitalbd.androidroomdatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DaoClass {

    @Insert
    public void addUser(User user);

    @Query("select * from users")
    public List<User> getUsers();
}
