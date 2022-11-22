package com.example.groupwork.model.dice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;

import java.util.List;

public class DiceHistoryAdapter extends RecyclerView.Adapter<DiceHistoryAdapter.ViewHolder> {

    private List<String> stringList;

    public DiceHistoryAdapter(List<String> stringList){
        this.stringList = stringList;
    }

    @NonNull
    @Override
    public DiceHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.dice_roll_history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiceHistoryAdapter.ViewHolder holder, int position) {
        holder.textView.setText(stringList.get(position));
    }

    @Override
    public int getItemCount() {
        return stringList.size();
    }


    // view holder calss
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.diceRoll);
        }

        public TextView getTextView() {
            return textView;
        }

    }
}
