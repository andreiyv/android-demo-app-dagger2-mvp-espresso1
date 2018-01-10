package com.test.xyz.daggersample1.ui.activity;

import com.test.xyz.daggersample1.ui.presenter.OnInfoCompletedListener;

public interface MainView extends OnInfoCompletedListener {
    public String getUserNameText();
    public String getCityText();

    public void showUserNameError(int messageId);
    public void showCityNameError(int messageId);

    public void showBusyIndicator();
    public void hideBusyIndicator();

    public void showResult(String result);
}
