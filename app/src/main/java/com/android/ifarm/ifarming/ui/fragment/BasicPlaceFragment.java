package com.android.ifarm.ifarming.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.ifarm.ifarming.R;
import com.android.ifarm.ifarming.widget.CircleImageView;

import butterknife.Bind;
import butterknife.OnClick;

public class BasicPlaceFragment extends BaseFragment {

    public static BasicPlaceFragment newFragment() {
        BasicPlaceFragment fragment = new BasicPlaceFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_place, container, false);
        bindView(this, view);
        return view;
    }

    @Bind(R.id.cover)
    CircleImageView mCover;
    @Bind(R.id.name)
    EditText mName;
    @Bind(R.id.place)
    EditText mPlace;
    @Bind(R.id.people)
    EditText mPeople;

    @OnClick(R.id.save)
    void onSave() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
    }
}
