package com.adndigitalbd.androidroomdatabase.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.adndigitalbd.androidroomdatabase.entity.User;
import com.adndigitalbd.androidroomdatabase.dao.DaoClass;

@Database(entities = User.class, version = 1)
public abstract class DatabaseClass extends RoomDatabase {
    public abstract DaoClass daoClass();
}
