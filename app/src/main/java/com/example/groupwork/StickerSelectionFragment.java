package com.example.groupwork;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.data.DataSource;
import com.example.groupwork.data.FirebaseViewModel;

import java.util.ArrayList;

///**
// * A simple {@link Fragment} subclass.
// * Use the {@link StickerSelectionFragment#newInstance} factory method to
// * create an instance of this fragment.
// */
public class StickerSelectionFragment extends DialogFragment implements StickerRecyclerViewInterface{


    public static final String TAG = "StickerSelectionFragm";


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
    private FirebaseViewModel viewModel;
    private ArrayList<Sticker> selected_stickerListCopy = new ArrayList<>();

    public interface OnInputListener {
        void sendInput(ArrayList<Sticker> selected_stickerList);
    }

    public OnInputListener onInputListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            stickerList = savedInstanceState.getParcelableArrayList("stickerList");
            selected_stickerList = savedInstanceState.getParcelableArrayList("selected_stickerList");
        }
        else {

            ds = new DataSource();

            stickerList = ds.loadStickers();
            selected_stickerList = new ArrayList<>();

        }


    }

    @Override
    public void onStart()
    {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null)
        {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_sticker_selection, container, false);


        Chat chatActivity = (Chat) getActivity();
        ArrayList<Sticker> list = chatActivity.getSelectedStickers();
        if(list !=null) {
            Log.d(TAG, list.toString());
            selected_stickerList = chatActivity.getSelectedStickers();

        }

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
                onInputListener.sendInput(selected_stickerList);
                closeFragment();
                //getActivity().setContentView(R.layout.activity_sticker_account);
            }
        });

        stickerRecyclerView = view.findViewById(R.id.sticker_recyclerview);
        stickerAdapter = new StickerAdapter( stickerList, view.getContext(), this, this);
        stickerRecyclerView.setAdapter(stickerAdapter);
        stickerRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 4));

        selected_stickerRecyclerView = view.findViewById(R.id.selectedStickerRV);

        selectedStickerAdapter = new StickerAdapter2(selected_stickerListCopy, view.getContext(), this);
        selected_stickerRecyclerView.setAdapter(selectedStickerAdapter);
        selected_stickerRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 4));

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
        stickerList.get(position).setNumUse(stickerList.get(position).getNumUse() + 1);
        selected_stickerList.add(new Sticker(stickerList.get(position).getName(), stickerList.get(position).getNumUse()));
        selected_stickerListCopy.add(new Sticker(stickerList.get(position).getName(), stickerList.get(position).getNumUse()));
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

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onInputListener = (OnInputListener) getActivity();
        } catch (ClassCastException e) {
            Log.e(TAG, "onAttach: " + e.getMessage());
        }
    }

    private void closeFragment() {
        this.dismiss();
    }


}