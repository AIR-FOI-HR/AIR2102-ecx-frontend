package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ecxfoi.wbl.wienerbergerfrontend.R;

public class TicketDetailsFragment extends Fragment
{

    private TicketDetailsViewModel mViewModel;

    public static TicketDetailsFragment newInstance()
    {
        return new TicketDetailsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.ticket_details_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(TicketDetailsViewModel.class);
        // TODO: Use the ViewModel
    }

}