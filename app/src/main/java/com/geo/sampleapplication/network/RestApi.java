package com.geo.sampleapplication.network;

import com.geo.sampleapplication.model.ApiResponse;


import retrofit2.Call;
import retrofit2.http.GET;

import static com.geo.sampleapplication.network.RetrofitInstance.BASE_URL;

public interface RestApi {


    @GET(".")
    Call<ApiResponse> listItems();
}
