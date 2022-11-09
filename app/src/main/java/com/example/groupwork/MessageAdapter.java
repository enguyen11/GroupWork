package com.example.groupwork;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder>{
    ArrayList<StickerMessage> messageList;
    Context context;


    public MessageAdapter(ArrayList<StickerMessage> messageList, Context context){
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
        String sender = messageList.get(position).sender;
        String receiver = messageList.get(position).receiver;
        String content = messageList.get(position).content;
        String uri = "@drawable/" + content;
        holder.sender.setText(sender);
        holder.receiver.setText(receiver);
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
        Drawable res = context.getResources().getDrawable(imageResource);
        res.setBounds(new Rect(0,0, 50,50));
        holder.content.setImageDrawable(res);
    }


    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
