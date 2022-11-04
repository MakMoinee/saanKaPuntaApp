package com.rizaltechnology.saankapuntaapp.Preferrences;

import android.content.Context;
import android.content.SharedPreferences;

import com.rizaltechnology.saankapuntaapp.Models.Users;

public class MyUserPreferrence {

    private Context context;

    public MyUserPreferrence(Context mContext) {
        this.context = mContext;
    }

    public void saveUser(Users users) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("docID", users.getDocID());
        editor.putString("email", users.getEmail());
        editor.putString("password", users.getPassword());
        editor.putInt("userType", users.getUserType());
        editor.commit();
        editor.apply();

    }

    public Users getUsers() {
        Users users = new Users();
        SharedPreferences sharedPreferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        users.setDocID(sharedPreferences.getString("docID", ""));
        users.setEmail(sharedPreferences.getString("email", ""));
        users.setPassword(sharedPreferences.getString("password", ""));
        users.setUserType(sharedPreferences.getInt("userType", 0));
        return users;
    }
}
