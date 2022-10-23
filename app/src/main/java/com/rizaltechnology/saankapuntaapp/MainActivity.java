package com.rizaltechnology.saankapuntaapp;

import android.os.Bundle;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.rizaltechnology.saankapuntaapp.Interfaces.FragmentFinish;

public class MainActivity extends AppCompatActivity implements FragmentFinish {

    private Fragment fragment;
    private FragmentTransaction ft;
    private FragmentManager fm;
    private int fragmentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_main);
        startFragment();
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
        fragment = new CreateAccountFragment(MainActivity.this);
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.frame, fragment, null);
        ft.commit();
    }

    @Override
    public void onBackPressed() {
        switch (fragmentIndex) {
            case 1:
                super.onBackPressed();
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                break;
            case 2:
                onFinishFirstFragment();
                break;

        }
    }
}