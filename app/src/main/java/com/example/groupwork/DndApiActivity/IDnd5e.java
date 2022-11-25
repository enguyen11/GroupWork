package com.example.groupwork.DndApiActivity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IDnd5e {

    //returns the list of objects in the results of hitting an endpoint
    @GET("api/{endpoint}")
    Call<Dnd5eItemList>  getItemList(@Path(value = "endpoint", encoded = true) String endpoint);


    @GET("api/equipment/{index}")
    Call<Equipment>  getSpecificEquipment(@Path(value = "index", encoded = true) String index);


    @GET("api/equipment-categories/{index}")
    Call<EquipmentCategoryList>  getEquipmentInCategory(@Path(value = "index", encoded = true) String index);


    @GET("api/monsters/{index}")
    Call<Monster>  getSpecificMonster(@Path(value = "index", encoded = true) String index);


    @GET("api/spells/{index}")
    Call<Spell>  getSpecificSpell(@Path(value = "index", encoded = true) String index);


}
