package com.ecxfoi.wbl.wienerbergerfrontend.api;

import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthenticationData;
import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthenticationRequest;
import com.ecxfoi.wbl.wienerbergerfrontend.models.CompanyData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIService
{
    @POST(APIRoutes.BASE_URL + APIRoutes.ROUTE_LOGIN)
    Call<WienerbergerResponse<AuthenticationData>> createUser(@Body AuthenticationRequest user);

    @GET(APIRoutes.BASE_URL + APIRoutes.ROUTE_COMPANIES)
    Call<WienerbergerResponse<ArrayList<CompanyData>>> getAllCompanies();
}
