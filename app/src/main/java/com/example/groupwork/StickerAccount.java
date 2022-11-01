package com.example.groupwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class StickerAccount extends AppCompatActivity {

    private Button friendsButton;
    private Button stickersButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_account);

        friendsButton = findViewById(R.id.button_friends);
        stickersButton = findViewById(R.id.button_stickers);

        friendsButton.setOnClickListener(view -> {
            //Open friends activity

        });
        stickersButton.setOnClickListener(view -> {
            //Open sticker catalog activity

        });
        //We want the welcome message to actually display the username
    }
}