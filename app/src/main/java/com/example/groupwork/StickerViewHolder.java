package com.example.groupwork;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StickerViewHolder extends RecyclerView.ViewHolder{
    public final ImageView sticker;

    private static final String TAG = "StickerViewHolder";

    public StickerViewHolder(@NonNull View itemView, StickerRecyclerViewInterface myStickerRVI) {
        super(itemView);
        sticker = itemView.findViewById(R.id.sticker_image);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myStickerRVI != null){
                    int pos = getBindingAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION){
                        myStickerRVI.onStickerClick(pos);
                    }

                }
            }
        });
    }
}
