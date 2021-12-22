package com.ecxfoi.wbl.wienerbergerfrontend.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.utils.ExitActivity;

import dagger.android.support.DaggerAppCompatActivity;

public abstract class BaseActivity<T extends ViewModel> extends DaggerAppCompatActivity
{
    private T viewModel;

    public abstract T getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        this.viewModel = viewModel == null ? getViewModel() : viewModel;
    }

    @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setTitle("Exit?")
                .setMessage("Do you really want to close Wienerberger Application?")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setPositiveButton(android.R.string.yes, (dialog, whichButton) -> ExitActivity.exitApplication(this))
                .setNegativeButton(android.R.string.no, null).show();
    }
}