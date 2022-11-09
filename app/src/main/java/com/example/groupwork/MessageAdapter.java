package com.example.groupwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder>{
    ArrayList<String> messageList;
    Context context;


    public MessageAdapter(ArrayList<String> messageList, Context context){
        this.messageList = messageList;
        this.context = context;
    }
    @NonNull
    @Override
    public MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MessageViewHolder((LayoutInflater.from(context).inflate(R.layout.message_card, null)));
    }

    @Override
    public void onBindViewHolder(@NonNull MessageViewHolder holder, int position) {
        String name = messageList.get(position).toString();
        holder.message.setText(name);
    }


    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
