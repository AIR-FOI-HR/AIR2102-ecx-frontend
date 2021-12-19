package com.ecxfoi.wbl.wienerbergerfrontend.models;

import com.google.gson.annotations.SerializedName;

public class CompanyData
{
    public Integer id;

    public String name;

    @SerializedName("address_street")
    public String addressStreet;

    @SerializedName("address_post_code")
    public String addressPostCode;

    @SerializedName("address_city")
    public String addressCity;

    @SerializedName("address_country_code")
    public String addressCountryCode;

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
}