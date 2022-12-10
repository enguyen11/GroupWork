package com.example.groupwork.DNDChat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder>{
    private final ArrayList<Message> messageList;
    private final Context context;

    public MessageAdapter (ArrayList<Message> messageList, Context context){
        this.messageList = messageList;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewHolder((LayoutInflater.from(context).inflate(R.layout.dnd_message_card, null)));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        holder.sender.setText(String.valueOf(messageList.get(position).getSender()));
        holder.senderMessage.setText(String.valueOf(messageList.get(position).getContent()));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
