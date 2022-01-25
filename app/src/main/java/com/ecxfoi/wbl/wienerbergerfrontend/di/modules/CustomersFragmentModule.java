package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.customers.CustomersViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class CustomersFragmentModule
{
    @Provides
    CustomersViewModel customersViewModel()
    {
        return new CustomersViewModel();
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(CustomersViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}
