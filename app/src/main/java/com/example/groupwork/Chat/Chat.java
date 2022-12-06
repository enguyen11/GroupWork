package com.example.groupwork.Chat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.groupwork.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Chat extends AppCompatActivity {

    public static String USER_EMAIL;

    private DocumentReference mDocRef;
    private TextInputLayout messageArea;
    private FloatingActionButton send;
    private Map<String, String> dataToSave;

    private ArrayList<ComposeMessage> messageList;
    //= FirebaseFirestore.getInstance().document();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat2);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            USER_EMAIL = extras.getString("user");
            mDocRef = FirebaseFirestore.getInstance().document("users/" + USER_EMAIL);
        }
        dataToSave = new HashMap<>();
        messageList = new ArrayList<>();
        this.messageArea = findViewById(R.id.messageBar);
        this.send = findViewById(R.id.send);



        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageArea.getEditText().getText().toString();
                dataToSave.put("Sender", USER_EMAIL);
                dataToSave.put("Message", message);
                ComposeMessage messageToSend = new ComposeMessage(USER_EMAIL, message);
                messageList.add(messageToSend);
                //System.out.println(Arrays.toString(messageList.toArray()));

                RecyclerView chatHistory = findViewById(R.id.chatHistory);
                chatHistory.setAdapter(new ComposeMessageAdapter(messageList, chatHistory.getContext()));
                chatHistory.setLayoutManager(new LinearLayoutManager(Chat.this));
                chatHistory.getAdapter().notifyDataSetChanged();


                mDocRef.set(dataToSave).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            System.out.println("Data was saved");
                        }
                        else {
                            System.out.println("Something went wrong");
                        }
                    }
                });
            }
        });

    }
}