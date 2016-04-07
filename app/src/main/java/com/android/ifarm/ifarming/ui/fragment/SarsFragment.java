package com.android.ifarm.ifarming.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.ui.adapter.HomeAdapter;

public class SarsFragment extends BaseFragment {

    public static SarsFragment newFragment() {
        SarsFragment fragment = new SarsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_sars, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        HomeAdapter adapter = new HomeAdapter();
        Bundle args = getArguments();
        if (args == null) {
            args = new Bundle();
        }
    }
}
