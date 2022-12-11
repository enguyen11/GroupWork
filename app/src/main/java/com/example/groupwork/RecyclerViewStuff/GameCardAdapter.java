package com.example.groupwork.RecyclerViewStuff;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.LoadCampaign.LoadCampaignSelectionInterface;
import com.example.groupwork.R;
import com.example.groupwork.RPG_Model.Game;
import com.example.groupwork.StickerActivity.MessageViewHolder;
import com.example.groupwork.StickerActivity.Sticker;

import java.util.ArrayList;

public class GameCardAdapter extends RecyclerView.Adapter<GameCardViewHolder>{
    private ArrayList<Game> gameList;
    private Context context;
    private LoadCampaignSelectionInterface loadCampaignSelectionInterface;


    public GameCardAdapter(ArrayList<Game> gameList, Context context, LoadCampaignSelectionInterface loadCampaignSelectionInterface) {
        this.gameList = gameList;
        this.context = context;
        this.loadCampaignSelectionInterface = loadCampaignSelectionInterface;
    }

    @NonNull
    @Override
    public GameCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new GameCardViewHolder((LayoutInflater.from(context).inflate(R.layout.game_card, null)), loadCampaignSelectionInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull GameCardViewHolder holder, int position) {
        String name = gameList.get(position).getName();
        String characterName = gameList.get(position).getCurUserCharacter();
//        String system = gameList.get(position).getSystem();
        holder.campaignName.setText(name);
        holder.userCharacter.setText(characterName);
//        holder.gameSystem.setText(system);

    }

    @Override
    public int getItemCount() {
        return gameList.size();
    }

    public void update(ArrayList<Game> data) {

//        for (Game g : gameList) {
//        }
        this.notifyDataSetChanged();

    }

    public void clear() {
        gameList.clear();
        this.notifyDataSetChanged();
    }
}
