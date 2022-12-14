package com.example.groupwork.StickerActivity;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

import java.util.ArrayList;

public class StickerAdapter extends RecyclerView.Adapter<StickerViewHolder>{

    private final ArrayList<Sticker> stickerList;
    private final Context context;
    private static final String TAG = "DndAPIActivity";
    private final StickerRecyclerViewInterface myStickerRVI;
    private StickerSelectionFragment fragment;

    public StickerAdapter(ArrayList<Sticker> stickerList, Context context,
                          StickerSelectionFragment fragment, StickerRecyclerViewInterface stickerRecyclerViewInterface) {
        this.stickerList = stickerList;
        this.context = context;
        this.fragment = fragment;
        this.myStickerRVI = stickerRecyclerViewInterface;

    }

    @NonNull
    @Override
    public StickerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StickerViewHolder((LayoutInflater.from(context).inflate(R.layout.sticker_card, null)), myStickerRVI);
    }

    @Override
    public void onBindViewHolder(@NonNull StickerViewHolder holder, int position) {
        String uri = "@drawable/" + stickerList.get(position).getName();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

        Drawable res = context.getResources().getDrawable(imageResource);
        res.setBounds(new Rect(0,0,96,96));
        holder.sticker.setImageDrawable(res);
        holder.numUse.setText(String.valueOf(stickerList.get(position).getNumUse()));
    }

    @Override
    public int getItemCount() {
        return stickerList.size();
    }
}
