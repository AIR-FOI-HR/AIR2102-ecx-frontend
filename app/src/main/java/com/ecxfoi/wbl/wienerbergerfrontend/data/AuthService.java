package com.ecxfoi.wbl.wienerbergerfrontend.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import org.apache.commons.lang.StringUtils;

public class AuthService
{
    public static boolean login(String username, String password, Context context)
    {
        try
        {
            // TODO: handle login authentication, return received jwt

            if (!(StringUtils.equals(username, "admin") && StringUtils.equals(password, "Test1234")))
            {
                throw new Exception();
            }

            String jwt = "abc.def.ghi";

            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = preferences.edit();

            if (!preferences.getString("username", "").equals(username))
            {
                editor.putString("username", username);
                editor.putString("password", password);
            }

            editor.putString("jwt", jwt);
            editor.apply();

            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public static String getUsername(Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("username", null);
    }

    public static String getPassword(Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("password", null);
    }

    public static String getJWT(Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString("jwt", null);
    }

    public static void logout(Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("jwt", "");
        editor.apply();
    }
}