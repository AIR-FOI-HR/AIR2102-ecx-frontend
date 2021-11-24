package com.ecxfoi.wbl.wienerbergerfrontend.auth;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.ecxfoi.wbl.wienerbergerfrontend.api.APIClient;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthService
{
    public static AuthenticationInterface authenticationInterface;

    public static void createLoginRequest(String email, String password)
    {
        AuthenticationRequest request = new AuthenticationRequest(password, email);

        Call<WienerbergerResponse<AuthenticationData>> call = APIClient.getService().createUser(request);

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

    private static void changeParameter(String parameterName, String parameterValue, Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        if (!preferences.getString(parameterName, "").equals(parameterValue))
        {
            editor.putString(parameterName, parameterValue);
        }
        editor.apply();
    }

    public static void setEmail(String email, Context context)
    {
        changeParameter("email", email, context);
    }

    public static void setPassword(String password, Context context)
    {
        changeParameter("password", password, context);
    }

    public static void setJWT(String jwt, Context context)
    {
        changeParameter("jwt", jwt, context);
    }

    public static String getEmail(Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("email", null);
    }

    public static String getPassword(Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("password", null);
    }

    public static String getJWT(Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("jwt", null);
    }

    public static void logout(Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("jwt", "");
        editor.apply();
    }
}