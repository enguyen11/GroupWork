package com.example.groupwork.CharacterEditor;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.groupwork.R;
import com.example.groupwork.RPG_Model.Player;

public class SheetBuilderActivity extends AppCompatActivity {

    private Player user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sheet_builder);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            user = extras.getParcelable("player");
            Intent goTo = new Intent(SheetBuilderActivity.this, CharacterSheetActivity.class);
            goTo.putExtra("player", user);
            startActivity(goTo);
        }

    }

    @Override
    public void onBackPressed(){
        Intent back = new Intent();
        back.putExtra("player", user);
        setResult(Activity.RESULT_OK, back);
        super.onBackPressed();

    }
}