package com.example.groupwork;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    public final TextView sender;
    public final TextView receiver;
    public final ImageView content;

    public MessageViewHolder(@NonNull View itemView){
        super(itemView);
        sender = itemView.findViewById(R.id.textView_sender);
        receiver = itemView.findViewById(R.id.textView_receiver);
        content = itemView.findViewById(R.id.imageView_content);

    }
}
