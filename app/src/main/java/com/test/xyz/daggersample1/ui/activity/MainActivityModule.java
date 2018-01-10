package com.test.xyz.daggersample1.ui.activity;

import com.test.xyz.daggersample1.di.ActivityScope;
import com.test.xyz.daggersample1.interactor.MainInteractor;
import com.test.xyz.daggersample1.interactor.MainInteractorImpl;
import com.test.xyz.daggersample1.ui.presenter.MainPresenter;
import com.test.xyz.daggersample1.ui.presenter.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    public final MainView view;

    public MainActivityModule(MainView view) {
        this.view = view;
    }

    @Provides
    @ActivityScope
    MainView provideMainView() {
        return this.view;
    }

    @Provides
    @ActivityScope
    MainInteractor provideMainInteractor(MainInteractorImpl interactor) {
        return interactor;
    }

    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter(MainPresenterImpl presenter) {
        return presenter;
    }
}
