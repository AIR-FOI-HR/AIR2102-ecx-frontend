package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.TicketDetailsFragmentBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.models.TicketData;

import javax.inject.Inject;

public class TicketDetailsFragment extends BaseFragment<TicketDetailsViewModel>
{
    @Inject
    ViewModelProvider.Factory factory;

    private TicketDetailsViewModel viewModel;

    @Override
    public TicketDetailsViewModel getViewModel()
    {
        viewModel = new ViewModelProvider(this, factory).get(TicketDetailsViewModel.class);
        return viewModel;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        final com.ecxfoi.wbl.wienerbergerfrontend.databinding.TicketDetailsFragmentBinding binding = TicketDetailsFragmentBinding.inflate(inflater, container, false);
        binding.setTicketDetailsViewmodel(viewModel);

        if (getArguments() == null)
        {
            Toast.makeText(getContext(), getString(R.string.generic_error_message), Toast.LENGTH_LONG).show();
        }

        Long currentTicketID = getArguments().getLong("ticket");
        viewModel.fetchSingleTicketData(currentTicketID).observe(getViewLifecycleOwner(), this::setTicketData);

        return binding.getRoot();
    }

    public void setTicketData(TicketData ticketData)
    {
        viewModel.setTicketData(ticketData);
    }
}