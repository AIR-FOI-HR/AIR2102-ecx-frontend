package com.ecxfoi.wbl.wienerbergerfrontend.converters;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.models.TicketData;

public class TicketStatusConverter
{
    public static int getStatusMessageResource(TicketData ticketData)
    {
        if (ticketData != null)
        {
            switch (ticketData.getStatus())
            {
                case 0:
                    return R.string.status_new;
                case 1:
                    return R.string.status_inprogress;
                case 2:
                    return R.string.status_resolved;
            }
        }
        return R.string.status_unknown;
    }

    public static int getStatusBackgroundResource(TicketData ticketData)
    {
        if (ticketData != null)
        {
            switch (ticketData.getStatus())
            {
                case 0:
                    return R.drawable.circle_status_new;
                case 1:
                    return R.drawable.circle_status_progress;
                case 2:
                    return R.drawable.circle_status_resolved;
            }
        }
        return R.drawable.circle_status_new;
    }
}