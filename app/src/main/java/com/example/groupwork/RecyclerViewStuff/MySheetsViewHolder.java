package com.example.groupwork.RecyclerViewStuff;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;


public class MySheetsViewHolder extends RecyclerView.ViewHolder {

    public final TextView sheetText;
    public MySheetsViewHolder(@NonNull View itemView) {
        super(itemView);
        sheetText = itemView.findViewById(R.id.textView_sheetName_forCard);
    }
}
