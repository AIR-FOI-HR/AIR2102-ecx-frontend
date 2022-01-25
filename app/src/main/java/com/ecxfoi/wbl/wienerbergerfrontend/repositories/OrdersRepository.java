package com.ecxfoi.wbl.wienerbergerfrontend.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecxfoi.wbl.wienerbergerfrontend.api.APIService;
import com.ecxfoi.wbl.wienerbergerfrontend.models.OrderData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.OrderDetailsData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrdersRepository
{
    private final APIService apiService;

    @Inject
    public OrdersRepository(APIService apiService)
    {
        this.apiService = apiService;
    }

    public LiveData<ArrayList<OrderData>> getAllOrders(Long companyId)
    {
        final MutableLiveData<ArrayList<OrderData>> orderData = new MutableLiveData<>();

        apiService.getAllOrders(companyId).enqueue(new Callback<WienerbergerResponse<List<OrderData>>>()
        {
            @Override
            public void onResponse(final Call<WienerbergerResponse<List<OrderData>>> call, final Response<WienerbergerResponse<List<OrderData>>> response)
            {
                orderData.setValue((ArrayList<OrderData>) response.body().getData());
            }

            @Override
            public void onFailure(final Call<WienerbergerResponse<List<OrderData>>> call, final Throwable t)
            {

            }
        });

        return orderData;
    }

    public LiveData<OrderDetailsData> getOrderData(Long orderId)
    {
        final MutableLiveData<OrderDetailsData> orderData = new MutableLiveData<>();

        apiService.getSingleOrder(orderId).enqueue(new Callback<WienerbergerResponse<OrderDetailsData>>()
        {
            @Override
            public void onResponse(final Call<WienerbergerResponse<OrderDetailsData>> call, final Response<WienerbergerResponse<OrderDetailsData>> response)
            {
                orderData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(final Call<WienerbergerResponse<OrderDetailsData>> call, final Throwable t)
            {

            }
        });

        return orderData;
    }
}
