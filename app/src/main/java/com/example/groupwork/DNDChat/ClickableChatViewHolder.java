package com.example.groupwork.DNDChat;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

public class ClickableChatViewHolder extends RecyclerView.ViewHolder{
    public final TextView chat;
    public ClickableChatViewHolder(@NonNull View itemView, ChatSelectionRecyclerViewInterface myChatRVInterface) {
        super(itemView);
        this.chat = itemView.findViewById(R.id.chatSelected);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myChatRVInterface != null){
                    int pos = getBindingAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION){
                        myChatRVInterface.onChatClick(pos);
                    }

                }
            }
        });
    }
}
