package com.example.groupwork.GameCreation;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.groupwork.R;

public class SelectPlayerTypeDialog extends DialogFragment {

    public static String TAG = "SelectPlayerTypeDialog";

    public interface OnInputListener {
        void sendInput(String selection);
    }

    public OnInputListener onInputListener;
    private TextView selectPlayer, selectGM, actionCancel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_dm_or_player, container, false);

        actionCancel = view.findViewById(R.id.action_cancel);
        selectPlayer = view.findViewById(R.id.action_party_member);
        selectGM = view.findViewById(R.id.action_gm);

        actionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });


        selectPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInputListener.sendInput("player");
                getDialog().dismiss();
            }
        });

        selectGM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onInputListener.sendInput("gm");
                getDialog().dismiss();
            }
        });

        return view;

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

}