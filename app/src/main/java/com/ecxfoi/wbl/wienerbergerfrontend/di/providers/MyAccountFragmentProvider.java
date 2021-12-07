package com.ecxfoi.wbl.wienerbergerfrontend.di.providers;

import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.MyAccountFragmentModule;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.myaccount.MyAccountFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MyAccountFragmentProvider
{
    @ContributesAndroidInjector(modules = MyAccountFragmentModule.class)
    abstract MyAccountFragment provideMyAccountFragmentFactory();
}
