package com.example.groupwork.RecyclerViewStuff;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.LoadCampaign.LoadCampaignSelectionInterface;
import com.example.groupwork.R;

public class GameCardViewHolder extends RecyclerView.ViewHolder  {
    public final TextView campaignName;
    public final TextView userCharacter;
//    public final TextView gameSystem;

    public GameCardViewHolder(@NonNull View itemView, LoadCampaignSelectionInterface loadCampaignSelectionInterface) {
        super(itemView);
        campaignName = itemView.findViewById(R.id.textView_campaign_name);
        userCharacter = itemView.findViewById(R.id.textView_game_character);
//        gameSystem = itemView.findViewById(R.id.textView_game_system);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (loadCampaignSelectionInterface != null){
                    int pos = getBindingAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION){
                        loadCampaignSelectionInterface.onCampaignCardClick(pos);
                    }

                }
            }
        });
    }
}
