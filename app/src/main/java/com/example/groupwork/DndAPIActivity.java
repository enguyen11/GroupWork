package com.example.groupwork;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import android.widget.Spinner;
import android.util.Log;

import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.model.Dnd5eItem;
import com.example.groupwork.model.Dnd5eItemList;
import com.example.groupwork.model.Equipment;
import com.example.groupwork.model.IDnd5e;
import com.example.groupwork.model.Monster;
import com.example.groupwork.model.Spell;

import retrofit2.Retrofit;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;


import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//import com.example.groupwork.Dnd5ApiCaller.Dnd5Item;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class is the main driver for the DndMain activity.
 */
public class DndAPIActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private static final String TAG = "DndAPIActivity";
    private Spinner spinner;
    private RecyclerView recyclerView;
    //    private List<Dnd5Item> itemList;
    private Retrofit retrofit;
    private Button retrofitBtn;
    private IDnd5e api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dnd_apiactivity);

        // Set the spinner for all categories
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.DndApiOptions, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        retrofitBtn = findViewById(R.id.button);
        retrofitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * TODO: switch or if to determine which call to make
                 *  pass in variable instead of hardcoded string
                 */
                String endpoint = "equipment";
                String index = "club";
                getEndpointList(endpoint);
                getSpecificEquipment(index);
                //getSpecificMonster(index);
                //getSpecificEquipment(index);
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dnd5eapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(IDnd5e.class);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String text;
        text = adapterView.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        //Our recycler
//        recyclerView = new RecyclerView(this);


        retrofitBtn = findViewById(R.id.button);
        retrofitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                /**
                 * TODO: switch or if to determine which call to make
                 *  pass in variable instead of hardcoded string
                 */
                String endpoint = "equipment";
                String index = "club";
                getEndpointList(endpoint);
                getSpecificEquipment(index);
                //getSpecificMonster(index);
                //getSpecificEquipment(index);
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dnd5eapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(IDnd5e.class);

    }

    // Get requests

    //All items in an endpoint
    private void getEndpointList(String endpoint){
        // to execute the call
        Call<Dnd5eItemList>  call = api.getItemList(endpoint);
        //call.execute() runs on the current thread, which is main at the moment. This will crash
        // use Retrofit's method enqueue. This will automatically push the network call to background thread
        call.enqueue(new Callback<Dnd5eItemList>() {
            @Override
            public void onResponse(Call<Dnd5eItemList>  call, Response<Dnd5eItemList> response) {
                //This gets called when at least the call reaches a server and there was a response BUT 404 or any legitimate error code from the server, also calls this
                // check response code is between 200-300 and API was found

                if(!response.isSuccessful()){
                    Log.d(TAG, "Call failed!" + response.code());
                    return;
                }

                Log.d(TAG, "Call Succeeded!");
                Dnd5eItemList itemList = response.body();
                for(Dnd5eItem item : itemList.getItems()){
                    StringBuffer  str = new StringBuffer();
                    str.append("Code:: ")
                            .append(response.code())
                            .append("Name: ")
                            .append(item.getName())
                            .append("\n")
                            .append("url: ")
                            .append(item.getURL()) //if we implement clicking on the item we will need this url
                            .append("\n");


                    Log.d(TAG, str.toString());


                }
            }

            @Override
            public void onFailure(Call<Dnd5eItemList> call, Throwable t) {
                // this gets called when url is wrong and therefore calls can't be made OR processing the request goes wrong.
                Log.d(TAG, "Call failed!" + t.getMessage() + call.toString());
            }
        });
    }


    //get equipment specified by
    private void getSpecificEquipment(String index){
        // to execute the call
        Call<Equipment>  call = api.getSpecificEquipment(index);
        //call.execute() runs on the current thread, which is main at the moment. This will crash
        // use Retrofit's method enqueue. This will automatically push the network call to background thread
        call.enqueue(new Callback<Equipment>() {
            @Override
            public void onResponse(Call<Equipment>  call, Response<Equipment> response) {
                //This gets called when at least the call reaches a server and there was a response BUT 404 or any legitimate error code from the server, also calls this
                // check response code is between 200-300 and API was found

                if(!response.isSuccessful()){
                    Log.d(TAG, "Call failed!" + response.code());
                    return;
                }

                Equipment equipment = response.body();
                StringBuffer  str = new StringBuffer();
                str.append("Code:: ")
                        .append(response.code())
                        .append("Name: ")
                        .append(equipment.getName())
                        .append("\n")
                        .append("Description: ")
                        .append(equipment.getDescription())
                        .append("\n");


                Log.d(TAG, str.toString());

                Log.d(TAG, "Call Succeeded!");

            }

            @Override
            public void onFailure(Call<Equipment> call, Throwable t) {
                // this gets called when url is wrong and therefore calls can't be made OR processing the request goes wrong.
                Log.d(TAG, "Call failed!" + t.getMessage());
            }
        });
    }


    //get monster specified by index
    private void getSpecificMonster(String index){
        // to execute the call
        Call<Monster>  call = api.getSpecificMonster(index);
        //call.execute() runs on the current thread, which is main at the moment. This will crash
        // use Retrofit's method enqueue. This will automatically push the network call to background thread
        call.enqueue(new Callback<Monster>() {
            @Override
            public void onResponse(Call<Monster>  call, Response<Monster> response) {
                //This gets called when at least the call reaches a server and there was a response BUT 404 or any legitimate error code from the server, also calls this
                // check response code is between 200-300 and API was found

                if(!response.isSuccessful()){
                    Log.d(TAG, "Call failed!" + response.code());
                    return;
                }

                Monster monster = response.body();
                StringBuffer  str = new StringBuffer();
                str.append("Code:: ")
                        .append(response.code())
                        .append("Name :")
                        .append(monster.getName())
                        .append("\n")
                        .append("Description: ")
                        .append(monster.getDescription())
                        .append("\n");


                Log.d(TAG, str.toString());

                Log.d(TAG, "Call Succeeded!");

            }

            @Override
            public void onFailure(Call<Monster> call, Throwable t) {
                // this gets called when url is wrong and therefore calls can't be made OR processing the request goes wrong.
                Log.d(TAG, "Call failed!" + t.getMessage());
            }
        });
    }


    //get spell specified by index
    private void getSpecificSpell(String index){
        // to execute the call
        Call<Spell>  call = api.getSpecificSpell(index);
        //call.execute() runs on the current thread, which is main at the moment. This will crash
        // use Retrofit's method enqueue. This will automatically push the network call to background thread
        call.enqueue(new Callback<Spell>() {
            @Override
            public void onResponse(Call<Spell>  call, Response<Spell> response) {
                //This gets called when at least the call reaches a server and there was a response BUT 404 or any legitimate error code from the server, also calls this
                // check response code is between 200-300 and API was found

                if(!response.isSuccessful()){
                    Log.d(TAG, "Call failed!" + response.code());
                    return;
                }

                Spell spell = response.body();
                StringBuffer  str = new StringBuffer();
                str.append("Code:: ")
                        .append(response.code())
                        .append("Name :")
                        .append(spell.getName())
                        .append("\n")
                        .append("Description: ")
                        .append(spell.getDescription())
                        .append("\n");


                Log.d(TAG, str.toString());

                Log.d(TAG, "Call Succeeded!");

            }

            @Override
            public void onFailure(Call<Spell> call, Throwable t) {
                // this gets called when url is wrong and therefore calls can't be made OR processing the request goes wrong.
                Log.d(TAG, "Call failed!" + t.getMessage());
            }
        });
    }
}