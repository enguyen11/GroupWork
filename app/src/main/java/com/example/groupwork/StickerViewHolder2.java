package com.example.groupwork;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StickerViewHolder2 extends RecyclerView.ViewHolder{
    public final ImageView sticker;

    private static final String TAG = "StickerViewHolder";

    public StickerViewHolder2(@NonNull View itemView) {
        super(itemView);
        sticker = itemView.findViewById(R.id.sticker_image2);
    }
}
