package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.models.TicketData;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.TicketsRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class SupportTicketsViewModel extends ViewModel
{
    private final TicketsRepository ticketsRepository;

    @Inject
    public SupportTicketsViewModel(final TicketsRepository ticketsRepository)
    {
        this.ticketsRepository = ticketsRepository;
    }

    public LiveData<ArrayList<TicketData>> fetchTicketData()
    {
        return ticketsRepository.getAllTickets();
    }
}