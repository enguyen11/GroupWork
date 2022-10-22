package com.example.groupwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSearchActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearchActivity = findViewById(R.id.button_search_activity);

        btnSearchActivity.setOnClickListener(view -> {
            Intent goToSearch = new Intent(MainActivity.this, DndAPIActivity.class );
            MainActivity.this.startActivity(goToSearch);
        });
    }
}