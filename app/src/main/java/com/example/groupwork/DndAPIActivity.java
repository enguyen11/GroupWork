package com.example.groupwork;

import android.content.Context;
import android.os.Bundle;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import Dnd5ApiCaller.Dnd5Item;

/**
 * This class is the main driver for the DndMain activity.
 */
public class DndAPIActivity extends AppCompatActivity {

    private Spinner spinner;
    private RecyclerView recyclerView;
    private List<Dnd5Item> itemList;

    public DndAPIActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnd_apiactivity);

        //Our recycler
        recyclerView = new RecyclerView(this);

    }
}