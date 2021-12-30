package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.models.TicketData;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.TicketsRepository;

import java.util.ArrayList;

import javax.inject.Inject;

public class SupportTicketsViewModel extends ViewModel
{
    private final TicketsRepository ticketsRepository;

    private final ObservableField<ArrayList<TicketData>> ticketsData;

    @Inject
    public SupportTicketsViewModel(final TicketsRepository ticketsRepository)
    {
        this.ticketsRepository = ticketsRepository;
        ticketsData = new ObservableField<>();
    }

    public ObservableField<ArrayList<TicketData>> getTicketsData()
    {
        return ticketsData;
    }

    public LiveData<ArrayList<TicketData>> fetchTicketData()
    {
        return ticketsRepository.getAllTickets();
    }

    public void setTicketsData(final ArrayList<TicketData> ticketsData)
    {
        this.ticketsData.set(ticketsData);
    }
}
