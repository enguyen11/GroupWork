package com.example.groupwork.Firebase;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

public class MessageViewHolder extends RecyclerView.ViewHolder {
    public final TextView sender;
    public final TextView receiver;
    public final TextView text;
    public final ImageView content;

    public MessageViewHolder(@NonNull View itemView){
        super(itemView);
        sender = itemView.findViewById(R.id.textView_sender);
        receiver = itemView.findViewById(R.id.textView_receiver);
        content = itemView.findViewById(R.id.imageView_content);
        text = itemView.findViewById(R.id.message_view);
    }
}
