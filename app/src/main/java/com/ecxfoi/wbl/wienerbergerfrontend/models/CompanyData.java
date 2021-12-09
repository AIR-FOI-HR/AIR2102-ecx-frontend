package com.ecxfoi.wbl.wienerbergerfrontend.models;

public class CompanyData
{
    public Integer id;
    public String name;
    public String addressStreet;
    public String addressPostCode;
    public String addressCity;
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
