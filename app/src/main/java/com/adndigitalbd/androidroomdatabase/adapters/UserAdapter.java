package com.adndigitalbd.androidroomdatabase.adapters;

import android.app.AlertDialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.adndigitalbd.androidroomdatabase.R;
import com.adndigitalbd.androidroomdatabase.activities.AddUserActivity;
import com.adndigitalbd.androidroomdatabase.activities.MainActivity;
import com.adndigitalbd.androidroomdatabase.database.DatabaseClass;
import com.adndigitalbd.androidroomdatabase.entity.User;
import com.adndigitalbd.androidroomdatabase.globalVariables.GlobalVariables;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    public List<User> userList;
    DatabaseClass db;
    private Context mContext;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        mContext = parent.getContext();
        db = Room.databaseBuilder(parent.getContext(),
                DatabaseClass.class, "myDatabase").allowMainThreadQueries().build();

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_adapter, parent, false);
        UserHolder userAdapter = new UserHolder(v);
        return userAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull final UserAdapter.UserHolder userHolder, final int position) {
        userHolder.name.setText(userList.get(position).getName().toString());
        userHolder.email.setText(userList.get(position).getEmail().toString());


        userHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                AlertDialog alertDialog = new AlertDialog.Builder(mContext).create();

                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "Edit",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                GlobalVariables.id = userList.get(position).getId();
                                GlobalVariables.name = userList.get(position).getName();
                                GlobalVariables.email = userList.get(position).getEmail();
                                GlobalVariables.updateFlag = "update";

                                Intent intent = new Intent(mContext,AddUserActivity.class);
                                mContext.startActivity(intent);
                            }
                        });


                alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                deleteUser(userList.get(position).getId());
                                dialog.dismiss();
                                userList.remove(position);
                                notifyItemRemoved(position);
                                notifyItemRangeChanged(position, userList.size());


                            }
                        });
                alertDialog.show();


            }
        });
    }

    @Override
    public int getItemCount() {
        return userList.size();
    }


    private void deleteUser(int id) {
        User user = new User();
        user.setId(id);
        db.daoClass().deleteUser(user);
        Toast.makeText(mContext, "Deleted!", Toast.LENGTH_SHORT).show();
    }


    public class UserHolder extends RecyclerView.ViewHolder {

        private TextView name, email;

        public UserHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);

        }
    }
}
