package com.example.groupwork.StickerActivity;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

public class FriendsViewHolder extends RecyclerView.ViewHolder {
    public final TextView friend;

    public FriendsViewHolder(@NonNull View itemView) {
        super(itemView);
        friend = itemView.findViewById(R.id.textView_friendName);
    }
}
