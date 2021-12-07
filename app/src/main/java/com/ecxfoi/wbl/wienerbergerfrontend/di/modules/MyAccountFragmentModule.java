package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.myaccount.MyAccountViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class MyAccountFragmentModule
{
    @Provides
    MyAccountViewModel myAccountViewModel()
    {
        return new MyAccountViewModel();
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(MyAccountViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}
