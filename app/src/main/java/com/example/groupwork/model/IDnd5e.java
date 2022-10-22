package com.example.groupwork.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface IDnd5e {

    // Get requests just need getter on Models
    @GET("api/equipment")   // "api/" is the relative Url of your api. We define base Url at a common place later
    Call<Dnd5eItemList>  getItemList();


//    @GET("api/equipment/{index}")
//    Call<Equipment>  getPostsWithQueryMultipleParams(@Query("itemName") String itemName,
//                                                           @Query("itemIndex") String itemIndex,
//                                                           @Query("itemURL") String itemURL);



    // Another way to do this is from URLEncoding, there's also an API for it on retrofit

    //Retrofit also supports PUT,PATCH and DELETE , same as Post

}
