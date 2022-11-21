package com.example.groupwork.model.dice;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.groupwork.R;

/**
 * This class represents a single dice and it's an item used in the interface.
 */
public class DiceItem {
    private final int type;
    private Integer quantity;
    private final int resource;
    private View view;

    public DiceItem(int maxValue, int quantity, int imageURL) {
        this.quantity = quantity;
        this.type = maxValue;
        this.resource = imageURL;
        view = null;
    }

    public int getResource() {
        return resource;
    }

    public int getType() {
        return type;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


    public void updateQuantity(){
        if (view != null){
          TextView text = view.findViewById(R.id.quantity);
          text.setText(quantity.toString());
        }
    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("d%d", type);
    }
}
