package com.ecxfoi.wbl.wienerbergerfrontend.di;

import android.app.Application;

import com.ecxfoi.wbl.wienerbergerfrontend.App;
import com.ecxfoi.wbl.wienerbergerfrontend.di.api.RetrofitModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.DaggerApplication;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {AndroidSupportInjectionModule.class,
        AppModule.class,
        ActivityBuilder.class,
        RetrofitModule.class,
        ServiceBuilder.class})
public interface AppComponent extends AndroidInjector<DaggerApplication>
{
    void inject(App application);

    @Component.Builder
    interface Builder
    {
        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
