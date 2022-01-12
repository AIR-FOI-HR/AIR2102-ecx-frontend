package com.ecxfoi.wbl.wienerbergerfrontend.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class OrderData
{
    private Long id;
    private String name;
    private String status;
    @SerializedName("date")
    private Date dateOfOrder;
    @SerializedName("payment_method")
    private String paymentMethod;
    @SerializedName("delivery_date")
    private Date deliveryDate;

    public OrderData(final Long id, final String name, final String status, final Date dateOfOrder, final String paymentMethod, final Date deliveryDate)
    {
        this.id = id;
        this.name = name;
        this.status = status;
        this.dateOfOrder = dateOfOrder;
        this.paymentMethod = paymentMethod;
        this.deliveryDate = deliveryDate;
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
        return "Order " + this.id.toString();
    }

    public void setName(final String name)
    {
        this.name = name;
    }

    public String getStatus()
    {
        return status.replace("InProgress", "In-progress");
    }

    public void setStatus(final String status)
    {
        this.status = status;
    }

    public Date getDateOfOrder()
    {
        return dateOfOrder;
    }

    public void setDateOfOrder(final Date dateOfOrder)
    {
        this.dateOfOrder = dateOfOrder;
    }

    public String getPaymentMethod()
    {
        return paymentMethod;
    }

    public void setPaymentMethod(final String paymentMethod)
    {
        this.paymentMethod = paymentMethod;
    }

    public Date getDeliveryDate()
    {
        return deliveryDate;
    }

    public void setDeliveryDate(final Date deliveryDate)
    {
        this.deliveryDate = deliveryDate;
    }
}
