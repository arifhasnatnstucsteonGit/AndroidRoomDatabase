package com.adndigitalbd.androidroomdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.RoomDatabase;

@Database(entities = User.class, version = 1)
public abstract class DatabaseClass extends RoomDatabase {
    public abstract DaoClass daoClass();
}
