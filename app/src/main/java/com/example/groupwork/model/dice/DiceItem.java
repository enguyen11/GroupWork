package com.example.groupwork.model.dice;

import androidx.annotation.NonNull;

/**
 * This class represents a single dice and it's an item used in the interface.
 */
public class DiceItem {
    private final int type;
    private int quantity;
    private final int resource;

    public DiceItem(int maxValue, int quantity, int imageURL) {
        this.quantity = quantity;
        this.type = maxValue;
        this.resource = imageURL;
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

    @NonNull
    @Override
    public String toString() {
        String format = String.format("d%d", type);
        return format;
    }
}
