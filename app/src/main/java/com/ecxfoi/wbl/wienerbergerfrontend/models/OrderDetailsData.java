package com.ecxfoi.wbl.wienerbergerfrontend.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class OrderDetailsData
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
    private String currency;
    @SerializedName("additional_project_info")
    private String additionalProjectInfo;

    public OrderDetailsData(final Long id, final String name, final String status, final Date dateOfOrder, final String paymentMethod, final Date deliveryDate, final String currency, final String additionalProjectInfo)
    {
        this.id = id;
        this.name = name;
        this.status = status;
        this.dateOfOrder = dateOfOrder;
        this.paymentMethod = paymentMethod;
        this.deliveryDate = deliveryDate;
        this.currency = currency;
        this.additionalProjectInfo = additionalProjectInfo;
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
        return "Order " + id.toString();
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

    public String getCurrency()
    {
        return currency;
    }

    public void setCurrency(final String currency)
    {
        this.currency = currency;
    }

    public String getAdditionalProjectInfo()
    {
        return additionalProjectInfo;
    }

    public void setAdditionalProjectInfo(final String additionalProjectInfo)
    {
        this.additionalProjectInfo = additionalProjectInfo;
    }
}
