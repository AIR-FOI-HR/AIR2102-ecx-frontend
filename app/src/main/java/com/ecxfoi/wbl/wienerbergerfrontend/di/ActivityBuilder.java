package com.ecxfoi.wbl.wienerbergerfrontend.di;

import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.CompanySelectionActivityModule;
import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.CustomersFragmentModule;
import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.LoginActivityModule;
import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.MainActivityModule;
import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.MyAccountFragmentModule;
import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.SplashActivityModule;
import com.ecxfoi.wbl.wienerbergerfrontend.di.providers.CustomersFragmentProvider;
import com.ecxfoi.wbl.wienerbergerfrontend.di.providers.DeliveryNotesFragmentProvider;
import com.ecxfoi.wbl.wienerbergerfrontend.di.providers.HomeFragmentProvider;
import com.ecxfoi.wbl.wienerbergerfrontend.di.providers.MyAccountFragmentProvider;
import com.ecxfoi.wbl.wienerbergerfrontend.di.providers.OrdersFragmentProvider;
import com.ecxfoi.wbl.wienerbergerfrontend.di.providers.SettingsFragmentProvider;
import com.ecxfoi.wbl.wienerbergerfrontend.di.providers.SupportTicketsFragmentProvider;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.companyselection.CompanySelectionActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.login.LoginActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.MainActivity;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.settings.SettingsFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets.SupportTicketsFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder
{
    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity contributeLoginActivity();

    @ContributesAndroidInjector(modules = CompanySelectionActivityModule.class)
    abstract CompanySelectionActivity contributeCompanySelectionActivity();

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity contributeSplashActivityModule();

    //TODO: consider mashing up the first three activites into one and converting each into fragments

    @ContributesAndroidInjector(modules = {
            MainActivityModule.class,
            MyAccountFragmentProvider.class,
            CustomersFragmentProvider.class,
            DeliveryNotesFragmentProvider.class,
            HomeFragmentProvider.class,
            OrdersFragmentProvider.class,
            SettingsFragmentProvider.class,
            SupportTicketsFragmentProvider.class
            })
    abstract MainActivity contributeMainActivity();
}
