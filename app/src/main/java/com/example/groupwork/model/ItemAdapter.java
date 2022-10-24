package com.example.groupwork.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder>{
    private final ArrayList<Dnd5eItem> itemList;
    private final Context context;

    private static final String TAG = "ItemAdapter";

    public ItemAdapter(ArrayList<Dnd5eItem> itemList, Context context){
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder:  " + viewType);
        return new ItemViewHolder((LayoutInflater.from(context).inflate(R.layout.item_card, null)));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder:  " + position);
        holder.name.setText(itemList.get(position).getName());
        //holder.url.setText(itemList.get(position).getURL());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public void update(ArrayList<Dnd5eItem> data) {
        itemList.clear();
        itemList.addAll(data);
        this.notifyDataSetChanged();
    }
}
