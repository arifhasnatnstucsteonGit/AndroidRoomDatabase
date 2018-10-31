package com.adndigitalbd.androidroomdatabase.activities;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.solver.GoalRow;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.adndigitalbd.androidroomdatabase.R;
import com.adndigitalbd.androidroomdatabase.database.DatabaseClass;
import com.adndigitalbd.androidroomdatabase.entity.User;
import com.adndigitalbd.androidroomdatabase.globalVariables.GlobalVariables;

public class AddUserActivity extends AppCompatActivity {


    private EditText mName,mEmail;
    private Button mAddUser;
    private String nameString,emailString;

    private Context mContext;
    DatabaseClass db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mContext = AddUserActivity.this;

        mName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mAddUser = findViewById(R.id.addUser);


        if (GlobalVariables.updateFlag.equals("update")){
            mName.setText(GlobalVariables.name);
            mEmail.setText(GlobalVariables.email);
        }

        db = Room.databaseBuilder(getApplicationContext(),
                DatabaseClass.class, "myDatabase").allowMainThreadQueries().build();

        mAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                nameString = mName.getText().toString();
                emailString = mEmail.getText().toString();

                if (nameString.length()>0 && emailString.length()>0){



                    if (GlobalVariables.updateFlag.equals("update")){
                        User user = new User();
                        user.setId(GlobalVariables.id);
                        user.setName(nameString);
                        user.setEmail(emailString);
                        db.daoClass().updateUser(user);
                        Toast.makeText(mContext, "Successful", Toast.LENGTH_SHORT).show();
                        GlobalVariables.updateFlag = "";

                        startActivity(new Intent(AddUserActivity.this,MainActivity.class));
                    }else {

                        User user = new User();
                        user.setName(nameString);
                        user.setEmail(emailString);
                        db.daoClass().addUser(user);
                        Toast.makeText(mContext, "Successful", Toast.LENGTH_SHORT).show();

                        startActivity(new Intent(AddUserActivity.this, MainActivity.class));

                    }

                }

            }
        });






    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        GlobalVariables.updateFlag = "";
    }
}
