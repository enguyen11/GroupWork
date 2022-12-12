package com.example.groupwork.Menus;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.groupwork.CampaignNotesActivity;
import com.example.groupwork.Login.DnDLogin;
import com.example.groupwork.MainActivity;
import com.example.groupwork.R;
import com.example.groupwork.board.BattleMapActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoadedGameMenuFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoadedGameMenuFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "user";
    private static final String ARG_PARAM2 = "campaign";
    private static final String TAG = "LoadedGameMenuFragment";

    private String campaignName;
    private String user;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Button mapBtn;
    private Button notes;
    private TextView name_title;

    public LoadedGameMenuFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragment_loaded_game_menu.
     */
    // TODO: Rename and change types and number of parameters
    public static LoadedGameMenuFragment newInstance(String param1, String param2) {
        LoadedGameMenuFragment fragment = new LoadedGameMenuFragment();
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
            campaignName = getArguments().getString("campaignName");
            user = getArguments().getString("user");
        }
        Log.d(TAG, "campaign name: " + campaignName);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_loaded_game_menu, container, false);
        name_title = v.findViewById(R.id.text_campaign_name_title);
        name_title.setText(campaignName);
        Log.d(TAG, "campaign name: " + campaignName);
        notes = v.findViewById(R.id.btn_notes);
        notes.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), CampaignNotesActivity.class);
            i.putExtra("user", user);
            i.putExtra("campaignName", campaignName);
            getActivity().startActivity(i);
        });
        //set map
        mapBtn = v.findViewById(R.id.btn_map);
        mapBtn.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), BattleMapActivity.class);
            i.putExtra("user", user);
            i.putExtra("campaignName", campaignName);
            getActivity().startActivity(i);
        });

        // Inflate the layout for this fragment
        return v;
    }
}