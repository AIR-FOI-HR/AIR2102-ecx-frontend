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


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>
{

    private String[] ticketsStringArray;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView textId;
        private final TextView textDesc;

        public ViewHolder(View view)
        {
            super(view);
            // Define click listener for the ViewHolder's View

            textId = (TextView) view.findViewById(R.id.tv_ticket_id);
            textDesc = (TextView) view.findViewById(R.id.tv_ticket_text);
        }

        public TextView getIdTextView()
        {
            return textId;
        }

        public TextView getDescTextView()
        {
            return textDesc;
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
        ticketsStringArray = new String[ticketArray.size()];
        int counter = 0;
        for (TicketData ticket : ticketArray)
        {
            ticketsStringArray[counter++] = "#" + ticket.id + "\n" + ticket.status + " | " + ticket.message;
        }
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.support_ticket_row_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position)
    {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getIdTextView().setText(ticketsStringArray[position].split("\n")[0]);
        viewHolder.getDescTextView().setText(ticketsStringArray[position].split("\n")[1]);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount()
    {
        return ticketsStringArray.length;
    }
}