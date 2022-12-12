package com.example.groupwork.RecyclerViewStuff;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.CharacterEditor.CharacterSheetActivity;
import com.example.groupwork.RPG_Model.SheetType;
import com.example.groupwork.R;

import java.util.ArrayList;

public class MySheetsAdapter extends RecyclerView.Adapter<MySheetsViewHolder> {
    ArrayList<SheetType> sheetList;
    Context context;
    String name;

    public MySheetsAdapter(Context context, ArrayList<SheetType> sheetList, String name){
        this.context = context;
        this.sheetList = sheetList;
        this.name = name;
    }

    @NonNull
    @Override
    public MySheetsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MySheetsViewHolder(LayoutInflater.from(context).inflate(R.layout.sheet_type_card2, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MySheetsViewHolder holder, int position) {
        String sheetName = sheetList.get(position).getName();
        holder.sheetText.setText(sheetName);
        holder.sheetText.setOnClickListener(view -> {
            Intent goTo = new Intent(context, CharacterSheetActivity.class);
            goTo.putExtra("player", name);
            goTo.putExtra("index", position);
            context.startActivity(goTo);
        });
    }

    @Override
    public int getItemCount() {
        return sheetList.size();
    }
}
