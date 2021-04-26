package com.geo.sampleapplication.view.activity;


import androidx.fragment.app.Fragment;

import com.geo.sampleapplication.R;
import com.geo.sampleapplication.view.fragment.PageOneFragment;
import com.geo.sampleapplication.view.fragment.PageTwoFragment;

public class MainActivity extends SingleFragmentActivity implements PageOneFragment.ComputeCallbacks {

    // I use Singleton for easy develop and Implementation later
    @Override
    protected Fragment createFragment() {
        return PageOneFragment.newInstance();
    }

    //this is Activities Job to start a Fragment not own fragments. use CallBack here
    @Override
    public void onComputeClicked(Double latitude, Double longitude) {
        PageTwoFragment pageTwoFragment = PageTwoFragment.newInstance(latitude, longitude);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, pageTwoFragment)
                .commit();

    }
}

