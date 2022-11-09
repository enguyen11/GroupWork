package com.example.groupwork;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    public final TextView message;

    public MessageViewHolder(@NonNull View itemView){
        super(itemView);
        message = itemView.findViewById(R.id.textView_message);

    }
}
