package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.deliverynotes;

import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;

import javax.inject.Inject;

public class DeliveryNotesFragment extends BaseFragment<DeliveryNotesViewModel>
{
    @Inject
    ViewModelProvider.Factory factory;

    private DeliveryNotesViewModel viewModel;

    @Override
    public DeliveryNotesViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, factory).get(DeliveryNotesViewModel.class);
        return viewModel;
    }
}