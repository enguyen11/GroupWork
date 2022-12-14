package com.example.groupwork.StickerActivity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

public class StickerViewHolder extends RecyclerView.ViewHolder{
    public final ImageView sticker;
    public final TextView numUse;

    private static final String TAG = "StickerViewHolder";

    public StickerViewHolder(@NonNull View itemView, StickerRecyclerViewInterface myStickerRVI) {
        super(itemView);
        sticker = itemView.findViewById(R.id.sticker_image);
        numUse = itemView.findViewById(R.id.usages);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myStickerRVI != null){
                    //TODO change this
                    //int pos = 10;
                     int pos = getBindingAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION){
                        myStickerRVI.onStickerClick(pos);
                    }

                }
            }
        });
    }
}
