package com.example.groupwork.Firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.groupwork.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Friends extends AppCompatActivity {

    private String userID;
    private ArrayList<String> friendsList;
    private Button btnNewFriend;
    private EditText editNewFriend;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;
    private RecyclerView friendsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends);

        btnNewFriend = findViewById(R.id.button_add_friend);
        editNewFriend = findViewById(R.id.editTextNewFriend);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userID = extras.getString("userID");
        }
        friendsList = new ArrayList<>();
        db = FirebaseDatabase.getInstance("https://cs5220-dndapp-default-rtdb.firebaseio.com/");
        mDatabase = db.getReference("Users");
        getList();
        //System.out.println("*********" + friendsList.get(0) + "*******");
        friendsView = findViewById(R.id.recycler_friends);
        friendsView.setAdapter(new FriendsAdapter(friendsList, friendsView.getContext()));
        friendsView.setLayoutManager(new LinearLayoutManager(Friends.this));
        btnNewFriend.setOnClickListener(view -> {
            friendsList.add(editNewFriend.getText().toString());
            mDatabase.child(userID).child("friendsList").setValue(friendsList);
            friendsList.clear();
            friendsView.getAdapter().notifyDataSetChanged();
        });
    }
    private void getList(){
        mDatabase.child(userID).child("friendsList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot child : snapshot.getChildren()){
                    friendsList.add(child.getValue().toString());
                    friendsView.getAdapter().notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }
}