package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.home;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.deliverynotes.DeliveryNotesViewModel;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment<HomeViewModel>
{
    @Inject
    private ViewModelProvider.Factory factory;

    private HomeViewModel viewModel;

    @Override
    public HomeViewModel getViewModel() {
        viewModel = new ViewModelProvider(this, factory).get(HomeViewModel.class);
        return viewModel;
    }
}