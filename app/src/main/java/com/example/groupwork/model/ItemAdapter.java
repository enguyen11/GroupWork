package com.example.groupwork.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder>{
    private final ArrayList<Dnd5eItem> itemList;
    private final Context context;

    public ItemAdapter(ArrayList<Dnd5eItem> itemList, Context context){
        this.itemList = itemList;
        this.context = context;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.equipment_arrange, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.bindThisData(itemList.get(position));
        System.out.println(position);
        System.out.println(itemList.get(position).getName());
        //holder.description.setText(itemList.getItems().get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
