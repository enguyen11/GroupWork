package com.example.groupwork.model;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

public class ItemViewHolder extends RecyclerView.ViewHolder{
    public final TextView name;
    //public final TextView url;

    private static final String TAG = "ItemViewHolder";

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.item_name);
        //url = itemView.findViewById(R.id.item_url);
    }
}
