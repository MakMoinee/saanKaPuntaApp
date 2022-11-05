package com.rizaltechnology.saankapuntaapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.rizaltechnology.saankapuntaapp.Common.Constants;
import com.rizaltechnology.saankapuntaapp.Fragments.BuildingFragment;
import com.rizaltechnology.saankapuntaapp.Fragments.CreateAccountFragment;
import com.rizaltechnology.saankapuntaapp.Fragments.FirstFragment;
import com.rizaltechnology.saankapuntaapp.Fragments.MainFormFragment;
import com.rizaltechnology.saankapuntaapp.Fragments.SecondFragment;
import com.rizaltechnology.saankapuntaapp.Interfaces.FragmentFinish;
import com.rizaltechnology.saankapuntaapp.Interfaces.StorageListener;
import com.rizaltechnology.saankapuntaapp.Models.Buildings;
import com.rizaltechnology.saankapuntaapp.Models.Users;
import com.rizaltechnology.saankapuntaapp.Preferrences.MyUserPreferrence;
import com.rizaltechnology.saankapuntaapp.Services.Storage;

import java.util.List;

public class MainActivity extends AppCompatActivity implements FragmentFinish {

    private Fragment fragment;
    private BuildingFragment buildFragment;
    private FragmentTransaction ft;
    private FragmentManager fm;
    private int fragmentIndex = 0;
    private ProgressDialog ps;

    private List<Buildings> buildings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_main);
        initViews();
        ps = new ProgressDialog(MainActivity.this);
    }

    private void initViews() {
//        storage = new Storage(MainActivity.this);
        String[] buildingFilePaths = Constants.buildingFileFolder.split(",");

//        storage.getBuildingsFromStorage(buildingFilePaths[0]);
        Users users = new MyUserPreferrence(MainActivity.this).getUsers();
        if (users.getDocID() != "") {
            onLoginFinish();
        } else {
            startFragment();
        }
    }


    private void startFragment() {
        fragmentIndex = 0;
        fragment = new FirstFragment(MainActivity.this, MainActivity.this);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frame, fragment, null);
        ft.commit();
    }

    @Override
    public void onFinishFirstFragment() {
        fragmentIndex = 1;
        fragment = new SecondFragment(MainActivity.this, MainActivity.this);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frame, fragment, null);
        ft.commit();
    }

    @Override
    public void onFinishSecondFragment() {
        fragmentIndex = 2;
        fragment = new CreateAccountFragment(MainActivity.this, MainActivity.this, ps);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frame, fragment, null);
        ft.commit();
    }

    @Override
    public void onLoginFinish() {
        fragmentIndex = 3;
        fragment = new MainFormFragment(MainActivity.this, MainActivity.this, false);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frame, fragment, null);
        ft.commit();
    }

    @Override
    public void openBuildingFragment(Buildings buildings) {
        fragmentIndex = 4;
        buildFragment = new BuildingFragment(MainActivity.this, buildings);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frame, buildFragment, null);
        ft.addToBackStack("building");
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        switch (fragmentIndex) {
            case 1:
            case 3:
                super.onBackPressed();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case 2:
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                onFinishFirstFragment();
                break;
            case 4:
                buildFragment.releasePlayer();
                overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
                fragmentIndex = 3;
                fragment = new MainFormFragment(MainActivity.this, MainActivity.this, true);
                fm = getSupportFragmentManager();
                ft = fm.beginTransaction();
                ft.replace(R.id.frame, fragment, null);
                ft.commit();
                break;

        }
    }


}