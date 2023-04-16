package com.rizaltechnology.saankapuntaapp.Interfaces;

import com.rizaltechnology.saankapuntaapp.Models.Buildings;
import com.rizaltechnology.saankapuntaapp.Models.Offices;

import java.util.List;

public interface FragmentFinish {
    void onFinishFirstFragment();
    void onFinishSecondFragment();
    void onLoginFinish();
    void openBuildingFragment(Buildings buildings, Offices o, List<String> l);
    void openProfileFragment();
}
