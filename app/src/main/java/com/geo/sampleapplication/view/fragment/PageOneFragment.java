package com.geo.sampleapplication.view.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.geo.sampleapplication.R;
import com.geo.sampleapplication.databinding.FragmentPageOneBinding;
import com.geo.sampleapplication.utils.NumericUtils;


public class PageOneFragment extends Fragment {

    //use data binding
    private FragmentPageOneBinding mBinding;
    // define local Variables to save inputs from User
    private Double mLatitude;
    private Double mLongitude;
    // define an object of this fragment's callback
    private ComputeCallbacks mComputeCallbacks;

    // default constructor
    private PageOneFragment() {
    }

    //every component that want run this Fragment shod Use this
    public static PageOneFragment newInstance() {
        return new PageOneFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflate fragment
        mBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_page_one,
                container,
                false);
        return mBinding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //button search listener
        mBinding.buttonSearch.setOnClickListener(v -> {
            // try until user enter valid Type format . user should just enter number and .
            try {
                // trim user Input to have just 2 place after .
                mLatitude = NumericUtils.trimInputTo2DecimalPlace(Double.parseDouble(mBinding.editTextLatitude.getText().toString()));
                mLongitude = NumericUtils.trimInputTo2DecimalPlace(Double.parseDouble(mBinding.editTextLongitude.getText().toString()));
                //check range of input
                if (mLatitude >= -85 && mLatitude <= 85 && mLongitude >= -180 && mLongitude <= 180) {
                    mComputeCallbacks.onComputeClicked(mLatitude, mLongitude);

                } else {
                    //if range of number wasn't valid show toast to user
                    Toast.makeText(getActivity(),
                            "invalid number range",
                            Toast.LENGTH_SHORT)
                            .show();

                }
            } catch (NumberFormatException e) {
                //if input format type wasn't valid show toast to user
                Toast.makeText(getActivity(),
                        "invalid number format",
                        Toast.LENGTH_SHORT)
                        .show();
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        //this is for that the activity should implement my callback
        if (context instanceof ComputeCallbacks) {
            mComputeCallbacks = (ComputeCallbacks) context;
        } else {
            throw new ClassCastException(context.toString() + "must implement onComputeClicked");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();

        //when fragment was destroy the callback object should be remove .
        // this is for prevent memory leek
        mComputeCallbacks = null;
    }

    // define interface for use callback
    public interface ComputeCallbacks {
        void onComputeClicked(Double latitude, Double longitude);
    }


}