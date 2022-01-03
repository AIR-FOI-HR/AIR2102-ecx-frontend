package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.models.TicketData;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.TicketsRepository;

import javax.inject.Inject;

public class TicketDetailsViewModel extends ViewModel
{
    private final TicketsRepository ticketsRepository;
    private final ObservableField<TicketData> ticketData;

    public ObservableField<TicketData> getTicketData()
    {
        return ticketData;
    }

    @Inject
    public TicketDetailsViewModel(final TicketsRepository ticketsRepository)
    {
        this.ticketsRepository = ticketsRepository;
        ticketData = new ObservableField<>();
    }

    public LiveData<TicketData> fetchSingleTicketData(Long id)
    {
        return ticketsRepository.getSingleTicket(id);
    }

    public void setTicketData(final TicketData ticketData)
    {
        this.ticketData.set(ticketData);
    }
}