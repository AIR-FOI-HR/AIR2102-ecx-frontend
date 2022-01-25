package com.ecxfoi.wbl.wienerbergerfrontend.base;

import android.content.Context;

import androidx.lifecycle.ViewModel;

import dagger.android.support.DaggerFragment;

public abstract class BaseFragment<T extends ViewModel> extends DaggerFragment
{
    @SuppressWarnings("FieldCanBeLocal")
    private T viewModel;

    public abstract T getViewModel();

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        viewModel = getViewModel();
    }
}