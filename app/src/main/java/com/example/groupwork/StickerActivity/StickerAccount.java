package com.example.groupwork.StickerActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

import com.example.groupwork.R;

public class StickerAccount extends AppCompatActivity {

    private Button friendsButton;
    private Button stickersButton;
    private double version;
    private int numberOfStickers; // this will be tied to the version number
    private Drawable[] stickerArray;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sticker_account);

        friendsButton = findViewById(R.id.button_friends);

        friendsButton.setOnClickListener(view -> {
            //Open friends activity

        });
        //We want the welcome message to actually display the username
    }

    /**
     * An incomplete function for filling an array with stickers
     * Loop through starting from 0 to the number of drawables
     * Query the database with that number, then add to the array at that number
     */
    /*
        private void fillStickerArray(){
            int num = 0;
            while num < numberOfStickers{
                String stickerName = **iterate_through_names.xml**
                Drawable sticker = new Drawable(StickerAccount.this, stickerName);
                stickerArray[num] =
                num++;
                }
        }
     */
}