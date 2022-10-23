package com.rizaltechnology.saankapuntaapp.Services;

import com.rizaltechnology.saankapuntaapp.Interfaces.FireStoreListener;
import com.rizaltechnology.saankapuntaapp.Models.Users;

public interface LocalFireStore {

    void insertUserRecord(Users users, FireStoreListener listener);
    void getLogin(Users users);
}
