package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.FragmentSupportTicketsBinding;
import com.ecxfoi.wbl.wienerbergerfrontend.models.TicketData;

import java.util.ArrayList;

import javax.inject.Inject;

public class SupportTicketsFragment extends BaseFragment<SupportTicketsViewModel>
{
    @Inject
    ViewModelProvider.Factory factory;
    private FragmentSupportTicketsBinding binding;
    private SupportTicketsViewModel viewModel;

    @Override
    public SupportTicketsViewModel getViewModel()
    {
        viewModel = new ViewModelProvider(this, factory).get(SupportTicketsViewModel.class);
        return viewModel;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState)
    {
        binding = FragmentSupportTicketsBinding.inflate(inflater, container, false);

        final Button buttonCreate = binding.btnCreate;

        final Activity allTicketsActivity = getActivity();
        if (allTicketsActivity != null)
        {
            buttonCreate.setOnClickListener(v -> Navigation.findNavController(allTicketsActivity, R.id.nav_host_fragment).navigate(R.id.action_supportTicketsFragment_to_createSupportTicketsFragment));
        }

        viewModel.fetchTicketData().observe(getViewLifecycleOwner(), this::setTicketsData);

        return binding.getRoot();
    }

    private void setTicketsData(final ArrayList<TicketData> ticketData)
    {
        viewModel.setTicketsData(ticketData);
        binding.listView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.listView.setAdapter(new RecyclerAdapter(ticketData));
    }
}