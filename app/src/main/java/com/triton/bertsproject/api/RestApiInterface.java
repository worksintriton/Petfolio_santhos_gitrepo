package com.triton.bertsproject.api;


import com.triton.bertsproject.responsepojo.FetchAllBrandsResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentCategoriesResponse;
import com.triton.bertsproject.responsepojo.FetchAllParentMakesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

public interface RestApiInterface {

    /*Get All Categories*/
    @GET("category")
    Call<FetchAllParentCategoriesResponse> fetchallcategoriesListResponseCall(@Header("Content-Type") String type);

    /*Get All Brands*/
    @GET("brand")
    Call<FetchAllBrandsResponse> fetchallbrandsListResponseCall(@Header("Content-Type") String type);

    /*Get All Makes*/
    @GET("make")
    Call<FetchAllParentMakesResponse> fetchallmakesListResponseCall(@Header("Content-Type") String type);

}
