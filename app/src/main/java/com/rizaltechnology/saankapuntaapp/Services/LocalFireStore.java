package com.rizaltechnology.saankapuntaapp.Services;

import com.rizaltechnology.saankapuntaapp.Interfaces.FireStoreListener;
import com.rizaltechnology.saankapuntaapp.Models.Buildings;
import com.rizaltechnology.saankapuntaapp.Models.Users;

public interface LocalFireStore {

    void insertUserRecord(Users users, FireStoreListener listener);

    void getLogin(Users users, FireStoreListener listener);

    void storeBuildings(Buildings buildings, FireStoreListener listener);
}
