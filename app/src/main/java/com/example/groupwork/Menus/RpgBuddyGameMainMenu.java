package com.example.groupwork.Menus;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import javax.annotation.Nullable;

import com.example.groupwork.GameCreation.GMGameCreation;
import com.example.groupwork.GameCreation.PlayerJoinGame;
import com.example.groupwork.GameCreation.SelectPlayerTypeDialog;
import com.example.groupwork.LoadCampaign.LoadCampaignSelectionInterface;
import com.example.groupwork.R;
import com.example.groupwork.RPG_Model.Game;
import com.example.groupwork.RecyclerViewStuff.GameCardAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RpgBuddyGameMainMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RpgBuddyGameMainMenu extends Fragment  implements SelectPlayerTypeDialog.OnInputListener, LoadCampaignSelectionInterface {

    private static final String TAG = "RpgBuddyGameMainMenu";

    private RecyclerView games_recyclerView;
    private TextView emptyView;
    private ArrayList<Game> campaignList;
    private GameCardAdapter gameCardAdapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btnNewGame;
    private FirebaseDatabase db;
    private DatabaseReference mDatabase;
    private String user;


    public RpgBuddyGameMainMenu() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RpgBuddyGameMainMenu.
     */
    // TODO: Rename and change types and number of parameters
    public static RpgBuddyGameMainMenu newInstance(String param1, String param2) {
        RpgBuddyGameMainMenu fragment = new RpgBuddyGameMainMenu();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_rpg_buddy_game_main_menu, container, false);

        Bundle args = getArguments();
        if(args != null) {
            user = args.getString("userID");
        } else {
        }

        emptyView = v.findViewById(R.id.text_empty);

        if(user != null) {
            //user's created/joined games shown in a recyclerview
            campaignList = new ArrayList<>();
            games_recyclerView = v.findViewById(R.id.games_recycler_view);
            gameCardAdapter = new GameCardAdapter(campaignList, this.getContext());
            games_recyclerView.setAdapter(gameCardAdapter);
            games_recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

            db = FirebaseDatabase.getInstance("https://dndapp-b52b2-default-rtdb.firebaseio.com");
            mDatabase = db.getReference("Users");

            mDatabase.child(user).child("CampaignList").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    campaignList.clear();
                    for (DataSnapshot child : snapshot.getChildren()) {

                        String campaignName = child.getKey();
                        Log.d(TAG, "campaign: " + campaignName);
                        String curUserCharacter = child.child("character").getValue().toString();
                        Game game = new Game(campaignName, curUserCharacter, user);
                        campaignList.add(game);
                    }
                    games_recyclerView.getAdapter().notifyDataSetChanged();

                    if (campaignList.size() > 0 ) {
                        games_recyclerView.setVisibility(View.VISIBLE);
                        emptyView.setVisibility(View.GONE);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

            if (campaignList.size() == 0 || campaignList.isEmpty()) {
                games_recyclerView.setVisibility(View.GONE);
                emptyView.setVisibility(View.VISIBLE);
                Log.d(TAG, "no games to show");
            } else {
                games_recyclerView.setVisibility(View.VISIBLE);
                emptyView.setVisibility(View.GONE);
                Log.d(TAG, "you should see " + campaignList.size() + " games");
            }
        } else {
            Log.d(TAG, "user is null");
        }



        return v;
        // Inflate the layout for this fragment
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstance){
            btnNewGame = getView().findViewById(R.id.button_newGame);
            btnNewGame.setOnClickListener(view1 -> {
                new SelectPlayerTypeDialog().show(getActivity().getSupportFragmentManager(), TAG);
            });

        }

    @Override
    public void sendInput(String selection) {
        Intent i = null;
        Context context = getActivity().getApplicationContext();
        if(selection == "gm") {
            i = new Intent(context, GMGameCreation.class);
        } else if(selection == "player"){
            i = new Intent(context, PlayerJoinGame.class);
        } else {
            return;
        }
        startActivity(i);
    }

    @Override
    public void onCampaignCardClick(int position) {
        Intent goToCampaign = new Intent(getContext(), LoadedGameActivity.class);
        String campaignName = campaignList.get(position).getName();
        String username = campaignList.get(position).getCurUser();
        goToCampaign.putExtra("user", username);
        goToCampaign.putExtra("campaignName", campaignName);
        RpgBuddyGameMainMenu.this.startActivity(goToCampaign);
    }
}