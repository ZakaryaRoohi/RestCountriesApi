package com.geo.sampleapplication.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.geo.sampleapplication.model.ApiResponseItem;
import com.geo.sampleapplication.repository.ApiResponseRepository;

import java.util.List;

public class PageTwoFragmentViewModel extends AndroidViewModel {

    // use viewModel because in future maybe PageTwoFragment be bigger and
    // this class supply data for view  and survive configuration changes
    // such as screen rotations
    private final ApiResponseRepository mApiResponseRepository;


    public PageTwoFragmentViewModel(@NonNull Application application) {
        super(application);
        mApiResponseRepository = ApiResponseRepository.getInstance();

    }
    public LiveData<List<ApiResponseItem>> getAllApiResponseItemLiveData(){
        return mApiResponseRepository.getAllApiResponseItem();
    }

    public void fetchCountries() {
        mApiResponseRepository.fetchInitData();
    }


}
