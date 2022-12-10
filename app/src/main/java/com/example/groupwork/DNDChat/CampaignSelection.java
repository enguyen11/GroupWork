package com.example.groupwork.DNDChat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.groupwork.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.checkerframework.checker.units.qual.C;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CampaignSelection extends AppCompatActivity implements CampaignSelectionRecyclerViewInterface{

    private static ArrayList<ClickableCampaign> campaignList;
    private ClickableCampaignAdapter campaignListAdapter;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;

    public static String SENDER;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campaign_selection);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            this.SENDER = extras.getString("userID");
        }

        campaignList = new ArrayList<>();
        //this.campaignList.add(new Clickablecampaign("Test"));

        RecyclerView campaignSelection = findViewById(R.id.campaignSelection);
        campaignListAdapter = new ClickableCampaignAdapter(campaignList, campaignSelection.getContext(), this);
        campaignSelection.setAdapter(campaignListAdapter);
        campaignSelection.setLayoutManager(new LinearLayoutManager(CampaignSelection.this));

        db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com/");
        StringBuilder path = new StringBuilder("Users");
        path.append("/");
        path.append(SENDER);
        path.append("/CampaignList/Campaigns");
        System.out.println(path.toString());
        mDatabase = db.getReference(path.toString());
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot mySnapShot: snapshot.getChildren()){
                    System.out.println("Value: " + mySnapShot.getValue());
                    CampaignSelection.campaignList.add(new ClickableCampaign(String.valueOf(mySnapShot.getValue())));
                    campaignListAdapter.notifyDataSetChanged();
                }
                System.out.println("My List: " + Arrays.toString(campaignList.toArray()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                System.out.println("The read failed: " + error.getCode());
            }
        });



    }

    @Override
    public void onCampaignClick(int position) {
        Intent goToCampaign = new Intent(CampaignSelection.this, DnDChat.class);
        String campaignName = campaignList.get(position).getCampaignName();
        System.out.println("Campaign Name: " + campaignName);
        goToCampaign.putExtra("userID", SENDER);
        goToCampaign.putExtra("campaignName", campaignName);
        CampaignSelection.this.startActivity(goToCampaign);

    }
}