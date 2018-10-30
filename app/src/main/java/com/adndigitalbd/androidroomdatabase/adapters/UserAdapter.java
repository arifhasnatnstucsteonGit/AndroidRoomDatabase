package com.adndigitalbd.androidroomdatabase.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.adndigitalbd.androidroomdatabase.R;
import com.adndigitalbd.androidroomdatabase.entity.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    public List<User> userList;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
    }

    @NonNull
    @Override
    public UserAdapter.UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {

        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_adapter, parent, false);
        UserHolder userAdapter = new UserHolder(v);
        return userAdapter;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserHolder userHolder, int position) {
        userHolder.name.setText(userList.get(position).getName().toString());
        userHolder.email.setText(userList.get(position).getEmail().toString());
    }

    @Override
    public int getItemCount() {
        return userList.size();
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
