package com.adndigitalbd.androidroomdatabase;

import android.arch.persistence.room.Insert;

public interface DaoClass {

    @Insert
    public void addUser(User user);
}
