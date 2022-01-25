package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.deliverynotes;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.models.DeliveryNoteData;

import java.util.ArrayList;

public class DeliveryNotesRecyclerAdapter extends RecyclerView.Adapter<DeliveryNotesRecyclerAdapter.DeliveryNotesViewHolder>
{
    private final ArrayList<DeliveryNoteData> deliveryNoteArray;

    public DeliveryNotesRecyclerAdapter(ArrayList<DeliveryNoteData> deliveryNoteArray)
    {
        this.deliveryNoteArray = deliveryNoteArray;
    }

    @NonNull
    @Override
    public DeliveryNotesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.delivery_note_row_item, viewGroup, false);

        return new DeliveryNotesRecyclerAdapter.DeliveryNotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DeliveryNotesViewHolder deliveryNotesViewHolder, final int position)
    {
        deliveryNotesViewHolder.bind(deliveryNoteArray.get(deliveryNoteArray.size() - 1 - position));
    }

    @Override
    public int getItemCount()
    {
        return deliveryNoteArray.size();
    }

    public static class DeliveryNotesViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvID;
        private final TextView tvName;
        private final TextView tvShipToParty;
        private final TextView tvDeliveredDate;

        public DeliveryNotesViewHolder(final View view)
        {
            super(view);

            tvID = view.findViewById(R.id.tv_delivery_note_id);
            tvName = view.findViewById(R.id.tv_delivery_order_number);
            tvShipToParty = view.findViewById(R.id.tv_delivery_ship_to_party);
            tvDeliveredDate = view.findViewById(R.id.tv_delivery_delivered_date);
        }

        @SuppressLint("SetTextI18n")
        public void bind(DeliveryNoteData deliveryNoteData)
        {

            tvID.setText(deliveryNoteData.getId().toString());
            tvName.setText(deliveryNoteData.getOrderId().toString());
            tvShipToParty.setText(deliveryNoteData.getDeliveryAddress());
            tvDeliveredDate.setText(deliveryNoteData.getDeliveredDate());
        }
    }
}
