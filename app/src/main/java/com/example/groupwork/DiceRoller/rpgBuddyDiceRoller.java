package com.example.groupwork.DiceRoller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.model.dice.DiceGridAdapter;
import com.example.groupwork.model.dice.DiceHistoryAdapter;
import com.example.groupwork.model.dice.DiceItem;
import com.example.groupwork.model.dice.DiceThrow;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.example.groupwork.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link rpgBuddyDiceRoller#newInstance} factory method to
 * create an instance of this fragment.
 */
public class rpgBuddyDiceRoller extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private GridView gridView;
    private EditText bonusInput;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // normal fields
    private Integer lastResult;
    public HashMap<Integer, Integer> diceMap;
    private threadedDiceThrow helperObject;
    private Thread secundaryThread;
    private Handler handler;
    private TextView resultView;
    private TextView clearBtn;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter historyAdapter;
    private List<String> history;
    private Integer modifier;
    private TextView modifierLeft;
    private TextView modifierRight;
    private Vibrator vibrator; //insert joke here

    // shake feature
    private SensorManager sensorManager;
    private Sensor accelerometer;
    private boolean sensorEnabled = false;

    public rpgBuddyDiceRoller() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment rpgBuddyDiceRoller.
     */
    // TODO: Rename and change types and number of parameters
    public static rpgBuddyDiceRoller newInstance(String param1, String param2) {
        rpgBuddyDiceRoller fragment = new rpgBuddyDiceRoller();
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


        // modifier
        modifier = 0;

        View view = inflater.inflate(R.layout.fragment_rpg_buddy_dice_roller, container, false);
        //last result
        lastResult = 0;
        //initialize dice map
        diceMap = new HashMap<>();

        //vibrator;
        vibrator = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        // result text View and clear btn
        this.resultView = view.findViewById(R.id.currentResult);
        this.clearBtn = view.findViewById(R.id.clearBtn);


        // create handler
        handler = new Handler();


        // helper processor
        helperObject = new threadedDiceThrow(this);
        secundaryThread = new Thread(helperObject);

        // Define gridview
        GridView gridView = view.findViewById(R.id.diceRollerGrid);
        this.gridView = gridView;


        // define buttons

        // add all of the items
        ArrayList<DiceItem> items = new ArrayList<>();
        items.add(new DiceItem(20, 0, R.drawable.d20_black));
        items.add(new DiceItem(12, 0, R.drawable.d12_black));
        items.add(new DiceItem(10, 0, R.drawable.d10_black));
        items.add(new DiceItem(8, 0, R.drawable.d8_black));
        items.add(new DiceItem(6, 0, R.drawable.d6_black));
        items.add(new DiceItem(3, 0, R.drawable.d3_black));
        gridView.setAdapter(new DiceGridAdapter(this.getContext(), items));
        gridView.setNumColumns(3);


        // grid view on item listener
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DiceItem item = (DiceItem) adapterView.getItemAtPosition(i);
                int number = item.getQuantity() + 1;
                item.setQuantity(number);
                item.updateQuantity();
                addToDiceMap(item.getType());
                if (getVibrator() != null){
                    getVibrator().vibrate(60);
                }
            }
        });

        // reduce the item's number
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                DiceItem item = (DiceItem) adapterView.getItemAtPosition(i);
                if (item.getQuantity() > 0) {
                    int number = item.getQuantity() - 1;
                    item.setQuantity(number);
                    item.updateQuantity();
                    addToDiceMap(item.getType());
                    if (getVibrator() != null){
                        getVibrator().vibrate(100);
                    }
                    return true;
                }
                return false;
            }
        });

        //clear button
        clearBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                diceMap.clear();
                for (int i = 0; i < gridView.getAdapter().getCount(); i++) {
                    DiceItem item = (DiceItem) gridView.getAdapter().getItem(i);
                    item.setQuantity(0);
                    item.updateQuantity();
                }
                resultView.setText("0");
            }
        });

        //throw button
        FloatingActionButton throwDice = view.findViewById(R.id.throwButton);
        throwDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                secundaryThread.run();
            }
        });


        // history recycler view
        recyclerView = view.findViewById(R.id.historyView);
        this.history = new LinkedList<>();
        historyAdapter = new DiceHistoryAdapter(history);
        recyclerView.setAdapter(historyAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        // sensor
        this.sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        if (this.sensorManager != null) {
            Sensor temp = this.sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (temp != null) {
                this.accelerometer = temp;
                sensorEnabled = true;
                SensorEventListener listener = new SensorEventListener() {
                    @Override
                    public void onSensorChanged(SensorEvent sensorEvent) {
                        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                            double xAcc = sensorEvent.values[0];
                            double yAcc = sensorEvent.values[1];
                            double zAcc = sensorEvent.values[2];
                            Log.d("SENSOR", "onSensorChanged: sensor movement detected" + String.format("%f %f %f", xAcc, yAcc, zAcc));
                            if ((zAcc > 8 && xAcc > 8) || (yAcc > 8 && xAcc > 8) || (zAcc > 8 && yAcc > 8)) {
                                Log.d("TRIGGER", "onSensorChanged: sensor movement detected" + String.format("%f %f %f", xAcc, yAcc, zAcc));
                                secundaryThread.run();
                            }

                        }
                    }

                    @Override
                    public void onAccuracyChanged(Sensor sensor, int i) {

                    }
                };
                sensorManager.registerListener(listener, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                Log.d("SENSOR", "onCreateView: SENSOR ENABLED");
            }

            //set chrevon
            View chevronLeft = view.findViewById(R.id.chevronLeft);
            View chevronRight = view.findViewById(R.id.chevronRight);
            // modifiers
            modifierLeft = view.findViewById(R.id.modLeft);
            modifierRight = view.findViewById(R.id.modRight);

            chevronLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setModifier(getModifier() -1);
                    upadateResult(getLastResult());
                    updateModifierViews();
                }
            });

            chevronRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setModifier(getModifier() + 1);
                    upadateResult(getLastResult());
                    updateModifierViews();
                }
            });

        }


        // end of onCreateView, return the current view
        return view;
    }

    private HashMap<Integer, Integer> collectAllDice() {
        // collect all the dice from the adapters. (Shouldn't take long at all.)
        int size = this.gridView.getAdapter().getCount();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < size; i++) {
            DiceItem item = (DiceItem) gridView.getItemAtPosition(i);
            map.put(item.getType(), item.getQuantity());
        }
        this.diceMap = map;
        return map;
    }

    public HashMap<Integer, Integer> getDiceMap() {
        return diceMap;
    }

    public void addToDiceMap(int dice) {
        if (diceMap.containsKey(dice)) {
            // this is safe
            int num = diceMap.get(dice);
            diceMap.put(dice, num + 1);
        } else {
            diceMap.put(dice, 1);
        }
    }

    public Handler getHandler() {
        return handler;
    }

    public void upadateResult(int res) {
        lastResult = res + this.modifier;
        this.resultView.setText(lastResult.toString());
    }

    public RecyclerView.Adapter getAdapter() {
        return historyAdapter;
    }


    public Integer getLastResult() {
        return lastResult;
    }


    public List<String> getHistory() {
        return history;
    }


    public void scrollUpHistory() {
        recyclerView.scrollToPosition(recyclerView.getAdapter().getItemCount() - 1);
    }

    public int getModifier() {
        return modifier;
    }

    public void setModifier(int newMod) {
        modifier = newMod;
    }

    public void updateModifierViews(){
        if (modifier == 0){
            modifierRight.setText("+0");
            modifierLeft.setText("-0");
        }else if (modifier > 0){
            modifierRight.setText("+" + modifier.toString());
            modifierLeft.setText("+0");
        }else{
            modifierRight.setText("+0");
            modifierLeft.setText(modifier.toString());
        }
    }

    public Vibrator getVibrator(){
        return vibrator;
    }
}

/**
 * This class helps process all the small calculations
 * required to return the result for every throw.
 */
class threadedDiceThrow implements Runnable {

    private rpgBuddyDiceRoller mainClass;
    private long cooldown = 0L;

    /**
     * The current activity.
     *
     * @param mainClass our current fragment
     */
    threadedDiceThrow(rpgBuddyDiceRoller mainClass) {
        this.mainClass = mainClass;
    }

    @Override
    public void run() {


        DiceThrow diceThrow = calculateThrow(mainClass.getDiceMap());
        mainClass.getHandler().post(new Runnable() {
            @Override
            public void run() {
                long currentTime = System.currentTimeMillis();
                if (currentTime - cooldown <= 400.00 && cooldown != 0L) return;
                cooldown = currentTime;
                mainClass.upadateResult(diceThrow.getResult());
                mainClass.getHistory().add(diceThrow.toString());
                mainClass.getAdapter().notifyDataSetChanged();
                mainClass.scrollUpHistory();
                if (mainClass.getVibrator() != null){
                    mainClass.getVibrator().vibrate(100);
                }
            }
        });
    }

    public DiceThrow calculateThrow(HashMap<Integer, Integer> map) {
        DiceThrow currDiceThrow = new DiceThrow(map);
        currDiceThrow.throwDice();
        return currDiceThrow;
    }

}