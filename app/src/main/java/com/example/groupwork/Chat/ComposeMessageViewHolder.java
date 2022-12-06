package com.example.groupwork.Chat;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;


public class ComposeMessageViewHolder extends RecyclerView.ViewHolder{
    public final TextView senderEmail;
    public final TextView senderMessage;
    public ComposeMessageViewHolder(@NonNull View itemView) {
        super(itemView);
        senderEmail = itemView.findViewById(R.id.senderEmail);
        senderMessage = itemView.findViewById(R.id.senderMessage);
    }
}
