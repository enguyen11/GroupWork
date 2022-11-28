package com.example.groupwork.CharacterEditor;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.groupwork.RPG_Model.Player;
import com.example.groupwork.RPG_Model.SheetType;
import com.example.groupwork.RecyclerViewStuff.MySheetsAdapter;
import com.example.groupwork.RecyclerViewStuff.MySheetsViewHolder;

import javax.annotation.Nullable;

import com.example.groupwork.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RpgBuddyCharacterEditor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RpgBuddyCharacterEditor extends Fragment {

    private Button btnNewSheet;
    private RecyclerView sheetView;
    private Player user;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RpgBuddyCharacterEditor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RpgBuddyCharacterEditor.
     */
    // TODO: Rename and change types and number of parameters
    public static RpgBuddyCharacterEditor newInstance(String param1, String param2) {
        RpgBuddyCharacterEditor fragment = new RpgBuddyCharacterEditor();
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
        Bundle args = getArguments();
        if(args != null) {
            user = (Player) args.getParcelable("player");
        }
        System.out.println("sheet name: " + user.getSheets().get(0).getName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_rpg_buddy_character_editor, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstance){

        btnNewSheet = getView().findViewById(R.id.button_new_sheet);
        btnNewSheet.setOnClickListener(view1 -> {
            SheetType newSheet = new SheetType();
            newSheet.setName("Dummy Sheet");
            System.out.println("sheet size: " + user.getSheets().size());
            user.getSheets().add(newSheet);
            sheetView.getAdapter().notifyDataSetChanged();
            Intent goTo = new Intent(getActivity(), SheetBuilderActivity.class);
            goTo.putExtra("player", user);
            System.out.println("**********************user: " + user);
            startActivity(goTo);
        });

        sheetView = getView().findViewById(R.id.recycler_sheet_list);
        sheetView.setAdapter(new MySheetsAdapter(getContext(),user.getSheets()));
        sheetView.setLayoutManager(new LinearLayoutManager(getContext()));
        sheetView.getAdapter().notifyDataSetChanged();
    }

}