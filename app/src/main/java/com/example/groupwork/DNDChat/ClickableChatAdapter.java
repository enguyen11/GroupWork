package com.example.groupwork.DNDChat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

import java.util.ArrayList;

public class ClickableChatAdapter extends RecyclerView.Adapter<ClickableChatViewHolder>{

    private Context context;
    private ArrayList<ClickableChat> chatList;
    private ChatSelectionRecyclerViewInterface myChatRVInterface;

    public ClickableChatAdapter(ArrayList<ClickableChat> chatList, Context context,
                                ChatSelectionRecyclerViewInterface myChatRVInterface){
        this.chatList = chatList;
        this.context = context;
        this.myChatRVInterface = myChatRVInterface;
    }

    @NonNull
    @Override
    public ClickableChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClickableChatViewHolder
                ((LayoutInflater.from(context).inflate(R.layout.dnd_select_chat_card_2, null)), myChatRVInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ClickableChatViewHolder holder, int position) {
        holder.chat.setText(String.valueOf(chatList.get(position).getChatName()));
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
}
