package com.example.groupwork.StickerActivity;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

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
        holder.sender.setText(sender);
        holder.receiver.setText(receiver);
        String uri = null;
        // check if content contains sticker
        if (content.contains("sticker_")){
            String[] words = content.split(" ");
            for (String word: words){
                if (word.contains("sticker_")){
                    uri = "@drawable/" + word.trim();
                    break;
                }
            }
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

            if (imageResource > 0){
                Drawable res = context.getResources().getDrawable(imageResource);
                res.setBounds(new Rect(0,0,96,96));
                holder.content.setImageDrawable(res);
                holder.text.setVisibility(View.INVISIBLE);
            }else{
                holder.content.setVisibility(View.INVISIBLE);
                holder.text.setText("STICKER NOT FOUND: " + uri);
            }
        } else {
            holder.content.setVisibility(View.INVISIBLE);
            holder.text.setText(content);
        }
    }


    @Override
    public int getItemCount() {
        return messageList.size();
    }
}
