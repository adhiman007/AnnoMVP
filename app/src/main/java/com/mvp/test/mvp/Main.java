package com.mvp.test.mvp;

import com.mvp.MVP;
import com.mvp.Presenter;
import com.mvp.View;
import com.mvp.test.pojo.User;

/**
 * Created by Abhishek on 6/5/2016.
 */

@MVP
public interface Main {
    @Presenter
    void login(User user);

    @View
    void onUserLogin();

    @View
    void onUserNameError(int resId);

    @View
    void onPasswordError(int resId);

    @View
    void showDialog();

    @View
    void hideDialog();
}
