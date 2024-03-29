package com.carpeinfinitus.petfolio.api;


import com.carpeinfinitus.petfolio.responsepojo.AddressResultsResponse;
import com.carpeinfinitus.petfolio.responsepojo.GetAddressResultResponse;
import com.carpeinfinitus.petfolio.responsepojo.PlacesResultsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {

    // https://maps.googleapis.com/maps/api/place/autocomplete/json?input=iluppaiyur&key=AIzaSyCVeEuZcqrs9phnrk1aNSpiJ57hb-V8hhE

    String BASE_URL = "https://maps.googleapis.com";
   // String key = "AIzaSyCVeEuZcqrs9phnrk1aNSpiJ57hb-V8hhE";
   public static String MAP_KEY = "AIzaSyAlvAK3lZepIaApTDbDZUNfO0dBmuP6h4A";

    @GET("/maps/api/place/autocomplete/json")
    Call<PlacesResultsResponse> getCityResults(@Query("input") String input, @Query("key") String key);

    // https://maps.googleapis.com/maps/api/geocode/json?&address=chennai&key=AIzaSyCVeEuZcqrs9phnrk1aNSpiJ57hb-V8hhE


    @GET("/maps/api/geocode/json")
    Call<AddressResultsResponse> getaddressResults(@Query("address") String input, @Query("key") String key);

    //https://maps.googleapis.com/maps/api/geocode/json?latlng=11.039049521240667,78.65450095385313&key=AIzaSyCVeEuZcqrs9phnrk1aNSpiJ57hb-V8hhE

    @GET("/maps/api/geocode/json")
    Call<GetAddressResultResponse> getAddressResultResponseCall(@Query("latlng") String input, @Query("key") String key);
}