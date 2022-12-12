package com.example.groupwork.CharacterEditor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

import java.util.ArrayList;

public class SheetAdapter extends RecyclerView.Adapter<SheetViewHolder> {
    private ArrayList<String> stringList;
    private Context context;


    public SheetAdapter(Context context, ArrayList<String> stringList){
        this.context = context;
        this.stringList = stringList;
    }
    @NonNull
    @Override
    public SheetViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SheetViewHolder(LayoutInflater.from(context).inflate(R.layout.sheet_info_card, null));
    }

    @Override


    public void onBindViewHolder(@NonNull SheetViewHolder holder, int position) {
        System.out.println("Adding: " + stringList.get(position));
        holder.header.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }
}
