package com.rizaltechnology.saankapuntaapp.Interfaces;

import com.rizaltechnology.saankapuntaapp.Models.Buildings;

import java.util.List;

public interface StorageListener {
    void onSuccess(String data);
    void onSuccessBuilding(List<Buildings> buildingsList);
    void onError(Exception e);
}
