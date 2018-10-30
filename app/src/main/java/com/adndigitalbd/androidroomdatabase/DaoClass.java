package com.adndigitalbd.androidroomdatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

@Dao
public interface DaoClass {

    @Insert
    public void addUser(User user);
}
