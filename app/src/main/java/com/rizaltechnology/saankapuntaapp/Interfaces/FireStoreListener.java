package com.sample.clinic.Interfaces;

import com.google.firebase.firestore.DocumentReference;
import com.sample.clinic.Models.Users;

public interface FireStoreListener {
    void onAddUserSuccess(Users users);

    void onAddUserError(Exception e);
}
