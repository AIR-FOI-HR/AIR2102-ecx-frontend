package com.ecxfoi.wbl.wienerbergerfrontend.data;

import com.ecxfoi.wbl.wienerbergerfrontend.models.Data;

public class AuthenticationResponse implements Data
{
    public final String jwt;

    public AuthenticationResponse(String jwt)
    {
        this.jwt = jwt;
    }
}