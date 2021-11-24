package com.ecxfoi.wbl.wienerbergerfrontend.models;

import java.io.Serializable;

public class WienerbergerResponse implements Serializable
{
    final private boolean success;
    final private String message;
    final private WienerbergerResponseData wienerbergerResponseData;

    public WienerbergerResponse(final boolean success, final String message, final WienerbergerResponseData wienerbergerResponseData)
    {
        this.success = success;
        this.message = message;
        this.wienerbergerResponseData = wienerbergerResponseData;
    }

    public WienerbergerResponseData getData()
    {
        return wienerbergerResponseData;
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