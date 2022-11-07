package com.example.groupwork;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.data.DataSource;

import java.util.ArrayList;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link StickerSelectionFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class StickerSelectionFragment extends Fragment implements StickerRecyclerViewInterface{

    private RecyclerView stickerRecyclerView;
    private RecyclerView selected_stickerRecyclerView;
    private Button selectSticker;
    private StickerAdapter stickerAdapter;
    private StickerAdapter2 selectedStickerAdapter;
    private ArrayList<Sticker> stickerList;
    protected ArrayList<Sticker> selected_stickerList;
    private DataSource ds;
    private String message;
    private Button stickSelectionComplete;

//    public StickerSelectionFragment(){
//        super(R.layout.fragment_sticker_selection);
//    }
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//
//    public StickerSelectionFragment() {
//        // Required empty public constructor
//    }
//
//    /**
//     * Use this factory method to create a new instance of
//     * this fragment using the provided parameters.
//     *
//     * @param param1 Parameter 1.
//     * @param param2 Parameter 2.
//     * @return A new instance of fragment StickerSelectionFragment.
//     */
//    // TODO: Rename and change types and number of parameters
//    public static StickerSelectionFragment newInstance(String param1, String param2) {
//        StickerSelectionFragment fragment = new StickerSelectionFragment();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.fragment_sticker_selection);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }

        if (savedInstanceState != null) {
            stickerList = savedInstanceState.getParcelableArrayList("stickerList");
            Log.d(TAG, "world");
            selected_stickerList = savedInstanceState.getParcelableArrayList("selected_stickerList");
        }
        else {

            ds = new DataSource();

            stickerList = ds.loadStickers();
            selected_stickerList = new ArrayList<>();

        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sticker_selection, container, false);


        selectSticker = view.findViewById(R.id.button_selectSticker);
        selectSticker.setOnClickListener(v -> {
            if(message != null){
                //send message to user selected in Chat activity
                System.out.println(message);
            }

        });

        stickSelectionComplete = view.findViewById(R.id.button_selectSticker);
        stickSelectionComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToSearch = new Intent(v.getContext(), Chat.class);
                startActivity(goToSearch);
            }
        });

        stickerRecyclerView = view.findViewById(R.id.sticker_recyclerview);
        stickerAdapter = new StickerAdapter( stickerList, view.getContext(), this, this);
        stickerRecyclerView.setAdapter(stickerAdapter);
        stickerRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 4));

        selected_stickerRecyclerView = view.findViewById(R.id.selectedStickerRV);
        selectedStickerAdapter = new StickerAdapter2(selected_stickerList, view.getContext(), this);
        selected_stickerRecyclerView.setAdapter(selectedStickerAdapter);
        selected_stickerRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 4));

        //stickerRecyclerView.setHasFixedSize(true);

        return view;

    }

    /**
     *
     * @param message - name of sticker clicked on list
     */
    public void setMessage(String message){
        this.message = message;

    }

    //Used this video as a reference: https://www.youtube.com/watch?v=7GPUpvcU1FE to make recyclerview clickable
    @Override
    public void onStickerClick(int position) {
        selected_stickerList.add(new Sticker(stickerList.get(position).getName()));
        stickerList.get(position).numUse = stickerList.get(position).getNumUse() + 1;
        stickerAdapter.notifyItemChanged(position);
        selectedStickerAdapter.notifyItemChanged(position);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putParcelableArrayList("stickerList", (ArrayList) stickerList);
        //Log.d(TAG, "hello");
        savedInstanceState.putParcelableArrayList("selected_stickerList", (ArrayList) selected_stickerList);

    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            stickerList = savedInstanceState.getParcelableArrayList("stickerList");
            //Log.d(TAG, "world");
            selected_stickerList = savedInstanceState.getParcelableArrayList("selected_stickerList");
            //Log.d(TAG, "onViewStateRestored: not empty");
        }
    }
}