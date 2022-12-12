package com.example.groupwork.RecyclerViewStuff;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groupwork.R;


import org.w3c.dom.Text;

public class MyCharsViewHolder extends RecyclerView.ViewHolder {

    public final TextView name;
    public MyCharsViewHolder(@NonNull View itemView) {
        super(itemView);
        this.name = itemView.findViewById(R.id.textView_sheetName_forCard);
    }
}
