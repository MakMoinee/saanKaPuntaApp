package com.rizaltechnology.saankapuntaapp;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.auth.User;
import com.rizaltechnology.saankapuntaapp.Interfaces.FragmentFinish;
import com.rizaltechnology.saankapuntaapp.Models.Users;

public class SecondFragment extends Fragment {

    private Context mContext;
    private Button btnLogin;
    private EditText editEmail, editPassword;
    private FragmentFinish fn;
    private TextView txtCreateAccount;

    public SecondFragment(Context context, FragmentFinish finish) {
        mContext = context;
        fn = finish;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View mView = LayoutInflater.from(mContext).inflate(R.layout.fragment_second, container, false);
        initViews(mView);
        initListener();
        return mView;
    }

    private void initListener() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editEmail.getText().toString().equals("") || editPassword.getText().toString().equals("")) {
                    Toast.makeText(mContext, "Please don't leave empty fields", Toast.LENGTH_SHORT).show();
                } else {
                    Users users = new Users();
                    users.setEmail(editEmail.getText().toString());
                    users.setPassword(editPassword.getText().toString());


                }
            }
        });
        txtCreateAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fn.onFinishSecondFragment();
            }
        });
    }

    private void initViews(View mView) {
        btnLogin = mView.findViewById(R.id.btnLogin);
        editEmail = mView.findViewById(R.id.editEmail);
        editPassword = mView.findViewById(R.id.editPassword);
        txtCreateAccount = mView.findViewById(R.id.txtCreateAccount);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}