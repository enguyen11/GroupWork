package com.example.groupwork.Menus;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.groupwork.GameCreation.CreateGameActivity;

import javax.annotation.Nullable;

import com.example.groupwork.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RpgBuddyGameMainMenu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RpgBuddyGameMainMenu extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button btnNewGame;


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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rpg_buddy_game_main_menu, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstance){
            btnNewGame = getView().findViewById(R.id.button_newGame);
            btnNewGame.setOnClickListener(view1 -> {
                Intent goTo = new Intent(getActivity(), CreateGameActivity.class);
                startActivity(goTo);
            });

        }

}