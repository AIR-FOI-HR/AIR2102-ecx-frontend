package com.ecxfoi.wbl.wienerbergerfrontend.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.ecxfoi.wbl.wienerbergerfrontend.api.APIService;
import com.ecxfoi.wbl.wienerbergerfrontend.models.UserData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class UserRepository
{
    private final APIService apiService;

    @Inject
    public UserRepository(APIService apiService)
    {
        this.apiService = apiService;
    }

    public LiveData<UserData> getCurrentUserData()
    {
        final MutableLiveData<UserData> userData = new MutableLiveData<>();

        apiService.getCurrentUserData().enqueue(new Callback<WienerbergerResponse<UserData>>()
        {
            @Override
            public void onResponse(final Call<WienerbergerResponse<UserData>> call, final Response<WienerbergerResponse<UserData>> response)
            {
                userData.setValue(response.body().getData());
            }

            @Override
            public void onFailure(final Call<WienerbergerResponse<UserData>> call, final Throwable t)
            {

            }
        });

        return userData;
    }

    public Call<WienerbergerResponse<UserData>> setCurrentUserData(UserData userData)
    {
        return apiService.updateCurrentUserData(userData);
    }
}
