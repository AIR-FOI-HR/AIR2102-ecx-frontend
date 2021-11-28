package com.ecxfoi.wbl.wienerbergerfrontend.auth;

import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponse;

import retrofit2.Response;

public interface AuthenticationInterface
{
    void interpretResponse(String email, String password, Response<WienerbergerResponse<AuthenticationData>> response);

    void interpretError();
}
