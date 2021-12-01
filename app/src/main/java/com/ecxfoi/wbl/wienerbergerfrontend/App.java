package com.ecxfoi.wbl.wienerbergerfrontend;

import com.ecxfoi.wbl.wienerbergerfrontend.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;

public class App extends DaggerApplication
{
    // NOTE: Dagger generates its own classes, so when you make a change, sometimes it stops compiling before they are regenerated. If that's the case:
    // If the IDE says DaggerAppComponent doesn't exist, comment the first return (and the import for the class) and uncomment the return null
    // Let the project build and Dagger will generate the class
    // Then uncomment the first return and comment the return null
    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector()
    {
        return DaggerAppComponent.builder().application(this).build();
        //return null;
    }
}
