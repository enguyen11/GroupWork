package com.example.groupwork;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

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
public class StickerSelectionFragment extends Fragment implements OnStickerClickListener {

    private RecyclerView stickerRecyclerView;
    private Button sendSticker;
    private StickerAdapter stickerAdapter;
    private ArrayList<Sticker> stickerList;
    private DataSource ds;

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


        ds = new DataSource();

        stickerList = ds.loadStickers();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sticker_selection, container, false);


        sendSticker = view.findViewById(R.id.button_sendSticker);
        sendSticker.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Sticker sent", Toast.LENGTH_SHORT).show();
        });

        stickerRecyclerView = view.findViewById(R.id.sticker_recyclerview);
        stickerAdapter = new StickerAdapter(stickerList, view.getContext());
        stickerRecyclerView.setAdapter(stickerAdapter);
        stickerRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 4));

        //stickerRecyclerView.setHasFixedSize(true);

        return view;

    }

    @Override
    public void onStickerClick(String data) {
        Toast.makeText(getActivity(), "Got: " + data, Toast.LENGTH_SHORT).show();
    }
}