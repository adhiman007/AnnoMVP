package com.mvp.test.mvp.impl;

import android.os.Handler;
import android.text.TextUtils;

import com.mvp.test.R;
import com.mvp.test.mvp.MainInteractor;
import com.mvp.test.mvp.OnMainInteractedListener;
import com.mvp.test.pojo.User;

/**
 * Created by Abhishek on 6/5/2016.
 */

public class MainInteractorImpl implements MainInteractor {

    @Override
    public void login(User user, final OnMainInteractedListener listener) {
        if (TextUtils.isEmpty(user.getUserName()))
            listener.onUserNameError(R.string.empty_username);
        else if (TextUtils.isEmpty(user.getPassword()))
            listener.onPasswordError(R.string.empty_password);
        else if (user.getPassword().length() <= 6)
            listener.onPasswordError(R.string.min_password);
        else {
            listener.showDialog();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    listener.hideDialog();
                    listener.onUserLogin();
                }
            }, 5000);
        }
    }
}