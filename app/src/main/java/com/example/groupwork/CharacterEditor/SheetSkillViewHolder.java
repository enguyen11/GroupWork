package com.example.groupwork.CharacterEditor;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.groupwork.R;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SheetSkillViewHolder extends RecyclerView.ViewHolder {
    public final TextView statName;
    public final EditText entryField;
    public SheetSkillViewHolder(@NonNull View itemView) {
        super(itemView);
        statName = itemView.findViewById(R.id.textViewStatName);
        entryField = itemView.findViewById(R.id.editTextStat);
    }
}
