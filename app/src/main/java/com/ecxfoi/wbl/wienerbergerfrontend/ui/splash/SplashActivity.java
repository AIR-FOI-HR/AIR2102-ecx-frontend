package com.ecxfoi.wbl.wienerbergerfrontend.ui.splash;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.login.LoginActivity;

import javax.inject.Inject;

public class SplashActivity extends BaseActivity<SplashViewModel>
{
    SplashViewModel viewModel;
    @Inject
    ViewModelProvider.Factory factory;

    @Override
    public SplashViewModel getViewModel()
    {
        viewModel = new ViewModelProvider(this, factory).get(SplashViewModel.class);
        return viewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_splash); // show splash screen with Wienerberger logo

        new Handler().postDelayed(() -> {
            Intent mainIntent = new Intent(SplashActivity.this, LoginActivity.class);
            SplashActivity.this.startActivity(mainIntent);
            SplashActivity.this.finish();
        }, 600);
    }
}