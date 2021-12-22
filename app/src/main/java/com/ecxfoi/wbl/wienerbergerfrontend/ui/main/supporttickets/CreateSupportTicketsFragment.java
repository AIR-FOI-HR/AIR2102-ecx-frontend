package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.FragmentCreateNewTicketBinding;

import javax.inject.Inject;

public class CreateSupportTicketsFragment extends BaseFragment<CreateSupportTicketsViewModel>
{
    @Inject
    ViewModelProvider.Factory factory;

    private CreateSupportTicketsViewModel viewModel;
    private FragmentCreateNewTicketBinding binding;

    @Override
    public CreateSupportTicketsViewModel getViewModel()
    {
        viewModel = new ViewModelProvider(this, factory).get(CreateSupportTicketsViewModel.class);
        return viewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        binding = FragmentCreateNewTicketBinding.inflate(inflater, container, false);
        binding.setCreateSupportTicketsViewModel(viewModel);

        return binding.getRoot();
    }
}
