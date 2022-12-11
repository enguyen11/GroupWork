package com.example.groupwork.RecyclerViewStuff;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.CharacterEditor.CharacterSheetActivity;
import com.example.groupwork.RPG_Model.Character;
import com.example.groupwork.R;

import java.util.ArrayList;


public class MyCharsAdapter extends RecyclerView.Adapter<MyCharsViewHolder> {
    private Context context;
    private ArrayList<Character> charList;
    private String name;

    public MyCharsAdapter(Context context, ArrayList<Character> charList, String name){
        this.context = context;
        this.charList = charList;
        this.name = name;
    }

    @NonNull
    @Override
    public MyCharsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyCharsViewHolder(LayoutInflater.from(context).inflate(R.layout.sheet_type_card, null));
    }

    @Override
    public void onBindViewHolder(@NonNull MyCharsViewHolder holder, int position) {
        holder.name.setText(charList.get(position).getName());
        holder.name.setOnClickListener(view -> {
            Intent goTo = new Intent(context, CharacterSheetActivity.class);
            goTo.putExtra("player", name);
            goTo.putExtra("character", charList.get(position).getName());
            context.startActivity(goTo);
        });

    }

    @Override
    public int getItemCount() {
        return charList.size();
    }
}
