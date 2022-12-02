package com.example.groupwork.CharacterEditor;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.example.groupwork.R;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class SheetViewHolder extends RecyclerView.ViewHolder {

    public final TextView header;
    public final EditText edit;
    public SheetViewHolder(@NonNull View itemView) {
        super(itemView);
        header = itemView.findViewById(R.id.textViewStatName);
        edit = itemView.findViewById(R.id.editTextStat);
    }
}
