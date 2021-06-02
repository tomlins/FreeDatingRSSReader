package com.ifreedating.freedatingrssreader.fragment;

import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ifreedating.freedatingrssreader.app.R;

public class FilteredFragment extends ListFragment {

    public static final String TAG = "FilteredFragment";

    private View theView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "OnCreate");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.i(TAG, "OnPause");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        theView = inflater.inflate(R.layout.fragment_profiles_list, container, false);
        theView.setBackgroundColor(Color.parseColor("#444444"));
        return theView;
    }
}
