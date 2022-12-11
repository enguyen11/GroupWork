package com.example.groupwork.CharacterEditor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
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
    private ArrayList<ArrayList<String>> vals;
    private ArrayList<RecyclerView> rView;

    public SheetCatAdapter(Context context, ArrayList<String> list, HashMap<String, ArrayList<String>> map, ArrayList<ArrayList<String>> vals) {
        this.context = context;
        this.stringList = list;
        this.map = map;
        this.vals = vals;
        rView = new ArrayList<>();

    }

    @NonNull
    @Override
    public SheetCatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SheetCatViewHolder(LayoutInflater.from(context).inflate(R.layout.stat_cats_card, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SheetCatViewHolder holder, int position) {
        System.out.println("number of items added to adapter: " + position);
        holder.categoryName.setText(stringList.get(position));
        ArrayList<String> aList = map.get(stringList.get(position));
        System.out.println(aList);
        holder.statRecycler.setAdapter(new SheetSkillAdapter(context, aList, vals.get(position)));
        holder.statRecycler.setLayoutManager(new LinearLayoutManager(context));
        rView.add(holder.statRecycler);

    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public RecyclerView getItem(int index){
        System.out.println("get item arrayList size: " + rView.size());
        return rView.get(index);
    }
}
