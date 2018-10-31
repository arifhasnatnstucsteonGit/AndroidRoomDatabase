package com.adndigitalbd.androidroomdatabase.activities;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.adndigitalbd.androidroomdatabase.R;
import com.adndigitalbd.androidroomdatabase.adapters.UserAdapter;
import com.adndigitalbd.androidroomdatabase.database.DatabaseClass;
import com.adndigitalbd.androidroomdatabase.entity.User;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;

public class MainActivity extends AppCompatActivity {


    DatabaseClass db;

    private RecyclerView mRecyclerView;
    public UserAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<User> userList;

    private String name = "";
    private String emailString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle("Room Database");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        db = Room.databaseBuilder(getApplicationContext(),
                DatabaseClass.class, "myDatabase").allowMainThreadQueries().build();
        getAllData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               startActivity(new Intent(MainActivity.this,AddUserActivity.class));
            }
        });





    }


    private void getAllData() {
        List<User> userList = new ArrayList<>();
        userList = db.daoClass().getUsers();

        if (userList.size() > 0) {
            mAdapter = new UserAdapter(userList);
            mRecyclerView.setAdapter(mAdapter);

        } else {
            Toast.makeText(this, "No data ", Toast.LENGTH_SHORT).show();
        }


    }

    private void deleteUser() {
        User user = new User();
        user.setId(3);
        db.daoClass().deleteUser(user);

        Toast.makeText(this, "Delete user", Toast.LENGTH_SHORT).show();
    }

    private void updateUser() {
        User user = new User();
        user.setId(4);
        user.setEmail("arifhasnat.com@gmail.com");
        user.setName("arif hasnat");
        db.daoClass().updateUser(user);

        Toast.makeText(this, "User Updated", Toast.LENGTH_SHORT).show();
    }


}
