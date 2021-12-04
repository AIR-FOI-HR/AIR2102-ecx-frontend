package com.ecxfoi.wbl.wienerbergerfrontend.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecxfoi.wbl.wienerbergerfrontend.api.APIService;
import com.ecxfoi.wbl.wienerbergerfrontend.models.CompanyData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class CompanyRepository
{
    private APIService apiService;

    @Inject
    public CompanyRepository(APIService apiService) {
        this.apiService = apiService;
    }

    public LiveData<ArrayList<CompanyData>> getAllCompanies() {
        final MutableLiveData<ArrayList<CompanyData>> companyData = new MutableLiveData<>();

        apiService.getAllCompanies().enqueue(new Callback<WienerbergerResponse<ArrayList<CompanyData>>>() {
            @Override
            public void onResponse(final Call<WienerbergerResponse<ArrayList<CompanyData>>> call, final Response<WienerbergerResponse<ArrayList<CompanyData>>> response)
            {
                companyData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(final Call<WienerbergerResponse<ArrayList<CompanyData>>> call, final Throwable t)
            {

            }
        });

        return companyData;
    }
}
