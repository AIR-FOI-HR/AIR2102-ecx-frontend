package com.ecxfoi.wbl.wienerbergerfrontend.models;

import com.google.gson.annotations.SerializedName;

public class UserData
{
    public String title;
    @SerializedName("first_name")
    public String firstName;
    @SerializedName("last_name")
    public String lastName;
    @SerializedName("phone_number")
    public String phoneNum;
    @SerializedName("fax_number")
    public String faxNum;
    public String email;

    public UserData(final String title, final String firstName, final String lastName, final String phoneNum, final String faxNum, final String email)
    {
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNum = phoneNum;
        this.faxNum = faxNum;
        this.email = email;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(final String title)
    {
        this.title = title;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(final String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(final String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhoneNum()
    {
        return phoneNum;
    }

    public void setPhoneNum(final String phoneNum)
    {
        this.phoneNum = phoneNum;
    }

    public String getFaxNum()
    {
        return faxNum;
    }

    public void setFaxNum(final String faxNum)
    {
        this.faxNum = faxNum;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(final String email)
    {
        this.email = email;
    }
}
