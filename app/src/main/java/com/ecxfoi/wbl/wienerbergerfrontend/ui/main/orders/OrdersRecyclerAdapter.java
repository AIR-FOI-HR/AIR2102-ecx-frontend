package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.orders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.models.OrderData;
import com.ecxfoi.wbl.wienerbergerfrontend.ui.main.supporttickets.TicketListenerCallback;

import java.util.ArrayList;

abstract class OrdersRecyclerAdapter extends RecyclerView.Adapter<OrdersRecyclerAdapter.OrdersViewHolder> implements OrderListenerCallback
{
    private final ArrayList<OrderData> orderArray;

    public static class OrdersViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener
    {
        private final TextView tvName;
        private final TextView tvStatus;
        private final TextView tvDateOfOrder;
        private final TextView tvPaymentMethod;
        private final TextView tvDeliveryDate;
        private OrderData orderData;
        private final OrdersRecyclerAdapter recyclerAdapter;

        public OrdersViewHolder(final View view, final OrdersRecyclerAdapter recyclerAdapter)
        {
            super(view);

            tvName = view.findViewById(R.id.tv_order_name);
            tvStatus = view.findViewById(R.id.tv_order_status);
            tvDateOfOrder = view.findViewById(R.id.tv_order_date);
            tvPaymentMethod = view.findViewById(R.id.tv_order_payment_method);
            tvDeliveryDate = view.findViewById(R.id.tv_order_delivery_date);
            this.recyclerAdapter = recyclerAdapter;

            view.setOnLongClickListener(this);
        }

        public void bind(OrderData orderData)
        {
            this.orderData = orderData;

            tvName.setText(orderData.getName());
            tvStatus.setText(orderData.getStatus());
            tvDateOfOrder.setText(orderData.getDateOfOrder().toString());
            tvPaymentMethod.setText(orderData.getPaymentMethod());
            tvDeliveryDate.setText(orderData.getDeliveryDate().toString());
        }

        @Override
        public boolean onLongClick(final View view)
        {
            if (orderData != null)
            {
                recyclerAdapter.onOrderSelected(orderData.getId());
            }
            return true;
        }
    }

    public OrdersRecyclerAdapter(ArrayList<OrderData> orderArray)
    {
        this.orderArray = orderArray;
    }

    @NonNull
    @Override
    public OrdersViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.orders_row_item, viewGroup, false);

        return new OrdersRecyclerAdapter.OrdersViewHolder(view, this);
    }

    @Override
    public void onBindViewHolder(OrdersViewHolder ticketsViewHolder, final int position)
    {
        ticketsViewHolder.bind(orderArray.get(orderArray.size() - 1 - position));
    }

    @Override
    public int getItemCount()
    {
        return orderArray.size();
    }
}
