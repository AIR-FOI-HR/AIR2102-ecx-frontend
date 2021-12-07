package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.ui.companyselection.CompanySelectionViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class CompanySelectionActivityModule
{
    @Provides
    CompanySelectionViewModel provideCompanySelectionViewModel()
    {
        return new CompanySelectionViewModel();
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(CompanySelectionViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}