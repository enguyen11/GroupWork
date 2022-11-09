package com.example.groupwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnSearchActivity, firebase, project, about;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSearchActivity = findViewById(R.id.button_search_activity);

        btnSearchActivity.setOnClickListener(view -> {
            Intent goToSearch = new Intent(MainActivity.this, DndAPIActivity.class);
            MainActivity.this.startActivity(goToSearch);
        });

        firebase = findViewById(R.id.button_firebase);
        firebase.setOnClickListener(view -> {
            Intent goToSearch = new Intent(MainActivity.this, Firebase.class);
            MainActivity.this.startActivity(goToSearch);
        });

        project = findViewById(R.id.button_final);
        project.setOnClickListener(view -> {
            Intent goToSearch = new Intent(MainActivity.this, FinalProject.class);
            MainActivity.this.startActivity(goToSearch);
        });

        about = findViewById(R.id.button_about);
        about.setOnClickListener(view -> {
            Intent goToSearch = new Intent(MainActivity.this, About.class);
            MainActivity.this.startActivity(goToSearch);
        });
    }
}