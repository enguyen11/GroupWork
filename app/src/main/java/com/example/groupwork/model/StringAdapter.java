package com.example.groupwork.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

import java.util.ArrayList;

public class StringAdapter extends RecyclerView.Adapter<ItemViewHolder>{

    ArrayList<String> stringList;
    Context context;

    public StringAdapter(ArrayList<String> stringList, Context context){
        this.stringList = stringList;
        this.context = context;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder((LayoutInflater.from(context).inflate(R.layout.equipment_arrange, null)));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        System.out.println(stringList.get(position));
        holder.name.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
