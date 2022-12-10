package com.example.groupwork.DNDChat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;
import com.example.groupwork.StickerActivity.StickerViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ClickableCampaignAdapter extends RecyclerView.Adapter<ClickableCampaignViewHolder>{

    private Context context;
    private ArrayList<ClickableCampaign> campaignList;
    private CampaignSelectionRecyclerViewInterface myCampaignRVInterface;

    public ClickableCampaignAdapter (ArrayList<ClickableCampaign> campaignList, Context context,
                                 CampaignSelectionRecyclerViewInterface myCampaignRVInterface){
        this.campaignList = campaignList;
        this.context = context;
        this.myCampaignRVInterface = myCampaignRVInterface;
    }

    @NonNull
    @Override
    public ClickableCampaignViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ClickableCampaignViewHolder
                ((LayoutInflater.from(context).inflate(R.layout.dnd_select_campaign_card, null)), myCampaignRVInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull ClickableCampaignViewHolder holder, int position) {
        holder.campaign.setText(String.valueOf(campaignList.get(position).getCampaignName()));
    }

    @Override
    public int getItemCount() {
        return campaignList.size();
    }
}
