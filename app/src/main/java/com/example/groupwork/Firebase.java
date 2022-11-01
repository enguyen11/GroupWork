package com.example.groupwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class Firebase extends AppCompatActivity {

    private Button login_button;
    private EditText username_entry;
    private ArrayList<String> username_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        login_button = findViewById(R.id.button_enter_login);
        username_entry = findViewById(R.id.editText_Username);
        username_list = new ArrayList<>();


        /**
         * When clicked, the database should be queried for a username
         * matching what was entered into username_entry
         * the ArrayList is just for temporary use
         *
         */
        login_button.setOnClickListener(view -> {
            String username = username_entry.getText().toString();
            Boolean found = false;
            int num = 0;
            username_list.add("Username");
            //This loop is a placeholder for the actual logic
            //Prints may be replaced by messaged displayed on screen
            while(!found){
                System.out.println("loop");
                if(num >= username_list.size()){
                    System.out.println("Username not found!");
                    break;
                }
                if(username.matches(username_list.get(num))){ // query database instead
                    System.out.println("Username found!");
                    found = true;
                }
                num ++;
            }
            if(found){
                Intent goToAccount = new Intent(Firebase.this, StickerAccount.class);
                Firebase.this.startActivity(goToAccount);
            }
            else{
                //Prompt user to try the create account option
                System.out.println("Create new account?");
            }
        });

    }
}