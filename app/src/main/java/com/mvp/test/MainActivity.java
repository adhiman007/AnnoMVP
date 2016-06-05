package com.mvp.test;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mvp.test.mvp.MainPresenter;
import com.mvp.test.mvp.MainView;
import com.mvp.test.mvp.impl.MainPresenterImpl;
import com.mvp.test.pojo.User;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MainView {
    private MainPresenter presenter;
    private EditText editUserName;
    private EditText editPassword;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editUserName = (EditText) findViewById(R.id.edit_username);
        editPassword = (EditText) findViewById(R.id.edit_password);
        findViewById(R.id.btn_login).setOnClickListener(this);
        dialog = new ProgressDialog(this);
        dialog.setMessage("Please wait...");
        dialog.setCancelable(false);
        presenter = new MainPresenterImpl(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                User user = new User();
                user.setUserName(editUserName.getText().toString());
                user.setPassword(editPassword.getText().toString());
                presenter.login(user);
                break;
        }
    }

    @Override
    public void onUserLogin() {
        Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUserNameError(int resId) {
        editUserName.setError(getResources().getString(resId));
        editUserName.requestFocus();
    }

    @Override
    public void onPasswordError(int resId) {
        editPassword.setError(getResources().getString(resId));
        editPassword.requestFocus();
    }

    @Override
    public void showDialog() {
        dialog.show();
    }

    @Override
    public void hideDialog() {
        dialog.dismiss();
    }
}