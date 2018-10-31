package com.adndigitalbd.androidroomdatabase.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.adndigitalbd.androidroomdatabase.entity.User;

import java.util.List;

@Dao
public interface DaoClass {

    /***
     *  insert data
     * @param user
     */
    @Insert
    public void addUser(User user);

    /**
     * retrieve all data from users table
     * @return
     */
    @Query("select * from users")
    public List<User> getUsers();

    /***
     * Delete user from users table
     * @param user
     */
    @Delete
    public void deleteUser(User user);


    /***
     * Update user from users table
     * @param user
     */
    @Update
    public void updateUser(User user);
}
