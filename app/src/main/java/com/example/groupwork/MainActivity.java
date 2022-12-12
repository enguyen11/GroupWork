package com.example.groupwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.groupwork.DndApiActivity.DndAPIActivity;
import com.example.groupwork.Login.DnDLogin;
import com.example.groupwork.StickerActivity.Firebase;
import com.example.groupwork.board.BattleMapActivity;

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
            Intent goToSearch = new Intent(MainActivity.this, DnDLogin.class);
            MainActivity.this.startActivity(goToSearch);
        });

        about = findViewById(R.id.button_about);
        about.setOnClickListener(view -> {
            Intent goToSearch = new Intent(MainActivity.this, About.class);
            MainActivity.this.startActivity(goToSearch);
        });
    }
}