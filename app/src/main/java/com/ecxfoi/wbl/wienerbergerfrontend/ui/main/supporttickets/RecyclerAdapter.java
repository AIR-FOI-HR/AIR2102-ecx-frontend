package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.models.TicketData;

import java.util.ArrayList;

abstract class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.TicketsViewHolder> implements TicketListenerCallback
{
    private final ArrayList<TicketData> ticketArray;

    /**
     * Provide a reference to the type of views that you are using
     * (custom TicketsViewHolder).
     */
    public static class TicketsViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener
    {
        private final TextView tvSubject;
        private final TextView tvStatus;
        private final View circleStatus;
        private TicketData ticketData;
        private RecyclerAdapter recyclerAdapter;

        public TicketsViewHolder(final View view, final RecyclerAdapter recyclerAdapter)
        {
            super(view);

            tvSubject = view.findViewById(R.id.tv_ticket_subject);
            tvStatus = view.findViewById(R.id.tv_ticket_status);
            circleStatus = view.findViewById(R.id.circle_status_indicator);
            this.recyclerAdapter = recyclerAdapter;

            view.setOnLongClickListener(this);
        }

        public void bind(TicketData ticketData)
        {
            this.ticketData = ticketData;

            tvSubject.setText(ticketData.getSubject());
            switch (ticketData.getStatus())
            {
                case "New":
                    tvStatus.setText(R.string.status_new);
                    circleStatus.setBackgroundResource(R.drawable.circle_status_new);
                    break;
                case "Resolved":
                    tvStatus.setText(R.string.status_resolved);
                    circleStatus.setBackgroundResource(R.drawable.circle_status_resolved);
                    break;
                default: // Treat any vague status as "In-progress".
                    tvStatus.setText(R.string.status_inprogress);
                    circleStatus.setBackgroundResource(R.drawable.circle_status_progress);
                    break;
            }
        }

        @Override
        public boolean onLongClick(final View view)
        {
            if (ticketData != null)
            {
                recyclerAdapter.onTicketSelected(ticketData.getId());
            }
            return true;
        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param ticketArray String[] containing the data to populate views to be used
     *                    by RecyclerView.
     */
    public RecyclerAdapter(ArrayList<TicketData> ticketArray)
    {
        this.ticketArray = ticketArray;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public TicketsViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.support_ticket_row_item, viewGroup, false);

        return new TicketsViewHolder(view, this);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TicketsViewHolder ticketsViewHolder, final int position)
    {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        ticketsViewHolder.bind(ticketArray.get(ticketArray.size() - 1 - position));
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return ticketArray.size();
    }
}