package com.android.ifarm.ifarming.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.ifarm.ifarming.R;

import java.util.ArrayList;

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
    AppCompatSpinner mFrom;
    @Bind(R.id.type)
    AppCompatSpinner mType;
    @Bind(R.id.pinzhong)
    AppCompatSpinner mPz;
    @Bind(R.id.count)
    EditText mCount;

    @OnClick(R.id.save)
    void onSave() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        final ArrayList<String> mData=new ArrayList<>();
        for (int i=0;i<50;i++){
            mData.add("item"+i);
        }
        final ArrayAdapter adapterFrom = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, mData);
        adapterFrom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mFrom.setAdapter(adapterFrom);
        mFrom.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        final ArrayAdapter adapterType = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, mData);
        adapterType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mType.setAdapter(adapterType);
        mType.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        final ArrayAdapter adapterPz = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, mData);
        adapterPz.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mPz.setAdapter(adapterPz);
        mPz.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            }
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
    }
}
