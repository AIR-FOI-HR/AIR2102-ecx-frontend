package com.ecxfoi.wbl.wienerbergerfrontend.di.providers;

import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.MaterialBalanceFragmentModule;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.materialbalance.MaterialBalanceFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MaterialBalanceFragmentProvider
{
    @ContributesAndroidInjector(modules = MaterialBalanceFragmentModule.class)
    abstract MaterialBalanceFragment provideMaterialBalanceFragmentFactory();
}
