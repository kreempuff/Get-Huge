package com.udacity.gradle.builditbigger.paid;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Camera;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.gradle.builditbigger.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
        Camera camera = new Camera();
        camera.restore();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);


        return root;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
