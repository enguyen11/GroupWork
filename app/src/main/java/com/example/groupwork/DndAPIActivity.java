package com.example.groupwork;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.groupwork.model.Dnd5eItem;
import com.example.groupwork.model.Dnd5eItemList;
import com.example.groupwork.model.Equipment;
import com.example.groupwork.model.EquipmentAdapter;
import com.example.groupwork.model.IDnd5e;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class is the main driver for the DndMain activity.
 */
public class DndAPIActivity extends AppCompatActivity {
    private static final String TAG = "DndAPIActivity";

    private Spinner spinner;
    private RecyclerView recyclerView;
//    private List<Dnd5Item> itemList;
    private Retrofit retrofit;
    private Button retrofitBtn;
    private IDnd5e api;
    private List<Equipment> equipmentList;

    public DndAPIActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //Our recycler
        recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EquipmentAdapter(equipmentList, this));
        


        retrofitBtn = findViewById(R.id.button);
        retrofitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEquipment();
                //getComments();
                //postWithQ();
                //createANewPost();
                //createANewPostWithHeaders();
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.dnd5eapi.co/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(IDnd5e.class);

    }

    // Get requests Example
    private void getEquipment(){
        // to execute the call
        Call<Dnd5eItemList>  call = api.getItemList();
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
                            .append("Name :")
                            .append(item.getName())
                            .append("\n");


                   Log.d(TAG, str.toString());


                }
            }

            @Override
            public void onFailure(Call<Dnd5eItemList> call, Throwable t) {
                // this gets called when url is wrong and therefore calls can't be made OR processing the request goes wrong.
                Log.d(TAG, "Call failed!" + t.getMessage());
            }
        });
    }
}