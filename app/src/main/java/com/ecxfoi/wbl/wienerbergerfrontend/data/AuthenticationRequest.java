package com.ecxfoi.wbl.wienerbergerfrontend.data;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable
{
    final private String password;
    final private String email;

    public AuthenticationRequest(final String password, final String email)
    {
        this.password = password;
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public String getEmail()
    {
        return email;
    }
}