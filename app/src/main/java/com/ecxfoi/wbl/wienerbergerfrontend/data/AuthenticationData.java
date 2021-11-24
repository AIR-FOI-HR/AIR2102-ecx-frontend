package com.ecxfoi.wbl.wienerbergerfrontend.data;

import com.ecxfoi.wbl.wienerbergerfrontend.models.WienerbergerResponseData;

public class AuthenticationData implements WienerbergerResponseData
{
    public final String jwt;

    public AuthenticationData(String jwt)
    {
        this.jwt = jwt;
    }
}