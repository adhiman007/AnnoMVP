package com.mvp.test.mvp.impl;

import com.mvp.test.mvp.MainInteractor;
import com.mvp.test.mvp.MainPresenter;
import com.mvp.test.mvp.MainView;
import com.mvp.test.mvp.OnMainInteractedListener;
import com.mvp.test.pojo.User;

/**
 * Created by Abhishek on 6/5/2016.
 */

public class MainPresenterImpl implements MainPresenter, OnMainInteractedListener {
    private MainView view;
    private MainInteractor interactor;

    public MainPresenterImpl(MainView view) {
        this.view = view;
        interactor = new MainInteractorImpl();
    }

    @Override
    public void login(User user) {
        interactor.login(user, this);
    }

    @Override
    public void onUserLogin() {
        view.onUserLogin();
    }

    @Override
    public void onUserNameError(int resId) {
        view.onUserNameError(resId);
    }

    @Override
    public void onPasswordError(int resId) {
        view.onPasswordError(resId);
    }

    @Override
    public void showDialog() {
        view.showDialog();
    }

    @Override
    public void hideDialog() {
        view.hideDialog();
    }
}