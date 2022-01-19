package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.api.SelectedCompanyData;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.MaterialBalanceRepository;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.materialbalance.MaterialBalanceViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.myaccount.MyAccountViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class MaterialBalanceFragmentModule
{
    @Provides
    MaterialBalanceViewModel provideMaterialBalanceViewModel(MaterialBalanceRepository materialBalanceRepository, SelectedCompanyData selectedCompanyData)
    {
        return new MaterialBalanceViewModel(materialBalanceRepository, selectedCompanyData);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(MaterialBalanceViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}
