package com.example.groupwork.CharacterEditor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import com.example.groupwork.R;


public class SheetCatAdapter extends RecyclerView.Adapter<SheetCatViewHolder> {
    private Context context;
    private ArrayList<String> stringList;
    private HashMap<String, ArrayList<String>> map;

    public SheetCatAdapter(Context context, ArrayList<String> list, HashMap<String, ArrayList<String>> map) {
        this.context = context;
        this.stringList = list;
        this.map = map;
        System.out.println("********************Created************************");
        System.out.println(map);
        System.out.println(list);
    }

    @NonNull
    @Override
    public SheetCatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SheetCatViewHolder(LayoutInflater.from(context).inflate(R.layout.stat_cats_card, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SheetCatViewHolder holder, int position) {
        holder.categoryName.setText(stringList.get(position));
        ArrayList<String> aList = map.get(stringList.get(position));
        System.out.println("Sending aList: " + aList);
        holder.statRecycler.setAdapter(new SheetSkillAdapter(context, aList));
        holder.statRecycler.setLayoutManager(new LinearLayoutManager(context));

    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }
}
