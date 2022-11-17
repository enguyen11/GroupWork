package com.example.groupwork.Firebase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

import java.util.ArrayList;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsViewHolder>{
    ArrayList<String> friendsList;
    Context context;


    public FriendsAdapter(ArrayList<String> friendsList, Context context){
        this.friendsList = friendsList;
        this.context = context;
    }
    @NonNull
    @Override
    public FriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FriendsViewHolder((LayoutInflater.from(context).inflate(R.layout.friend_card, null)));
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsViewHolder holder, int position) {
        String name = friendsList.get(position).toString();
        holder.friend.setText(name);

    }

    @Override
    public int getItemCount() {
        return friendsList.size();
    }
}
