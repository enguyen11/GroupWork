package com.example.groupwork.CharacterEditor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.groupwork.R;


import java.util.ArrayList;

public class SheetSkillAdapter extends RecyclerView.Adapter<SheetSkillViewHolder> {
    private Context context;
    private ArrayList<String> stringList;
    private ArrayList<String> valList;
    private ArrayList<EditText> fieldVals;


    public SheetSkillAdapter(Context context, ArrayList<String> list, ArrayList<String> valList){
        this.context = context;
        this.stringList = list;
        this.valList  = valList;
        fieldVals = new ArrayList<>();
    }

    @NonNull
    @Override
    public SheetSkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SheetSkillViewHolder(LayoutInflater.from(context).inflate(R.layout.sheet_info_card, null));
    }

    @Override
    public void onBindViewHolder(@NonNull SheetSkillViewHolder holder, int position) {
        holder.statName.setText(stringList.get(position));
         if(valList.size() > position) {
            holder.entryField.setText(valList.get(position));
        }
        fieldVals.add(holder.entryField);

    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }

    public EditText getItem(int index){
        return fieldVals.get(index);
    }
}
