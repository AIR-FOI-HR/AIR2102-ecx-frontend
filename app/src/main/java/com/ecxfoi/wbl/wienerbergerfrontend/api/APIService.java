package com.ecxfoi.wbl.wienerbergerfrontend.api;

import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthenticationData;
import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthenticationRequest;
import com.ecxfoi.wbl.wienerbergerfrontend.models.CompanyData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.TicketData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.UserData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface APIService
{
    @POST(APIRoutes.BASE_URL + APIRoutes.ROUTE_LOGIN)
    Call<WienerbergerResponse<AuthenticationData>> createUser(@Body AuthenticationRequest user);

    @GET(APIRoutes.BASE_URL + APIRoutes.ROUTE_COMPANIES)
    Call<WienerbergerResponse<ArrayList<CompanyData>>> getAllCompanies();

    @GET(APIRoutes.BASE_URL + APIRoutes.ROUTE_USERS)
    Call<WienerbergerResponse<UserData>> getCurrentUserData();

    @PUT(APIRoutes.BASE_URL + APIRoutes.ROUTE_USERS)
    Call<WienerbergerResponse<UserData>> updateCurrentUserData(@Body UserData userData);

    @GET(APIRoutes.BASE_URL + APIRoutes.ROUTE_TICKETS + "{id}")
    Call<WienerbergerResponse<TicketData>> getSingleTicket(@Path("id") Long id);

    @GET(APIRoutes.BASE_URL + APIRoutes.ROUTE_TICKETS)
    Call<WienerbergerResponse<List<TicketData>>> getAllTickets();
}
