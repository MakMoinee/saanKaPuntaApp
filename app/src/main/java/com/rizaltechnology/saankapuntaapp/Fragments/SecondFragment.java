package com.rizaltechnology.saankapuntaapp.Fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.auth.User;
import com.rizaltechnology.saankapuntaapp.Interfaces.FireStoreListener;
import com.rizaltechnology.saankapuntaapp.Interfaces.FragmentFinish;
import com.rizaltechnology.saankapuntaapp.Models.Users;
import com.rizaltechnology.saankapuntaapp.Preferrences.MyUserPreferrence;
import com.rizaltechnology.saankapuntaapp.R;
import com.rizaltechnology.saankapuntaapp.Services.LocalFireStore;
import com.rizaltechnology.saankapuntaapp.Services.LocalFirestoreImpl;

public class SecondFragment extends Fragment {

    private Context mContext;
    private Button btnLogin;
    private EditText editEmail, editPassword;
    private FragmentFinish fn;
    private TextView txtCreateAccount;
    private LocalFireStore fs;
    private ProgressDialog pd;

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
                    pd.show();
                    Users users = new Users();
                    users.setEmail(editEmail.getText().toString());
                    users.setPassword(editPassword.getText().toString());
                    fs.getLogin(users, new FireStoreListener() {
                        @Override
                        public void onAddUserSuccess(Users users) {
                            pd.dismiss();
                            Toast.makeText(mContext, "Login Successfully", Toast.LENGTH_SHORT).show();
                            new MyUserPreferrence(mContext).saveUser(users);
                            fn.onLoginFinish();
                        }

                        @Override
                        public void onAddUserError(Exception e) {
                            pd.dismiss();
                            Toast.makeText(mContext, "Wrong username or Password", Toast.LENGTH_SHORT).show();
                        }
                    });

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
        fs = new LocalFirestoreImpl(mContext);
        pd = new ProgressDialog(mContext);
        pd.setCancelable(false);
        pd.setMessage("Sending Request ...");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}