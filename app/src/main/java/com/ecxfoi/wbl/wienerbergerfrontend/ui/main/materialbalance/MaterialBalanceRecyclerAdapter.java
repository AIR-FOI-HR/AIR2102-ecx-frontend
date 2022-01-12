package com.ecxfoi.wbl.wienerbergerfrontend.ui.main.materialbalance;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ecxfoi.wbl.wienerbergerfrontend.R;
import com.ecxfoi.wbl.wienerbergerfrontend.models.MaterialBalanceData;

import java.util.ArrayList;

abstract class MaterialBalanceRecyclerAdapter extends RecyclerView.Adapter<MaterialBalanceRecyclerAdapter.MaterialBalanceViewHolder>
{
    private final ArrayList<MaterialBalanceData> materialArray;

    public static class MaterialBalanceViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView tvID;
        private final TextView tvName;
        private final TextView tvQuantity;
        private MaterialBalanceData materialBalanceData;

        public MaterialBalanceViewHolder(final View view)
        {
            super(view);

            tvID = view.findViewById(R.id.tv_material_balance_id);
            tvName = view.findViewById(R.id.tv_material_balance_name);
            tvQuantity = view.findViewById(R.id.tv_material_balance_quantity);
        }

        public void bind(MaterialBalanceData materialBalanceData)
        {
            this.materialBalanceData = materialBalanceData;

            tvID.setText(materialBalanceData.getId().toString());
            tvName.setText(materialBalanceData.getName());
            tvQuantity.setText(materialBalanceData.getQuantity().toString());
        }
    }

    public MaterialBalanceRecyclerAdapter(ArrayList<MaterialBalanceData> materialArray)
    {
        this.materialArray = materialArray;
    }

    @NonNull
    @Override
    public MaterialBalanceViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType)
    {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.material_balance_row_item, viewGroup, false);

        return new MaterialBalanceRecyclerAdapter.MaterialBalanceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MaterialBalanceViewHolder ticketsViewHolder, final int position)
    {
        ticketsViewHolder.bind(materialArray.get(materialArray.size() - 1 - position));
    }

    @Override
    public int getItemCount()
    {
        return materialArray.size();
    }
}
