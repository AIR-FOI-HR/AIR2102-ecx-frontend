package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.ui.companyselection.CompanySelectionViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.login.LoginViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public class LoginActivityModule
{
    @Provides
    LoginViewModel provideLoginViewModel()
    {
        return new LoginViewModel();
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(LoginViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}
