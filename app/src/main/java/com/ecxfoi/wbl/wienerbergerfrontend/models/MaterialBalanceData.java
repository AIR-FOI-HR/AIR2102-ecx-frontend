package com.ecxfoi.wbl.wienerbergerfrontend.models;

import com.google.gson.annotations.SerializedName;

public class MaterialBalanceData
{
    @SerializedName("materialNumber")
    private Long id;
    private String name;
    private Integer quantity;

    public MaterialBalanceData(final Long id, final String name, final Integer quantity)
    {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
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

    public Integer getQuantity()
    {
        return quantity;
    }

    public void setQuantity(final Integer quantity)
    {
        this.quantity = quantity;
    }
}
