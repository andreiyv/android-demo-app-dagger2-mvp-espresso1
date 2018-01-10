package com.test.xyz.daggersample1.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.test.xyz.daggersample1.R;
import com.test.xyz.daggersample1.di.DaggerApplication;
import com.test.xyz.daggersample1.ui.presenter.MainPresenter;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements MainView, View.OnClickListener {

    @Inject
    MainPresenter presenter;

    @InjectView(R.id.userNameText)
    EditText userNameText;

    @InjectView(R.id.cityText)
    EditText cityText;

    @InjectView(R.id.btnShowInfo)
    Button showInfoButton;

    @InjectView(R.id.resultView)
    TextView resultView;

    @InjectView(R.id.progress)
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        DaggerApplication.get(this)
                .getAppComponent()
                .plus(new MainActivityModule(this))
                .inject(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        showInfoButton.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnShowInfo) {
            presenter.requestInformation();
        }
    }

    @Override
    public String getUserNameText() {
        return userNameText.getText().toString();
    }

    @Override
    public String getCityText() {
        return cityText.getText().toString();
    }

    @Override
    public void showUserNameError(int messageId) {
        userNameText.setError(getString(messageId));
    }

    @Override
    public void showCityNameError(int messageId) {
        cityText.setError(getString(messageId));
    }

    @Override
    public void showBusyIndicator() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBusyIndicator() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showResult(final String result) {
        resultView.setText(result);
    }

    @Override
    public void onUserNameValidationError(final int messageID) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideBusyIndicator();
                showUserNameError(messageID);
            }
        });
    }

    @Override
    public void onCityValidationError(final int messageID) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideBusyIndicator();
                showCityNameError(messageID);
            }
        });
    }

    @Override
    public void onSuccess(final String data) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideBusyIndicator();
                showResult(data);
            }
        });
    }

    @Override
    public void onFailure(final String errorMessage) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                hideBusyIndicator();
                showResult(errorMessage);
            }
        });
    }
}
