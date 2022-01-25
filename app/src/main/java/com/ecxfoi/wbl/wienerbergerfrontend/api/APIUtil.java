package com.ecxfoi.wbl.wienerbergerfrontend.api;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;

public class APIUtil
{
    public static String getErrorResponseMessage(ResponseBody errorBody)
    {
        String errorResponse = "";

        try
        {
            JSONObject jsonObject = new JSONObject(errorBody.string());
            errorResponse = jsonObject.getString("data");
        }
        catch (JSONException | IOException e)
        {
            e.printStackTrace();
        }

        return errorResponse;
    }
}
