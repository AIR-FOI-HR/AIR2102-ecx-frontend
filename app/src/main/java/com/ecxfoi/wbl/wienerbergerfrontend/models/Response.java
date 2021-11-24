package com.ecxfoi.wbl.wienerbergerfrontend.models;

import java.io.Serializable;

public class Response implements Serializable
{
    final private boolean success;
    final private String message;
    final private Data data;

    public Response(final boolean success, final String message, final Data data)
    {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public Data getData()
    {
        return data;
    }

    public boolean isSuccess()
    {
        return success;
    }

    public String getMessage()
    {
        return message;
    }
}