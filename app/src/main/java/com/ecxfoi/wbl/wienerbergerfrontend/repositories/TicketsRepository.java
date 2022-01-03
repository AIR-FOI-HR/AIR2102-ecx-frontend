package com.ecxfoi.wbl.wienerbergerfrontend.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecxfoi.wbl.wienerbergerfrontend.api.APIService;
import com.ecxfoi.wbl.wienerbergerfrontend.models.TicketData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class TicketsRepository
{
    private final APIService apiService;

    @Inject
    public TicketsRepository(APIService apiService)
    {
        this.apiService = apiService;
    }

    public LiveData<TicketData> getSingleTicket(Long ticketId)
    {
        final MutableLiveData<TicketData> ticketData = new MutableLiveData<>();

        apiService.getSingleTicket(ticketId).enqueue(new Callback<WienerbergerResponse<TicketData>>()
        {
            @Override
            public void onResponse(final Call<WienerbergerResponse<TicketData>> call, final Response<WienerbergerResponse<TicketData>> response)
            {
                if (response.isSuccessful())
                {
                    ticketData.setValue(response.body().getData());
                }
            }

            @Override
            public void onFailure(final Call<WienerbergerResponse<TicketData>> call, final Throwable t)
            {

            }
        });

        return ticketData;
    }

    public LiveData<ArrayList<TicketData>> getAllTickets()
    {
        final MutableLiveData<ArrayList<TicketData>> ticketData = new MutableLiveData<>();

        apiService.getAllTickets().enqueue(new Callback<WienerbergerResponse<List<TicketData>>>()
        {
            @Override
            public void onResponse(final Call<WienerbergerResponse<List<TicketData>>> call, final Response<WienerbergerResponse<List<TicketData>>> response)
            {
                ticketData.setValue((ArrayList<TicketData>) response.body().getData());
            }

            @Override
            public void onFailure(final Call<WienerbergerResponse<List<TicketData>>> call, final Throwable t)
            {

            }
        });

        return ticketData;
    }

    public Call<WienerbergerResponse<TicketData>> updateTicket(TicketData ticketData)
    {
        return apiService.createSingleTicket(ticketData);
    }
}