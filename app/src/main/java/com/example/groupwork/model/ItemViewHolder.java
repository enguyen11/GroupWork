package com.example.groupwork.model;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

public class ItemViewHolder extends RecyclerView.ViewHolder{
    public final TextView index;
    public final TextView name;
   // public final TextView description;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        index = itemView.findViewById(R.id.equipmentID);
        name = itemView.findViewById(R.id.equipmentName);
        //description = itemView.findViewById(R.id.equipmentDesc);
    }

    public void bindThisData(Dnd5eItem item){
        index.setText(item.getIndex());
        name.setText(item.getName());
    }
}
