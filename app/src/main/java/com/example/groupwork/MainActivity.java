package com.example.groupwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void Search(android.view.View view){
        Intent goToSearch = new Intent(this, SearchActivity.class);
        startActivity(goToSearch);
    }

}