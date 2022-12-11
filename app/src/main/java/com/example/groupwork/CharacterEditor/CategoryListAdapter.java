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

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListViewHolder> {

    private Context context;
    private ArrayList<String> stringList;
    private HashMap<String, ArrayList<String>> map;

    public CategoryListAdapter(Context context, ArrayList<String> list, HashMap<String, ArrayList<String>> map){
        this.context = context;
        this.stringList = list;
        this.map = map;
    }

    @NonNull
    @Override
    public CategoryListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryListViewHolder(LayoutInflater.from(context).inflate(R.layout.sheet_statsnames_card, null));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryListViewHolder holder, int position) {
        holder.text.setText(stringList.get(position));
        holder.recycler.setAdapter(new ListBuilderAdapter(context, map.get(stringList.get(position))));
        holder.recycler.setLayoutManager(new LinearLayoutManager(context));
        holder.removeButton.setOnClickListener(view -> {
            stringList.remove(position);
            this.notifyDataSetChanged();
        });
        holder.addButton.setOnClickListener(view -> {
            map.get(stringList.get(position)).add(holder.addField.getText().toString());
            holder.recycler.getAdapter().notifyDataSetChanged();
            holder.addField.setText(null);
        });

    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }
}
