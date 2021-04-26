package com.geo.sampleapplication.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.geo.sampleapplication.model.ApiResponse;
import com.geo.sampleapplication.model.ApiResponseItem;
import com.geo.sampleapplication.network.RestApi;
import com.geo.sampleapplication.network.RetrofitInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiResponseRepository {
    //this repository fetch data from Api

    //mutableLiveData is writeable and readable but  liveData is just readable
    private final RestApi mRestApi;
    private MutableLiveData<List<ApiResponseItem>> mAllCountryLivaData;
    private static ApiResponseRepository sRepository;

    //constructor
    private ApiResponseRepository() {

        mRestApi = RetrofitInstance.getInstance().create(RestApi.class);
        mAllCountryLivaData = new MutableLiveData<>();
    }

    //singleton
    public static ApiResponseRepository getInstance() {
        if (sRepository == null) {
            sRepository = new ApiResponseRepository();
        }
        return sRepository;
    }


    public void fetchInitData() {
            //send request to get data using retrofit Library
        //this method run on background thread not on UiThread
        mRestApi.listItems().enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful()) {
                    mAllCountryLivaData.setValue(response.body().getApiResponse());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {

            }
        });

    }

    //use live data
    public LiveData<List<ApiResponseItem>> getAllApiResponseItem() {
        return mAllCountryLivaData;
    }


}
