package com.geo.sampleapplication.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.geo.sampleapplication.R;
import com.geo.sampleapplication.adapter.CountryRecyclerAdapter;
import com.geo.sampleapplication.databinding.FragmentPageTwoBinding;
import com.geo.sampleapplication.model.ApiResponseItem;
import com.geo.sampleapplication.viewmodel.PageTwoFragmentViewModel;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;


public class PageTwoFragment extends Fragment implements OnMapReadyCallback {


    //define Keys to get Argument that send from previous fragment
    public static final String ARG_LATITUDE = "ArgLatitude";
    public static final String ARG_LONGITUDE = "ArgsLongitude";

    private PageTwoFragmentViewModel mViewModel;
    private FragmentPageTwoBinding mBinding;
    // define RecyclerAdapter
    private CountryRecyclerAdapter mAdapter;

    private GoogleMap mMap;
    private LatLng mLatLng;


    public PageTwoFragment() {
    }

    public static PageTwoFragment newInstance(Double latitude, Double longitude) {
        PageTwoFragment pageTwoFragment = new PageTwoFragment();
        Bundle args = new Bundle();
        args.putDouble(ARG_LATITUDE, latitude);
        args.putDouble(ARG_LONGITUDE, longitude);
        pageTwoFragment.setArguments(args);
        return pageTwoFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            double latitude = getArguments().getDouble(ARG_LATITUDE);
            double longitude = getArguments().getDouble(ARG_LONGITUDE);
            mLatLng = new LatLng(latitude, longitude);
        }
        mViewModel = new ViewModelProvider(this).get(PageTwoFragmentViewModel.class);
        mViewModel.fetchCountries();
        mAdapter = new CountryRecyclerAdapter();
        //use observer  ObserverDesign Pattern
        mViewModel.getAllApiResponseItemLiveData().observe(this, apiResponseItems -> {
            mAdapter.notifyDataSetChanged();
        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflate ui use dataBinding
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_page_two,
                container,
                false);

        mBinding.mapView.onCreate(savedInstanceState);
        mBinding.mapView.getMapAsync(this);
        mBinding.mapView.onStart();

        return mBinding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpAdapter();

    }

    public void setUpAdapter() {

        List<ApiResponseItem> apiResponseItems = mViewModel
                .getAllApiResponseItemLiveData().getValue();

        if (apiResponseItems == null)
            Toast.makeText(getActivity(), "response is null", Toast.LENGTH_SHORT).show();

        mAdapter.setProducts(apiResponseItems);
        mBinding.recyclerView.setAdapter(mAdapter);


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.getUiSettings().setMyLocationButtonEnabled(false);
        Toast.makeText(getContext(), getResources().getString(R.string.long_click_for_add_map_marker), Toast.LENGTH_LONG).show();

        // Updates the location and zoom of the MapView
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(mLatLng, 10);
        mMap.animateCamera(cameraUpdate);
        MarkerOptions markerOptions1 = new MarkerOptions();
        markerOptions1.position(mLatLng);
        mMap.addMarker(markerOptions1);

        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                mLatLng = latLng;
                CameraUpdate newCameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(latLng);
                mMap.clear();
                mMap.animateCamera(newCameraUpdate);
                mMap.addMarker(markerOptions);
            }
        });


    }

    @Override
    public void onResume() {
        mViewModel.getAllApiResponseItemLiveData().observe(this, apiResponseItems -> {
            mAdapter.notifyDataSetChanged();
        });
        setUpAdapter();
        mBinding.mapView.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mBinding.mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mBinding.mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mBinding.mapView.onLowMemory();
    }


}