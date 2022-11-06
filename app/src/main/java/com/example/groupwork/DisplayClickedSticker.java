package com.example.groupwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayClickedSticker extends AppCompatActivity {

    String sticker;
    TextView stickerDisplay;
    ImageView display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_clicked_sticker);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            sticker = extras.getString("passedSticker");
        }
        stickerDisplay = findViewById(R.id.passedSticker);
        stickerDisplay.setText(sticker);
        String temp = "@drawable/" + sticker;
        int imageResource = getResources().getIdentifier(temp, null, this.getPackageName());
        display = findViewById(R.id.display);
        display.setImageResource(imageResource);
    }
}