package com.example.groupwork.CharacterEditor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groupwork.R;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class ListBuilderAdapter extends RecyclerView.Adapter<ListBuildViewHolder> {

    private ArrayList<String> items;
    private Context context;
    private Button removeButton;

    public ListBuilderAdapter(Context context, ArrayList<String> strings){
        this.context = context;
        this.items = strings;

    }

    @NonNull
    @Override
    public ListBuildViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ListBuildViewHolder(LayoutInflater.from(context).inflate(R.layout.info_card, null));
    }

    @Override
    public void onBindViewHolder(@NonNull ListBuildViewHolder holder, int position) {
        holder.text.setText(items.get(position));
        holder.removeButton.setOnClickListener(view -> {
            items.remove(position);
            this.notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
