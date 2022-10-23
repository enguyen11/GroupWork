package com.example.groupwork.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<EquipmentViewHolder>{
    private final Dnd5eItemList itemList;
    private final Context context;

    public ItemAdapter(Dnd5eItemList itemList, Context context){
        this.itemList = itemList;
        this.context = context;
    }
    @NonNull
    @Override
    public EquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EquipmentViewHolder((LayoutInflater.from(context).inflate(R.layout.equipment_arrange, null)));
    }

    @Override
    public void onBindViewHolder(@NonNull EquipmentViewHolder holder, int position) {
        holder.index.setText(itemList.getItems().get(position).getIndex());
        holder.name.setText(itemList.getItems().get(position).getName());
        //holder.description.setText(itemList.getItems().get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return itemList.getItems().size();
    }
}
