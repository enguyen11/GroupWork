package com.example.groupwork.DNDChat;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {

    public final TextView sender;
    public final TextView senderMessage;
    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
        this.sender = itemView.findViewById(R.id.sender);
        this.senderMessage = itemView.findViewById(R.id.senderMessage);
    }
}
