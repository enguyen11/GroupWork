package com.example.groupwork.DNDChat;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

public class ClickableCampaignViewHolder extends RecyclerView.ViewHolder{
    public final TextView campaign;
    public ClickableCampaignViewHolder(@NonNull View itemView, CampaignSelectionRecyclerViewInterface myCampaignRVInterface) {
        super(itemView);
        this.campaign = itemView.findViewById(R.id.campaignSelected);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (myCampaignRVInterface != null){
                    int pos = getBindingAdapterPosition();

                    if (pos != RecyclerView.NO_POSITION){
                        myCampaignRVInterface.onCampaignClick(pos);
                    }

                }
            }
        });
    }
}
