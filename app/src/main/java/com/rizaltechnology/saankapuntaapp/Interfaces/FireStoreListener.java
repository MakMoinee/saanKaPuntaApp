package com.rizaltechnology.saankapuntaapp.Interfaces;

import com.google.firebase.firestore.DocumentReference;
import com.rizaltechnology.saankapuntaapp.Models.Buildings;
import com.rizaltechnology.saankapuntaapp.Models.Offices;
import com.rizaltechnology.saankapuntaapp.Models.Users;

import java.util.List;

public interface FireStoreListener {
    default void onAddUserSuccess(Users users){

    }

    default void onAddUserError(Exception e){

    }

    default void onError(){

    }

    default void onSuccess(List<Buildings> b){

    }

    default void onSuccessOffice(List<Offices> o){

    }
}
