package com.ecxfoi.wbl.wienerbergerfrontend.api;

import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthenticationData;
import com.ecxfoi.wbl.wienerbergerfrontend.auth.AuthenticationRequest;
import com.ecxfoi.wbl.wienerbergerfrontend.models.CompanyData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.DeliveryNoteData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.OrderData;
import com.ecxfoi.wbl.wienerbergerfrontend.models.OrderDetailsData;
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
import retrofit2.http.Query;

public interface APIService
{
    @POST(APIRoutes.BASE_URL + APIRoutes.ROUTE_LOGIN)
    Call<WienerbergerResponse<AuthenticationData>> loginUser(@Body AuthenticationRequest user);

    @GET(APIRoutes.BASE_URL + APIRoutes.ROUTE_COMPANIES)
    Call<WienerbergerResponse<ArrayList<CompanyData>>> getAllCompanies();

    @GET(APIRoutes.BASE_URL + APIRoutes.ROUTE_USERS)
    Call<WienerbergerResponse<UserData>> getCurrentUserData();

    @PUT(APIRoutes.BASE_URL + APIRoutes.ROUTE_USERS)
    Call<WienerbergerResponse<UserData>> updateCurrentUserData(@Body UserData userData);

    @GET(APIRoutes.BASE_URL + APIRoutes.ROUTE_TICKETS + "/{id}")
    Call<WienerbergerResponse<TicketData>> getSingleTicket(@Path("id") Long id);

    @GET(APIRoutes.BASE_URL + APIRoutes.ROUTE_TICKETS)
    Call<WienerbergerResponse<List<TicketData>>> getAllTickets();

    @POST(APIRoutes.BASE_URL + APIRoutes.ROUTE_TICKETS)
    Call<WienerbergerResponse<TicketData>> createSingleTicket(@Body TicketData ticket);

    @GET(APIRoutes.BASE_URL + APIRoutes.ROUTE_ORDERS + "/{companyId}")
    Call<WienerbergerResponse<List<OrderData>>> getAllOrders(@Path("companyId") Long companyId);

    @GET(APIRoutes.BASE_URL + APIRoutes.ROUTE_ORDER_DETAILS + "/{orderId}")
    Call<WienerbergerResponse<OrderDetailsData>> getSingleOrder(@Path("orderId") Long orderId);

    @GET(APIRoutes.BASE_URL + APIRoutes.ROUTE_DELIVERY_NOTES + "/dates")
    Call<WienerbergerResponse<List<DeliveryNoteData>>> getDeliveryNotesByDates(@Query("from") String from, @Query("to") String to);
}
