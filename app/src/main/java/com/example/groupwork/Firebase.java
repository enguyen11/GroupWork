package com.example.groupwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Firebase extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragment_container_view, StickerSelectionFragment.class, null)
                    .commit();
        }
    }
}