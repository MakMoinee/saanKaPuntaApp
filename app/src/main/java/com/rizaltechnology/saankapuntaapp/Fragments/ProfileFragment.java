package com.rizaltechnology.saankapuntaapp.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.rizaltechnology.saankapuntaapp.R;

public class ProfileFragment extends Fragment {

    Context mContext;


    public ProfileFragment(Context context) {
        this.mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.activity_profile,container,false);
        return mView;
    }
}
