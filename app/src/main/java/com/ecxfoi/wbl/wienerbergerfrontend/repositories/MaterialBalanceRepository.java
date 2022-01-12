package com.ecxfoi.wbl.wienerbergerfrontend.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecxfoi.wbl.wienerbergerfrontend.api.APIService;
import com.ecxfoi.wbl.wienerbergerfrontend.models.MaterialBalanceData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MaterialBalanceRepository
{
    private final APIService apiService;

    @Inject
    public MaterialBalanceRepository(APIService apiService)
    {
        this.apiService = apiService;
    }

    public LiveData<ArrayList<MaterialBalanceData>> getMaterialBalance(Long companyId)
    {
        final MutableLiveData<ArrayList<MaterialBalanceData>> orderData = new MutableLiveData<>();

        apiService.getMaterialBalance(companyId).enqueue(new Callback<WienerbergerResponse<List<MaterialBalanceData>>>()
        {
            @Override
            public void onResponse(final Call<WienerbergerResponse<List<MaterialBalanceData>>> call, final Response<WienerbergerResponse<List<MaterialBalanceData>>> response)
            {
                orderData.setValue((ArrayList<MaterialBalanceData>) response.body().getData());
            }

            @Override
            public void onFailure(final Call<WienerbergerResponse<List<MaterialBalanceData>>> call, final Throwable t)
            {

            }
        });

        return orderData;
    }

}
