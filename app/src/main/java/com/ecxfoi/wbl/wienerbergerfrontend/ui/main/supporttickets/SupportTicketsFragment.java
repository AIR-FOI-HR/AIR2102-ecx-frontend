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

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.base.BaseFragment;
import com.ecxfoi.wbl.wienerbergerfrontend.databinding.FragmentSupportTicketsBinding;

import javax.inject.Inject;

public class SupportTicketsFragment extends BaseFragment<SupportTicketsViewModel>
{
    @Inject
    ViewModelProvider.Factory factory;

    @Override
    public SupportTicketsViewModel getViewModel()
    {
        return new ViewModelProvider(this, factory).get(SupportTicketsViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState)
    {
        final com.ecxfoi.wbl.wienerbergerfrontend.databinding.FragmentSupportTicketsBinding binding = FragmentSupportTicketsBinding.inflate(inflater, container, false);

        final Button buttonCreate = binding.btnCreate;

        final Activity allTicketsActivity = getActivity();
        if (allTicketsActivity != null)
        {
            buttonCreate.setOnClickListener(v -> Navigation.findNavController(allTicketsActivity, R.id.nav_host_fragment).navigate(R.id.action_supportTicketsFragment_to_createSupportTicketsFragment));
        }

        return binding.getRoot();
    }
}