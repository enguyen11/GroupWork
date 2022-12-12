package com.example.groupwork.CharacterEditor;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.groupwork.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryListViewHolder extends RecyclerView.ViewHolder {
    public final TextView text;
    public final RecyclerView recycler;
    public final Button removeButton;
    public final EditText addField;
    public final Button addButton;
    public CategoryListViewHolder(@NonNull View itemView) {
        super(itemView);
        this.text = itemView.findViewById(R.id.textViewStatGroup);
        this.recycler = itemView.findViewById(R.id.recyclerViewForStats);
        this.removeButton = itemView.findViewById(R.id.removeGroupButton);
        this.addField = itemView.findViewById(R.id.EditAddIndStat);
        this.addButton = itemView.findViewById(R.id.addIndStatButton);
    }
}
