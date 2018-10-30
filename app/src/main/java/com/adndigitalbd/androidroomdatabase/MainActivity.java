package com.adndigitalbd.androidroomdatabase;

import android.arch.persistence.room.Room;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    DatabaseClass db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        db = Room.databaseBuilder(getApplicationContext(),
                DatabaseClass.class, "myDatabase").allowMainThreadQueries().build();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = new User();
                //user.setId(5);
                user.setName("hasnat arif ");
                user.setEmail("arifhasnat.info@gmail.com");
                db.daoClass().addUser(user);
                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();


//                deleteUser();
//                updateUser();

            }
        });

        getAllData();
    }


    private void getAllData(){
        List<User> userList = new ArrayList<>();
        userList=db.daoClass().getUsers();
        for (int i = 0; i < userList.size(); i++) {
            Toast.makeText(this, userList.get(i).getName().toString(), Toast.LENGTH_SHORT).show();
        }

    }

    private void deleteUser(){
        User user = new User();
        user.setId(3);
        db.daoClass().deleteUser(user);

        Toast.makeText(this, "Delete user", Toast.LENGTH_SHORT).show();
    }

    private void updateUser(){
        User user = new User();
        user.setId(4);
        user.setEmail("arifhasnat.com@gmail.com");
        user.setName("arif hasnat");
        db.daoClass().updateUser(user);

        Toast.makeText(this, "User Updated", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
