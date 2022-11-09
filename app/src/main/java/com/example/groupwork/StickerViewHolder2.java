package com.example.groupwork;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StickerViewHolder2 extends RecyclerView.ViewHolder{
    public final ImageView sticker;
    public final TextView numUse;

    private static final String TAG = "StickerViewHolder";

    public StickerViewHolder2(@NonNull View itemView) {
        super(itemView);
        sticker = itemView.findViewById(R.id.sticker_image);
        numUse = itemView.findViewById(R.id.usages);
    }
}
