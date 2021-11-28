package com.ecxfoi.wbl.wienerbergerfrontend.models;

import java.io.Serializable;

public class WienerbergerResponse<T> implements Serializable
{
    final private boolean success;
    final private String message;
    final private T data;

    public WienerbergerResponse(final boolean success, final String message, final T data)
    {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    public T getData()
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