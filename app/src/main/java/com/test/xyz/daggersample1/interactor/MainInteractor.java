package com.test.xyz.daggersample1.interactor;

import com.test.xyz.daggersample1.ui.presenter.OnInfoCompletedListener;

public interface MainInteractor {
    public void getInformation(String userName, String cityName, final OnInfoCompletedListener listener);
}
