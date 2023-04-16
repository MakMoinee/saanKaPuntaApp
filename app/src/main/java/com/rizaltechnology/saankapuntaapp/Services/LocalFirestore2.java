package com.rizaltechnology.saankapuntaapp.Services;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.rizaltechnology.saankapuntaapp.Interfaces.FireStoreListener;
import com.rizaltechnology.saankapuntaapp.Models.Buildings;
import com.rizaltechnology.saankapuntaapp.Models.Offices;

import java.util.ArrayList;
import java.util.List;

public class LocalFirestore2 {

    FirebaseFirestore db;

    public LocalFirestore2() {
        db = FirebaseFirestore.getInstance();
    }

    public void getBuildings(FireStoreListener listener) {
        db.collection("buildings")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (queryDocumentSnapshots.isEmpty()) {
                        listener.onError();
                    } else {
                        List<Buildings> buildingsList = new ArrayList<>();
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            if (documentSnapshot.exists()) {
                                Buildings buildings = documentSnapshot.toObject(Buildings.class);
                                if (buildings != null) {
                                    buildings.setDocID(documentSnapshot.getId());
                                    buildingsList.add(buildings);
                                }
                            }
                        }

                        if (buildingsList.size() > 0) {
                            listener.onSuccess(buildingsList);
                        } else {
                            listener.onError();
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("ERROR_GETTING_BUILDINGS", e.getMessage());
                    listener.onError();
                });
    }

    public void getOffices(FireStoreListener listener) {
        db.collection("offices")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    if (queryDocumentSnapshots.isEmpty()) {
                        listener.onError();
                    } else {
                        List<Offices> officesList = new ArrayList<>();
                        for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            if (documentSnapshot.exists()) {
                                Offices offices = documentSnapshot.toObject(Offices.class);
                                if (offices != null) {
                                    offices.setDocID(documentSnapshot.getId());
                                    officesList.add(offices);
                                }
                            }
                        }

                        if (officesList.size() > 0) {
                            listener.onSuccessOffice(officesList);
                        } else {
                            listener.onError();
                        }
                    }
                })
                .addOnFailureListener(e -> {
                    Log.e("ERROR_GETTING_OFFICES", e.getMessage());
                    listener.onError();
                });
    }
}
