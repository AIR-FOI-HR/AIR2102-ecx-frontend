package com.ecxfoi.wbl.wienerbergerfrontend.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient
{
    private static Retrofit retrofit = null;

    private static Retrofit getRetrofit()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder()
                    .baseUrl(APIRoutes.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static APIService getService()
    {
        return getRetrofit().create(APIService.class);
    }

}