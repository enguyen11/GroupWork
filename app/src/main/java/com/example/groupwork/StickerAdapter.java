package com.example.groupwork;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.model.Dnd5eItem;
import com.example.groupwork.model.ItemViewHolder;

import java.util.ArrayList;

public class StickerAdapter extends RecyclerView.Adapter<StickerViewHolder>{

    private final ArrayList<Sticker> stickerList;
    private final Context context;
    private static final String TAG = "DndAPIActivity";

    public StickerAdapter(ArrayList<Sticker> stickerList, Context context) {
        this.stickerList = stickerList;
        this.context = context;
    }

    @NonNull
    @Override
    public StickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StickerViewHolder((LayoutInflater.from(context).inflate(R.layout.sticker_card, null)));
    }

    @Override
    public void onBindViewHolder(@NonNull StickerViewHolder holder, int position) {
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
}
