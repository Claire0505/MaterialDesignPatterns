package com.admin.claire.materialdesignpatterns.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.admin.claire.materialdesignpatterns.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    public static final String TITLE = "FIRST";

    public FirstFragment() {
        // Required empty public constructor
    }

    public static FirstFragment newInstance(){
        return new FirstFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__first, container, false);
    }

}
