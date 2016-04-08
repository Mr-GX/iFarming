package com.android.ifarm.ifarming.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.ifarm.ifarming.R;

import butterknife.Bind;
import butterknife.OnClick;

public class BasicAnimFragment extends BaseFragment {

    public static BasicAnimFragment newFragment() {
        BasicAnimFragment fragment = new BasicAnimFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_basic_anim, container, false);
        bindView(this, view);
        return view;
    }

    @Bind(R.id.from)
    TextView mFrom;
    @Bind(R.id.type)
    TextView mType;
    @Bind(R.id.pinzhong)
    TextView mPz;
    @Bind(R.id.count)
    EditText mCount;

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
