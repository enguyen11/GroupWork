package com.example.groupwork.board;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.groupwork.R;

import java.util.ArrayList;

public class TileGridAdapter extends BaseAdapter {

    private int width;
    private int height;
    private LayoutInflater layoutInflater;
    private Context context;
    private ArrayList<Tile> tileArrayList;

    TileGridAdapter(ArrayList<Tile> tileArrayList, int width, int height, Context context){
        this.tileArrayList = tileArrayList;
        this.width = width;
        this.height = height;
        this.context = context;
        this.layoutInflater = null;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Tile currentTile = null;

        if (layoutInflater == null){
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null) view = layoutInflater.inflate(R.layout.dicecard, null);

        return view;
    }
}
