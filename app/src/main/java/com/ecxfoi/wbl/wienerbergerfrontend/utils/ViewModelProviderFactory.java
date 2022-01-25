package com.ecxfoi.wbl.wienerbergerfrontend.utils;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelProviderFactory<V> implements ViewModelProvider.Factory
{
    private final V viewModel;

    public ViewModelProviderFactory(V viewModel)
    {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    @SuppressWarnings("unchecked")
    public <T extends ViewModel> T create(Class<T> modelClass)
    {
        if (modelClass.isAssignableFrom(viewModel.getClass()))
        {
            return (T) viewModel;
        }
        throw new IllegalArgumentException("Unknown class name");
    }
}