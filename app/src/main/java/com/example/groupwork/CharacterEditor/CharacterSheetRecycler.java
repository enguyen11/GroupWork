package com.example.groupwork.CharacterEditor;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.R;


import java.util.ArrayList;


// This is a recycler adapater to conver your inputs into a ui that you can use to fill forms.
public class CharacterSheetRecycler extends RecyclerView.Adapter<CharacterSheetRecycler.ViewHolder> {

    private ArrayList<SheetStatCard> cardArrayList;

    // View holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageHolder;
        private final TextView hint;
        private final EditText input;
        

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            hint = (TextView) view.findViewById(R.id.hint);
            input = (EditText) view.findViewById(R.id.sheetDataInput);
            imageHolder = (ImageView) view.findViewById(R.id.cosmetic_icon);;
        }

        public TextView getHint() {
            return hint;
        }

        public EditText getInput(){
            return input;
        }

        public ImageView getImageHolder(){
            return imageHolder;
        }
    }

    CharacterSheetRecycler(ArrayList<SheetStatCard> list){
        this.cardArrayList = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sheets_feature_card_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SheetStatCard card = cardArrayList.get(position);
        holder.hint.setText(card.getHint());
        holder.input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if (card.isNumber()){
                        int number = new Integer(holder.input.getText().toString());
                        card.setIntData(number);
                    } else {
                        card.setStringData(holder.input.getText().toString());
                    }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // we can add a firebase call here
                // add firebase reference to constructor, when user is done editing,
                // we can perform a firebase call
            }
        });

        holder.imageHolder.setImageResource(card.getIconId());
    }


    @Override
    public int getItemCount() {
        return 0;
    }
}
