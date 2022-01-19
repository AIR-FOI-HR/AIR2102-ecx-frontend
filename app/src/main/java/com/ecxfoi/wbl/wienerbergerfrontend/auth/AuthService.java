package com.ecxfoi.wbl.wienerbergerfrontend.auth;

import android.content.Context;

import com.ecxfoi.wbl.wienerbergerfrontend.api.APIService;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;
import com.ecxfoi.wbl.wienerbergerfrontend.utils.SettingsManager;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthService
{
    private static APIService apiService;

    @Inject
    public AuthService(APIService _apiService)
    {
        apiService = _apiService;
    }

    public static AuthenticationInterface authenticationInterface;

    public static void createLoginRequest(String email, String password)
    {
        AuthenticationRequest request = new AuthenticationRequest(password, email);

        Call<WienerbergerResponse<AuthenticationData>> call = apiService.loginUser(request);

        call.enqueue(new Callback<WienerbergerResponse<AuthenticationData>>()
        {
            @Override
            public void onResponse(final Call<WienerbergerResponse<AuthenticationData>> call, final Response<WienerbergerResponse<AuthenticationData>> response)
            {
                authenticationInterface.interpretResponse(email, password, response);
            }

            @Override
            public void onFailure(final Call<WienerbergerResponse<AuthenticationData>> call, final Throwable t)
            {
                call.cancel();
                authenticationInterface.interpretError();
            }
        });
    }

    public static void setEmail(String email, Context context)
    {
        SettingsManager.changeParameter("email", email, context);
    }

    public static void setPassword(String password, Context context)
    {
        SettingsManager.changeParameter("password", password, context);
    }

    public static String getEmail(Context context)
    {
        return SettingsManager.getParameter("email", context);
    }

    public static String getPassword(Context context)
    {
        return SettingsManager.getParameter("password", context);
    }
}