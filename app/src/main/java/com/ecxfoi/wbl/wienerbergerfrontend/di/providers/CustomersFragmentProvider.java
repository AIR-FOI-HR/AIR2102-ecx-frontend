package com.ecxfoi.wbl.wienerbergerfrontend.di.providers;

import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.CustomersFragmentModule;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.customers.CustomersFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CustomersFragmentProvider
{
    @ContributesAndroidInjector(modules = CustomersFragmentModule.class)
    abstract CustomersFragment provideCustomersFragmentFactory();
}
