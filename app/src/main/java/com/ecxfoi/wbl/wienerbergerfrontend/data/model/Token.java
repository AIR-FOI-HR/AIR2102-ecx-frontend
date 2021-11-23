package com.ecxfoi.wbl.wienerbergerfrontend.data.model;

/**
 * Singleton class for future storing of JWT token.
 */
public class Token
{
    private Token()
    {
    }

    private static Token instance = new Token();

    private String JWT;

    public Token getInstance()
    {
        return instance;
    }

    public String getJWT()
    {
        return JWT;
    }

    public void setJWT(final String JWT)
    {
        this.JWT = JWT;
    }
}