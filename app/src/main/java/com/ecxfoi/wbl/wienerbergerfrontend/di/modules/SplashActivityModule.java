package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.MainActivityViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.splash.SplashViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class SplashActivityModule
{
    @Provides
    MainActivityViewModel provideMainActivityViewModel()
    {
        return new MainActivityViewModel();
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(SplashViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}
