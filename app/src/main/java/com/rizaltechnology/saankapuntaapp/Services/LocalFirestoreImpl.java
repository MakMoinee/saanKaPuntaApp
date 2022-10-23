package com.rizaltechnology.saankapuntaapp.Services;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rizaltechnology.saankapuntaapp.Common.Common;
import com.rizaltechnology.saankapuntaapp.Interfaces.FireStoreListener;
import com.rizaltechnology.saankapuntaapp.Models.Users;

import java.util.Map;

public class LocalFirestoreImpl implements LocalFireStore {

    private Context mContext;
    private FirebaseFirestore db;

    public LocalFirestoreImpl(Context context) {
        mContext = context;
        db = FirebaseFirestore.getInstance();
    }


    @Override
    public void insertUserRecord(Users users, FireStoreListener listener) {
        Map<String, Object> mapToInsert = Common.toLoginMaps(users);
        db.collection("user")
                .document("users")
                .set(mapToInsert)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        listener.onAddUserSuccess(null);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Log.e("INSERT_USER_RECORD", e.getMessage());
                    }
                });
    }

    @Override
    public void getLogin(Users users) {

    }
}
