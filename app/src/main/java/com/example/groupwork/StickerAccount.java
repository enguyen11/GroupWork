package com.example.groupwork;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

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
        stickersButton = findViewById(R.id.button_stickers);
        /*
            call function to fill stickerArray
            should run on a new thread
         */

        friendsButton.setOnClickListener(view -> {
            //Open friends activity

        });
        stickersButton.setOnClickListener(view -> {
            //Open sticker catalog activity

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