package com.ecxfoi.wbl.wienerbergerfrontend.models;

import com.google.gson.annotations.SerializedName;

public class CompanyData
{
    private Integer id;

    private String name;

    @SerializedName("address_street")
    private String addressStreet;

    @SerializedName("address_post_code")
    private String addressPostCode;

    @SerializedName("address_city")
    private String addressCity;

    @SerializedName("address_country_code")
    private String addressCountryCode;

    public CompanyData()
    {

    }

    public CompanyData(Integer id, final String name, final String city, final String countryCode, final String postCode, final String street)
    {
        this.id = id;
        this.name = name;
        this.addressCity = city;
        this.addressCountryCode = countryCode;
        this.addressPostCode = postCode;
        this.addressStreet = street;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(final Integer id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getAddressStreet()
    {
        return addressStreet;
    }

    public void setAddressStreet(final String addressStreet)
    {
        this.addressStreet = addressStreet;
    }

    public String getAddressPostCode()
    {
        return addressPostCode;
    }

    public void setAddressPostCode(final String addressPostCode)
    {
        this.addressPostCode = addressPostCode;
    }

    public String getAddressCity()
    {
        return addressCity;
    }

    public void setAddressCity(final String addressCity)
    {
        this.addressCity = addressCity;
    }

    public String getAddressCountryCode()
    {
        return addressCountryCode;
    }

    public void setAddressCountryCode(final String addressCountryCode)
    {
        this.addressCountryCode = addressCountryCode;
    }
}