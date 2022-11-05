package com.example.groupwork;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class FriendsViewHolder extends RecyclerView.ViewHolder {
    public final TextView friend;

    public FriendsViewHolder(@NonNull View itemView) {
        super(itemView);
        friend = itemView.findViewById(R.id.textView_friendName);
    }
}
