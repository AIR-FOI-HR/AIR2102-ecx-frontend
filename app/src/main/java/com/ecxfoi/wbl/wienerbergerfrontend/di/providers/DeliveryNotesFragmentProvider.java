package com.ecxfoi.wbl.wienerbergerfrontend.di.providers;

import com.ecxfoi.wbl.wienerbergerfrontend.di.modules.DeliveryNotesModule;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.deliverynotes.DeliveryNotesFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class DeliveryNotesFragmentProvider
{
    @ContributesAndroidInjector(modules = DeliveryNotesModule.class)
    abstract DeliveryNotesFragment provideDeliveryNotesFragmentFactory();
}