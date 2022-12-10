package com.example.groupwork.RecyclerViewStuff;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

public class GameCardViewHolder extends RecyclerView.ViewHolder  {
    public final TextView campaignName;
    public final TextView gameSystem;

    public GameCardViewHolder(@NonNull View itemView) {
        super(itemView);
        campaignName = itemView.findViewById(R.id.textView_campaign_name);
        gameSystem = itemView.findViewById(R.id.textView_game_system);
    }
}
