package com.ecxfoi.wbl.wienerbergerfrontend.di.providers;

import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.OrdersFragmentModule;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.orders.OrdersFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class OrdersFragmentProvider
{
    @ContributesAndroidInjector(modules = OrdersFragmentModule.class)
    abstract OrdersFragment provideOrdersFragmentFactory();
}
