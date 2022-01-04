package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.models.TicketData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;
import com.ecxfoi.wbl.wienerbergerfrontend.repositories.TicketsRepository;

import org.apache.commons.lang.StringUtils;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewTicketViewModel extends ViewModel
{
    private final TicketsRepository ticketsRepository;

    public ObservableField<Integer> infoMessage;
    public ObservableField<Boolean> isError;
    public ObservableField<TicketData> newTicket;

    @Inject
    public NewTicketViewModel(final TicketsRepository ticketsRepository)
    {
        this.ticketsRepository = ticketsRepository;
        newTicket = new ObservableField<>(new TicketData());
        infoMessage = new ObservableField<>(R.string.empty);
        isError = new ObservableField<>(false);
    }

    public void createNewTicket()
    {
        TicketData newTicketData = this.newTicket.get();

        if (newTicketData != null && !validateInput(newTicketData))
        {
            isError.set(true);
            return;
        }

        ticketsRepository.updateTicket(newTicketData).enqueue(new Callback<WienerbergerResponse<TicketData>>()
        {
            @Override
            public void onResponse(final Call<WienerbergerResponse<TicketData>> call, final Response<WienerbergerResponse<TicketData>> response)
            {
                isError.set(false);
                infoMessage.set(R.string.ticket_created);
                newTicket.set(new TicketData());
            }

            @Override
            public void onFailure(final Call<WienerbergerResponse<TicketData>> call, final Throwable t)
            {
                infoMessage.set(R.string.error_no_connection);
                isError.set(true);
                call.cancel();
            }
        });
    }

    private boolean validateInput(final TicketData newTicketData)
    {
        infoMessage.set(R.string.empty);

        if (StringUtils.length(newTicketData.getSubject()) < 2)
        {
            infoMessage.set(R.string.subject_input_error);
            return false;
        }
        if (StringUtils.length(newTicketData.getMessage()) < 5)
        {
            infoMessage.set(R.string.message_input_error);
            return false;
        }

        return true;
    }
}