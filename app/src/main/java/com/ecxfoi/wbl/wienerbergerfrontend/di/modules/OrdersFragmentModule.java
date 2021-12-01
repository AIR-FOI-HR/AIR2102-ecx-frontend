package com.ecxfoi.wbl.wienerbergerfrontend.di.modules;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.orders.OrdersViewModel;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

@Module
public class OrdersFragmentModule
{
    @Provides
    OrdersViewModel ordersViewModel()
    {
        return new OrdersViewModel();
    }

    @Provides
    ViewModelProvider.Factory provideViewModelProvider(OrdersViewModel viewModel)
    {
        return new ViewModelProviderFactory<>(viewModel);
    }
}
