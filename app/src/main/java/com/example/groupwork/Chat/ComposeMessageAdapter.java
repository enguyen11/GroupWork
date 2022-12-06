package com.example.groupwork.Chat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

import java.util.ArrayList;

public class ComposeMessageAdapter extends RecyclerView.Adapter<ComposeMessageViewHolder>{
    private final ArrayList<ComposeMessage> messageList;
    private final Context context;

    public ComposeMessageAdapter(ArrayList<ComposeMessage> messageList, Context context){
        this.messageList = messageList;
        this.context = context;
    }

    @NonNull
    @Override
    public ComposeMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ComposeMessageViewHolder((LayoutInflater.from(context).inflate(R.layout.message_carddnd, null)));
    }

    @Override
    public void onBindViewHolder(@NonNull ComposeMessageViewHolder holder, int position) {

        holder.senderEmail.setText(String.valueOf(messageList.get(position).getUserEmail()));
        holder.senderMessage.setText(String.valueOf(messageList.get(position).getMessage()));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
