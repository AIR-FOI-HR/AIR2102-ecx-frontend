package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.di.providers.HomeFragmentProvider;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.home.HomeViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeFragmentModule
{
    @Provides
    HomeViewModel homeViewModel()
    {
        return new HomeViewModel();
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(HomeFragmentProvider viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}
