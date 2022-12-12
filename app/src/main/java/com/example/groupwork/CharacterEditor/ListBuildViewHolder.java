package com.example.groupwork.CharacterEditor;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.Button;
import android.widget.TextView;
import com.example.groupwork.R;



public class ListBuildViewHolder extends RecyclerView.ViewHolder {
    public final TextView text;
    public final Button removeButton;

    public ListBuildViewHolder(@NonNull View itemView) {
        super(itemView);
        text = itemView.findViewById(R.id.info_card_text);
        removeButton = itemView.findViewById(R.id.remove_info_button);
    }
}
