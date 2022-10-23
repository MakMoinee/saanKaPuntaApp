package com.rizaltechnology.saankapuntaapp.Interfaces;

import com.google.firebase.firestore.DocumentReference;

public interface FireStoreListener {
    void onAddUserSuccess(DocumentReference documentReference);

    void onAddUserError(Exception e);
}
