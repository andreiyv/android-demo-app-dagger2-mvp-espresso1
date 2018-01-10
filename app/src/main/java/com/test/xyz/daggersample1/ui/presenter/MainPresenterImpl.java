package com.test.xyz.daggersample1.ui.presenter;

import com.test.xyz.daggersample1.R;
import com.test.xyz.daggersample1.interactor.MainInteractor;
import com.test.xyz.daggersample1.ui.activity.MainView;

import javax.inject.Inject;

public class MainPresenterImpl implements MainPresenter {
    private MainView mainView;
    private MainInteractor mainInteractor;

    @Inject
    public MainPresenterImpl(MainView mainView, MainInteractor mainInteractor) {
        this.mainView = mainView;
        this.mainInteractor = mainInteractor;
    }

    @Override
    public void requestInformation() {
        mainView.showBusyIndicator();
        mainInteractor.getInformation(mainView.getUserNameText(), mainView.getCityText(), mainView);
    }
}
