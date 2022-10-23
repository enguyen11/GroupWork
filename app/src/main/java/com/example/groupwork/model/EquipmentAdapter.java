package com.example.groupwork.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

import java.util.List;

public class EquipmentAdapter extends RecyclerView.Adapter<EquipmentViewHolder>{
    private final List<Equipment> equipmentList;
    private final Context context;

    public EquipmentAdapter(List<Equipment> equipmentList, Context context){
        this.equipmentList = equipmentList;
        this.context = context;
    }
    @NonNull
    @Override
    public EquipmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new EquipmentViewHolder((LayoutInflater.from(context).inflate(R.layout.equipment_arrange, null)));
    }

    @Override
    public void onBindViewHolder(@NonNull EquipmentViewHolder holder, int position) {
        holder.index.setText(equipmentList.get(position).getIndex());
        holder.name.setText(equipmentList.get(position).getName());
        holder.description.setText(equipmentList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return equipmentList.size();
    }
}
