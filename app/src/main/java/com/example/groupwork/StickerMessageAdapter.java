package com.example.groupwork;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StickerMessageAdapter extends RecyclerView.Adapter<StickerMessageViewHolder>{

    private final ArrayList<Sticker> stickerList;
    private final Context context;
    private static final String TAG = "StickerMessageAdapter";
    private StickerSelectionFragment fragment;

    public StickerMessageAdapter(ArrayList<Sticker> stickerList, Context context) {
        this.stickerList = stickerList;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public StickerMessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StickerMessageViewHolder((LayoutInflater.from(context).inflate(R.layout.sticker_card_with_sender, null)));
    }

    @Override
    public void onBindViewHolder(@NonNull StickerMessageViewHolder holder, int position) {
        String uri = "@drawable/" + stickerList.get(position).getName();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

        Drawable res = context.getResources().getDrawable(imageResource);
        res.setBounds(new Rect(0,0,96,96));
        holder.sticker.setImageDrawable(res);
        holder.senderText.setText(stickerList.get(position).getSender());
    }

    @Override
    public int getItemCount() {
        return stickerList.size();
    }
}
