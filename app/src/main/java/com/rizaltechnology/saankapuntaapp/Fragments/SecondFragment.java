package com.rizaltechnology.saankapuntaapp.Fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
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
    private TextInputEditText editSecret, editPW, editConfirmPW, editForgotEmail;
    private TextInputLayout layoutPW, layoutConfirmPW;
    private Button btnSave;
    private FragmentFinish fn;
    private TextView txtCreateAccount, txtForgotPassword;
    private LocalFireStore fs;
    private ProgressDialog pd;
    private AlertDialog fpAlertDialog;
    private Boolean showPW = true;

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
                            Log.e("LOGIN_ERR", e.getMessage());
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
        txtForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(mContext);
                View tView = LayoutInflater.from(mContext).inflate(R.layout.dialog_forgot_pass, null, false);
                initForgotViews(tView);
                initForgotListeners(tView);
                mBuilder.setView(tView);
                fpAlertDialog = mBuilder.create();
                fpAlertDialog.show();
            }
        });
    }

    private void initForgotListeners(View tView) {
        layoutPW.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showPW) {
                    layoutPW.setEndIconDrawable(R.drawable.ic_eye_off);
                    editPW.setInputType(InputType.TYPE_CLASS_TEXT);
                    showPW = false;
                } else {
                    layoutPW.setEndIconDrawable(R.drawable.ic_eye);
                    editPW.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    showPW = true;
                }
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editSecret.getText().toString().equals("") ||
                        editPW.getText().toString().equals("") ||
                        editConfirmPW.getText().toString().equals("") ||
                        editForgotEmail.getText().toString().equals("")) {
                    Toast.makeText(mContext, "Please Don't Leave Empty Fields", Toast.LENGTH_SHORT).show();
                } else {
                    if (editPW.getText().toString().equals(editConfirmPW.getText().toString())) {
                        pd.show();
                        Users mUser = new Users();
                        mUser.setEmail(editForgotEmail.getText().toString());
                        mUser.setPassword(editPW.getText().toString());
                        mUser.setSecret(editSecret.getText().toString());
                        fs.forgotPassword(mUser, new FireStoreListener() {
                            @Override
                            public void onAddUserSuccess(Users users) {
                                pd.dismiss();
                                fpAlertDialog.dismiss();
                                Toast.makeText(mContext, "Successfully Reset Password", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onAddUserError(Exception e) {
                                pd.dismiss();
                                Toast.makeText(mContext, "Failed to Reset Password", Toast.LENGTH_SHORT).show();
                                Log.e("FORGOT_PASSW_ERR", e.getMessage().toString());
                            }
                        });
                    } else {
                        Toast.makeText(mContext, "Password Doesn't Match", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private void initForgotViews(View tView) {
        editSecret = tView.findViewById(R.id.editSecret);
        editPW = tView.findViewById(R.id.editPassword);
        editConfirmPW = tView.findViewById(R.id.editConfirmPassword);
        btnSave = tView.findViewById(R.id.btnSave);
        editForgotEmail = tView.findViewById(R.id.editForgotEmail);
        layoutPW = tView.findViewById(R.id.layoutPassword);
        layoutConfirmPW = tView.findViewById(R.id.layoutConfirmPassword);

    }

    private void initViews(View mView) {
        btnLogin = mView.findViewById(R.id.btnLogin);
        editEmail = mView.findViewById(R.id.editEmail);
        editPassword = mView.findViewById(R.id.editPassword);
        txtCreateAccount = mView.findViewById(R.id.txtCreateAccount);
        txtForgotPassword = mView.findViewById(R.id.txtForgotPassword);
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