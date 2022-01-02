package com.ecxfoi.wbl.wienerbergerfrontend.models;

import com.google.gson.annotations.SerializedName;

public class TicketData
{
    public int id;
    public String subject;
    public String status;
    public String message;
    @SerializedName("resolve_message")
    public String resolveMessage;

    public TicketData(final int id, final String subject, final String status, final String message, final String resolveMessage)
    {
        this.id = id;
        this.subject = subject;
        this.status = status;
        this.message = message;
        this.resolveMessage = resolveMessage;
    }

    public int getId()
    {
        return id;
    }

    public void setId(final int id)
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

    public String getStatus()
    {
        return status;
    }

    public void setStatus(final String status)
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
