package com.ecxfoi.wbl.interface_login;

public interface LoginFragment
{
    void setErrorMessage(String errorMessage);

    <T> void setListener(T listener);
}