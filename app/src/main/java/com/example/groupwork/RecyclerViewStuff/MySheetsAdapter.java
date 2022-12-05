package com.example.groupwork.RecyclerViewStuff;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.RPG_Model.SheetType;
import com.example.groupwork.R;

import java.util.ArrayList;

public class MySheetsAdapter extends RecyclerView.Adapter<MySheetsViewHolder> {
    ArrayList<SheetType> sheetList;
    Context context;

    public MySheetsAdapter(Context context, ArrayList<SheetType> sheetList){
        this.context = context;
        this.sheetList = sheetList;
    }

    @NonNull
    @Override
    public MySheetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MySheetsViewHolder(LayoutInflater.from(context).inflate(R.layout.sheet_type_card, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MySheetsViewHolder holder, int position) {
        String sheetName = sheetList.get(position).getName();
        holder.sheetText.setText(sheetName);
    }

    @Override
    public int getItemCount() {
        return sheetList.size();
    }
}
