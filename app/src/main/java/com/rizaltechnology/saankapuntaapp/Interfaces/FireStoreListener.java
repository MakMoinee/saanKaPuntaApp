package com.rizaltechnology.saankapuntaapp.Interfaces;

import com.google.firebase.firestore.DocumentReference;
import com.rizaltechnology.saankapuntaapp.Models.Users;

public interface FireStoreListener {
    void onAddUserSuccess(Users users);

    void onAddUserError(Exception e);
}
