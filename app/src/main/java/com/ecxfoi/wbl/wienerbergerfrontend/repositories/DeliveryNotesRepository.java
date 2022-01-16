package com.ecxfoi.wbl.wienerbergerfrontend.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecxfoi.wbl.wienerbergerfrontend.api.APIService;
import com.ecxfoi.wbl.wienerbergerfrontend.models.DeliveryNoteData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.OrderData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryNotesRepository
{
    private final APIService apiService;

    @Inject
    public DeliveryNotesRepository(APIService apiService)
    {
        this.apiService = apiService;
    }

    public LiveData<ArrayList<DeliveryNoteData>> getDeliverNotesForDateRange(String from, String to)
    {
        final MutableLiveData<ArrayList<DeliveryNoteData>> deliveryNotesData = new MutableLiveData<>();

        apiService.getDeliveryNotesByDates(from, to).enqueue(new Callback<WienerbergerResponse<List<DeliveryNoteData>>>()
        {
            @Override
            public void onResponse(final Call<WienerbergerResponse<List<DeliveryNoteData>>> call, final Response<WienerbergerResponse<List<DeliveryNoteData>>> response)
            {
                ArrayList<DeliveryNoteData> deliveryNoteList = new ArrayList<>();

                if (response.body() != null)
                {
                    deliveryNoteList = ((ArrayList<DeliveryNoteData>) response.body().getData());
                }

                deliveryNotesData.setValue(deliveryNoteList);
            }

            @Override
            public void onFailure(final Call<WienerbergerResponse<List<DeliveryNoteData>>> call, final Throwable t)
            {

            }
        });

        return deliveryNotesData;
    }
}
