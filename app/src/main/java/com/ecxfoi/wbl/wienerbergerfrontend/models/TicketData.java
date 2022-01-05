package com.ecxfoi.wbl.wienerbergerfrontend.models;

import com.google.gson.annotations.SerializedName;

public class TicketData
{
    public Long id;
    public String subject;
    public int status;
    public String message;
    @SerializedName("resolve_message")
    public String resolveMessage;

    public TicketData()
    {
    }

    public TicketData(final Long id, final String subject, final int status, final String message, final String resolveMessage)
    {
        this.id = id;
        this.subject = subject;
        this.status = status;
        this.message = message;
        this.resolveMessage = resolveMessage;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(final Long id)
    {
        this.id = id;
    }

    public String getSubject()
    {
        return subject;
    }

    public void setSubject(final String subject)
    {
        this.subject = subject;
    }

    public int getStatus()
    {
        return status;
    }

    public void setStatus(final int status)
    {
        this.status = status;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(final String message)
    {
        this.message = message;
    }

    public String getResolveMessage()
    {
        return resolveMessage;
    }

    public void setResolveMessage(final String resolveMessage)
    {
        this.resolveMessage = resolveMessage;
    }
}