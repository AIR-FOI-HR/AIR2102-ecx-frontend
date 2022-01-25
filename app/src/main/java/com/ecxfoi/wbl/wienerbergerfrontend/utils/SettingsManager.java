package com.ecxfoi.wbl.wienerbergerfrontend.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import androidx.biometric.BiometricManager;

public class SettingsManager
{
    public static void changeParameter(String parameterName, String parameterValue, Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        if (!preferences.getString(parameterName, "").equals(parameterValue))
        {
            editor.putString(parameterName, parameterValue);
        }
        editor.apply();
    }

    public static String getParameter(String key, Context context)
    {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(key, null);
    }

    public static void setRememberLogin(LoginMethods selectedLoginMethod, Context context)
    {
        changeParameter("remember_login", selectedLoginMethod.toString(), context);
    }

    public static LoginMethods getRememberLogin(Context context)
    {
        String methodValue = getParameter("remember_login", context);

        if (methodValue == null)
        {
            setRememberLogin(LoginMethods.NONE, context);
            methodValue = "NONE";
        }

        return LoginMethods.valueOf(methodValue);
    }

    public static Boolean isFingerprintAvailable(Activity context)
    {
        androidx.biometric.BiometricManager biometricManager = androidx.biometric.BiometricManager.from(context);

        return biometricManager.canAuthenticate(androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_WEAK) == BiometricManager.BIOMETRIC_SUCCESS;
    }

    public enum LoginMethods
    {
        NONE, // -> don't remember login, use classic login for manual entering username/password
        CLASSIC, // -> use classic login with username/password already there
        PIN, // -> use 4-digit PIN number to log-in
        FINGERPRINT // -> user fingerprint scanner
    }
}