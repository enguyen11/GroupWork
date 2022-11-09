package com.example.groupwork;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.model.Dnd5eItem;

import java.util.ArrayList;

public class StickerAdapter2 extends RecyclerView.Adapter<StickerViewHolder2>{

    private final ArrayList<Sticker> stickerList;
    private final Context context;
    private static final String TAG = "DndAPIActivity";
    private StickerSelectionFragment fragment;

    public StickerAdapter2(ArrayList<Sticker> stickerList, Context context, StickerSelectionFragment fragment) {
        this.stickerList = stickerList;
        this.context = context;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public StickerViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StickerViewHolder2((LayoutInflater.from(context).inflate(R.layout.sticker_card2, null)));
    }

    @Override
    public void onBindViewHolder(@NonNull StickerViewHolder2 holder, int position) {
        String uri = "@drawable/" + stickerList.get(position).getName();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

        Drawable res = context.getResources().getDrawable(imageResource);
        res.setBounds(new Rect(0,0,96,96));
        holder.sticker.setImageDrawable(res);
    }

    @Override
    public int getItemCount() {
        return stickerList.size();
    }

    public void update(ArrayList<Sticker> data) {
        stickerList.clear();
        stickerList.addAll(data);
        this.notifyDataSetChanged();
    }
}
