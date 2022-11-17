package com.example.groupwork.model.dice;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.groupwork.R;

import java.util.ArrayList;

public class DiceGridAdapter extends BaseAdapter {
    private ArrayList<DiceItem> diceItems;
    private final Context context;
    private LayoutInflater layoutInflater;

    public DiceGridAdapter(Context context, ArrayList<DiceItem> diceItems) {
        this.diceItems = diceItems;
        this.context = context;
        this.layoutInflater = null;
    }

    @Override
    public int getCount() {
        return this.diceItems.size();
    }

    @Override
    public Object getItem(int i) {
        return diceItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int pos, View view, ViewGroup viewGroup) {
        View cardView;
        // check if view exists
        if (layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if (view == null){
            view = layoutInflater.inflate(R.layout.dicecard, null);
        }

        ImageView diceImage = view.findViewById(R.id.diceImage);
        TextView quantity = view.findViewById(R.id.quantity);

        diceImage.setImageResource(diceItems.get(pos).getResource());
        Integer i = diceItems.get(pos).getQuantity();
        quantity.setText(i.toString());

        return view;
    }
}