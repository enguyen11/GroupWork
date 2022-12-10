package com.example.groupwork.CharacterEditor;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groupwork.R;


public class SheetCatViewHolder extends RecyclerView.ViewHolder {
    public final TextView categoryName;
    public final RecyclerView statRecycler;
    public SheetCatViewHolder(@NonNull View itemView) {
        super(itemView);
        categoryName = itemView.findViewById(R.id.sheetStatCat);
        statRecycler = itemView.findViewById(R.id.sheetStatRecycler);
    }
}
