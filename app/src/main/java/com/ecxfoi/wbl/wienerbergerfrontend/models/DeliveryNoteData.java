package com.ecxfoi.wbl.wienerbergerfrontend.models;

import com.google.gson.annotations.SerializedName;

public class DeliveryNoteData
{
    private Long id;
    @SerializedName("order_id")
    private Long orderId;
    @SerializedName("ship_to_party")
    private String shipToParty;
    @SerializedName("delivery_address")
    private String deliveryAddress;
    @SerializedName("delivered_Date")
    private String deliveredDate;

    public DeliveryNoteData(final Long id, final Long orderId, final String shipToParty, final String deliveryAddress, final String deliveredDate)
    {
        this.id = id;
        this.orderId = orderId;
        this.shipToParty = shipToParty;
        this.deliveryAddress = deliveryAddress;
        this.deliveredDate = deliveredDate;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public Long getOrderId()
    {
        return orderId;
    }

    public void setOrderId(final Long orderId)
    {
        this.orderId = orderId;
    }

    public String getShipToParty()
    {
        return shipToParty;
    }

    public void setShipToParty(final String shipToParty)
    {
        this.shipToParty = shipToParty;
    }

    public String getDeliveryAddress()
    {
        return deliveryAddress;
    }

    public void setDeliveryAddress(final String deliveryAddress)
    {
        this.deliveryAddress = deliveryAddress;
    }

    public String getDeliveredDate()
    {
        return deliveredDate;
    }

    public void setDeliveredDate(final String deliveredDate)
    {
        this.deliveredDate = deliveredDate;
    }
}
