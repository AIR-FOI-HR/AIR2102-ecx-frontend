package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.api.SelectedCompanyData;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.OrdersRepository;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.orders.OrdersViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class OrdersFragmentModule
{
    @Provides
    OrdersViewModel ordersViewModel(OrdersRepository ordersRepository, SelectedCompanyData selectedCompanyData)
    {
        return new OrdersViewModel(ordersRepository, selectedCompanyData);
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(OrdersViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}
