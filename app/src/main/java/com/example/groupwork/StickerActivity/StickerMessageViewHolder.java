package com.example.groupwork.StickerActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

public class StickerMessageViewHolder extends RecyclerView.ViewHolder{
    public final ImageView sticker;
    public final TextView senderText;

    private static final String TAG = "StickerMessageViewHolder";

    public StickerMessageViewHolder(@NonNull View itemView) {
        super(itemView);
        sticker = itemView.findViewById(R.id.msg_sticker_image);
        senderText = itemView.findViewById(R.id.text_sender);
    }
}
