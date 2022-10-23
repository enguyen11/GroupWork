package com.example.groupwork.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

public class EquipmentViewHolder extends RecyclerView.ViewHolder{
    public final TextView name;
    public final TextView url;

    public EquipmentViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.equipmentName);
        url = itemView.findViewById(R.id.equipmentURL);
    }
}
