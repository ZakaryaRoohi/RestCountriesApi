package com.geo.sampleapplication.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

    public static final String BASE_URL = " https://restcountries.eu/rest/v2/all/";

    private RetrofitInstance() {
    }

    private static Retrofit sRetrofit = null;

    public static Retrofit getInstance() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}
