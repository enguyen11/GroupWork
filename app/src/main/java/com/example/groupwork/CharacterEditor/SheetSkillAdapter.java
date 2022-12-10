package com.example.groupwork.CharacterEditor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groupwork.R;


import java.util.ArrayList;

public class SheetSkillAdapter extends RecyclerView.Adapter<SheetSkillViewHolder> {
    private Context context;
    private ArrayList<String> stringList;

    public SheetSkillAdapter(Context context, ArrayList<String> list){
        this.context = context;
        this.stringList = list;
        System.out.println("Sheet skill: " + list);
    }

    @NonNull
    @Override
    public SheetSkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SheetSkillViewHolder(LayoutInflater.from(context).inflate(R.layout.sheet_info_card, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SheetSkillViewHolder holder, int position) {
        System.out.println(position);
        holder.statName.setText(stringList.get(position));
        System.out.println("Adding statName: " + stringList.get(position));

    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }
}
